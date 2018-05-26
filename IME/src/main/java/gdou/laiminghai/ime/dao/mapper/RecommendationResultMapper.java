package gdou.laiminghai.ime.dao.mapper;

import gdou.laiminghai.ime.model.entity.RecommendationResult;
import java.util.List;

public interface RecommendationResultMapper {
    int deleteByPrimaryKey(Long recommendationId);

    int insert(RecommendationResult record);

    RecommendationResult selectByPrimaryKey(Long recommendationId);

    List<RecommendationResult> selectAll();

    int updateByPrimaryKey(RecommendationResult record);
    
    //我是分割线
    /**
     * 查找用户推荐结果
     * @param userId
     * @return
     * @author: laiminghai
     * @datetime: 2018年5月26日 上午10:52:39
     */
    List<Long> findProductIdsByUserId(Long userId);
    /**
     * 删除用户推荐结果 
     * @param userId
     * @author: laiminghai
     * @datetime: 2018年5月26日 上午10:53:40
     */
    void deleteByUserId(Long userId);
}