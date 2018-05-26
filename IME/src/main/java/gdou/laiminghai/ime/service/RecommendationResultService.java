package gdou.laiminghai.ime.service;

import java.util.List;

public interface RecommendationResultService {
	/**
	 * 批量添加推荐结果
	 * @param userId
	 * @param productIds
	 * @author: laiminghai
	 * @datetime: 2018年5月26日 下午7:46:11
	 */
	void addRecommendationResult(Long userId,List<Long> productIds);
	/**
	 * 查找用户推荐结果 
	 * @param userId
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月26日 下午8:00:19
	 */
	List<Long> getRecommendationResult(Long userId);
}
