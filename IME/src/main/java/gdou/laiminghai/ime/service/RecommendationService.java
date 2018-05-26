package gdou.laiminghai.ime.service;

import java.util.List;

import gdou.laiminghai.ime.model.dto.UserPrefDTO;
import gdou.laiminghai.ime.model.vo.ProductInfoVO;

public interface RecommendationService {
	/**
	 * 新增用户偏好
	 * @param pref
	 * @author: laiminghai
	 * @datetime: 2018年5月26日 下午3:48:29
	 */
	void addNewPref(UserPrefDTO pref);
	/**
	 * 查找推荐结果
	 * @param userId
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月26日 下午7:57:58
	 */
	List<ProductInfoVO> getRecommendationResult(Long userId);
}
