package gdou.laiminghai.ime.dao.mapper;

import gdou.laiminghai.ime.model.entity.ProductInfo;
import gdou.laiminghai.ime.model.entity.UserFollowProduct;
import java.util.List;
import java.util.Map;

public interface UserFollowProductMapper {
    int deleteByPrimaryKey(Long userProductId);

    int insert(UserFollowProduct record);

    UserFollowProduct selectByPrimaryKey(Long userProductId);

    List<UserFollowProduct> selectAll();

    int updateByPrimaryKey(UserFollowProduct record);
    
    //我是分割线
    /**
     * 条件查询用户关注
     * @param map
     * @return
     * @author: laiminghai
     * @datetime: 2018年5月22日 上午12:56:18
     */
    List<UserFollowProduct> selectByCondition(Map<String,Object> map);
    /**
     * 查询关注的产品
     * @param userId
     * @return
     * @author: laiminghai
     * @datetime: 2018年5月22日 上午1:14:06
     */
    List<UserFollowProduct> findFollowedProducts(Long userId);
    /**
     * 统计商品关注量
     * @param productId
     * @return
     * @author: laiminghai
     * @datetime: 2018年5月29日 上午8:31:39
     */
    Long countUserFollow(Long productId);
}