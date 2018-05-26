package gdou.laiminghai.ime.service;

import java.util.List;

import gdou.laiminghai.ime.model.entity.UserBrowserRecord;
import gdou.laiminghai.ime.model.vo.ProductInfoVO;

public interface RankService {
	/**
	 * 商品浏览量排行
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月25日 下午3:42:59
	 */
	List<ProductInfoVO> getBrowserCountRank();
	/**
	 * 新增商品浏览记录
	 * @param browserRecord
	 * @author: laiminghai
	 * @datetime: 2018年5月25日 下午3:45:09
	 */
	void addBrowserRecord(UserBrowserRecord browserRecord);
	/**
	 * 排行榜更新（读取数据库），定时任务
	 * @author: laiminghai
	 * @datetime: 2018年5月26日 上午7:21:08
	 */
	void updateBrowserCountRank();
	/**
	 * 同步浏览记录至数据库，定时任务
	 * @author: laiminghai
	 * @datetime: 2018年5月26日 上午8:57:19
	 */
	void syncBrowserRecord();
}
