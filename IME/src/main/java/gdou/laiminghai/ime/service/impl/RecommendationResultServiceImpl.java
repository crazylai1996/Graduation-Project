package gdou.laiminghai.ime.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gdou.laiminghai.ime.dao.mapper.RecommendationResultMapper;
import gdou.laiminghai.ime.model.entity.RecommendationResult;
import gdou.laiminghai.ime.service.RecommendationResultService;


@Service
public class RecommendationResultServiceImpl implements RecommendationResultService {

	@Autowired
	private RecommendationResultMapper recommendationResultMapper;
	
	@Override
	public void addRecommendationResult(Long userId, List<Long> productIds) {
		if(productIds == null || productIds.size() <= 0) {
			System.out.println(userId+"推荐结果为空");
			return ;
		}
		//先删除
		recommendationResultMapper.deleteByPrimaryKey(userId);
		//再插入
		for (Long productId : productIds) {
			RecommendationResult result = new RecommendationResult();
			result.setUserId(userId);
			result.setUserId(productId);
			result.setGenTime(new Date());
			recommendationResultMapper.insert(result);
		}
	}

	@Override
	public List<Long> getRecommendationResult(Long userId) {
		return recommendationResultMapper.findProductIdsByUserId(userId);
	}

}
