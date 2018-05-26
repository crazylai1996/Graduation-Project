package gdou.laiminghai.ime.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Service;

import gdou.laiminghai.ime.common.setting.AppSetting;
import gdou.laiminghai.ime.common.util.JSONUtil;
import gdou.laiminghai.ime.common.util.RedisKeyUtil;
import gdou.laiminghai.ime.dao.redis.RedisDao;
import gdou.laiminghai.ime.model.entity.UserBrowserRecord;
import gdou.laiminghai.ime.model.vo.ProductInfoVO;
import gdou.laiminghai.ime.service.ProductService;
import gdou.laiminghai.ime.service.RankService;
import gdou.laiminghai.ime.service.UserBrowserRecordService;

@Service
public class RankServiceImpl implements RankService {
	
	//日志记录
	private static final Logger logger = Logger.getLogger(RankServiceImpl.class);
	
	@Autowired
	private RedisDao redisDao;
	@Autowired
	private ProductService productService;
	@Autowired
	private UserBrowserRecordService userBrowserRecordService;

	@Override
	public List<ProductInfoVO> getBrowserCountRank() {
		List<ProductInfoVO> rankList = new ArrayList<>();
		String rankKey = RedisKeyUtil.getProductBrowserRankKey();
		Set<TypedTuple<String>> rankValue = redisDao.
				zReverseRangeWithScores(rankKey, 0, AppSetting.PRODUCT_RANK_COUNT - 1);
		for (TypedTuple<String> typedTuple : rankValue) {
			Long productId = Long.parseLong(typedTuple.getValue());
			Long browserCount = typedTuple.getScore().longValue();
			ProductInfoVO productInfoVO = productService.getProductInfo(productId);
			productInfoVO.setBrowserCount(browserCount);
			rankList.add(productInfoVO);
		}
		return rankList;
	}

	@Override
	public void addBrowserRecord(UserBrowserRecord browserRecord) {
		String browserRecordKey = RedisKeyUtil.getToSyncProductBrowserRecordKey();
		Map<String, String> map = JSONUtil.object2Map(browserRecord);
		//浏览记录
		redisDao.hPutAll(browserRecordKey, map);
		//浏览记录队列
		String browserRecordsKey = RedisKeyUtil.getToSyncProductBrowserRecordsKey();
		redisDao.sAdd(browserRecordsKey, browserRecordKey);
		//更新排行榜
		String rankKey = RedisKeyUtil.getProductBrowserRankKey();
		Long rankSize = redisDao.zSize(rankKey);
		//保留50个商品的前提，若排行榜存在该商品，则更新
		Long zResult = redisDao.zRank(rankKey, browserRecord.getProductId()+"");
		if(rankSize <= 50 || zResult != null) {
			redisDao.zIncrementScore(rankKey, browserRecord.getProductId()+"", 1);
		}
		//测试专用---------------------------
		//记得删掉---------------------------
		this.syncBrowserRecord();
		//记得删掉---------------------------
	}

	@Override
	public void updateBrowserCountRank() {
		logger.info("开始更新浏览排行榜");
		String rankKey = RedisKeyUtil.getProductBrowserRankKey();
		//先清空
		redisDao.delete(rankKey);
		List<Map<String,Long>> browserRank = userBrowserRecordService.find7dBrowserCountRank();
		for (Map<String, Long> map : browserRank) {
			logger.info(map.toString());
			redisDao.zAdd(rankKey, map.get("productId")+"", map.get("count"));
		}
	}

	@Override
	public void syncBrowserRecord() {
		String recordsKey = RedisKeyUtil.getToSyncProductBrowserRecordsKey();
		Set<String> recordKeys = redisDao.setMembers(recordsKey);
		for (String recordKey : recordKeys) {
			//获取浏览记录
			Map<Object, Object> map = redisDao.hGetAll(recordKey);
			UserBrowserRecord record = JSONUtil.map2Object(map, UserBrowserRecord.class);
			userBrowserRecordService.addUserBrowserRecord(record);
			//删除忆同步浏览记录
			redisDao.delete(recordKey);
			redisDao.sRemove(recordsKey, recordKey);
		}
	}
	
}
