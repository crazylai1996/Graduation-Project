package gdou.laiminghai.ime.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.model.GenericDataModel;
import org.apache.mahout.cf.taste.impl.model.GenericPreference;
import org.apache.mahout.cf.taste.impl.model.GenericUserPreferenceArray;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.Preference;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import gdou.laiminghai.ime.common.util.RedisKeyUtil;
import gdou.laiminghai.ime.dao.redis.RedisDao;
import gdou.laiminghai.ime.model.dto.UserPrefDTO;
import gdou.laiminghai.ime.model.vo.ProductInfoVO;
import gdou.laiminghai.ime.service.ProductService;
import gdou.laiminghai.ime.service.RecommendationResultService;
import gdou.laiminghai.ime.service.RecommendationService;

@Service
public class RecommendationServiceImpl implements RecommendationService {
	
	//日志记录
	private static final Logger logger = Logger.getLogger(RecommendationServiceImpl.class);
	
	@Autowired
	private RedisDao redisDao;
	
	@Autowired
	private RecommendationResultService recommendationResultService;
	
	@Autowired
	private TaskExecutor taskExecutor;
	
	@Autowired
	private ProductService productService;

	@Override
	public void addNewPref(UserPrefDTO prefDTO) {
		Map<String,String> prefMap = new HashMap<>();
		long userId = prefDTO.getUserId();
		prefMap.put("userId", userId+"");
		long productId = prefDTO.getProductId();
		prefMap.put("productId", productId+"");
		float value = prefDTO.getValue();
		prefMap.put("value", value+"");
		String userPrefKey = RedisKeyUtil.getUserPrefKey(userId);
		//存储用户偏好
		redisDao.hPutAll(userPrefKey, prefMap);
		//加入分类偏好集合
		String userClassPrefsKey = RedisKeyUtil.getUserClassPrefsKey(
				prefDTO.getSkinTexture(), prefDTO.getBornYear());
		redisDao.sAdd(userClassPrefsKey, userPrefKey);
		//记录用户新增偏好Count
		String userNewPrefsKey = RedisKeyUtil.getUserNewPrefsKey();
		redisDao.zAdd(userNewPrefsKey, userId+"", 1);
		double newCount = redisDao.zScore(userNewPrefsKey, userId + "");
		//当前存在10个以上新偏好，重新生成推荐结果
		if(newCount >= 10) {
			//重新生成推荐记录
			taskExecutor.execute(
					new RecommendationTask(userId, userClassPrefsKey));
		}
	}

	@Override
	public List<ProductInfoVO> getRecommendationResult(Long userId) {
		List<Long> productIds = recommendationResultService.getRecommendationResult(userId);
		return productService.findMoreProductInfo(productIds);
	}

	/**
	 * 推荐生成任务
	 * @ClassName: RecommendationTask
	 * @author: laiminghai
	 * @datetime: 2018年5月26日 下午7:55:34
	 */
	private class RecommendationTask implements Runnable {

		private long userId;
		private String prefsSetKey;
		
		public RecommendationTask(long userId,String prefsSetKey) {
			super();
			this.userId = userId;
			this.prefsSetKey = prefsSetKey;
		}

		@Override
		public void run() {
			Set<String> prefKeys = redisDao.setMembers(prefsSetKey);
			Map<Long,List<Preference>> prefsMap = new HashMap<>();
			for (String prefKey : prefKeys) {
				Map<Object,Object> prefMap = redisDao.hGetAll(prefKey);
				long userId = (long) prefMap.get("userId");
				long productId = (long) prefMap.get("productId");
				float value = (float) prefMap.get("value");
				Preference preference = new GenericPreference(userId,productId,value);
				
				List<Preference> preferences = prefsMap.get(userId);
				if(preferences == null) {
					preferences = new ArrayList<>();
					prefsMap.put(userId, preferences);
				}
				preferences.add(preference);
			}
			FastByIDMap<PreferenceArray> fastByIDMap = new FastByIDMap<PreferenceArray>();
			for(Map.Entry<Long, List<Preference>> entry:prefsMap.entrySet()) {
				fastByIDMap.put(entry.getKey(), new GenericUserPreferenceArray(entry.getValue()));
			}
			
			try {
				DataModel dataModel = new GenericDataModel(fastByIDMap);
				//指定用户相似度计算方法
				UserSimilarity userSimilarity = new PearsonCorrelationSimilarity(dataModel);
				//指定距离最近的10个用户作为邻居
				UserNeighborhood userNeighborhood = new NearestNUserNeighborhood(10, userSimilarity, dataModel);
				//创建推荐引擎，根据数据模型、用户相似度模型、以及邻近值构建推荐引擎
				//这里选用基于用户的推荐器，用户数量少时速度快
				Recommender recommender = new GenericUserBasedRecommender(dataModel, userNeighborhood, userSimilarity);
				//向ID为userId的用户的推荐20个商品
				List<RecommendedItem> recommendedItems = recommender.recommend(userId, 20);
				List<Long> productIds = new ArrayList<>();
				for (RecommendedItem recommendedItem : recommendedItems) {
					Long productId = recommendedItem.getItemID();
					productIds.add(productId);
				}
				//推荐结果保存至数据库
				recommendationResultService.addRecommendationResult(userId, productIds);
			} catch (TasteException e) {
				logger.error("推荐计算异常：",e);
			}
		}
		
	}
}
