package gdou.laiminghai.ime.dao.mapper;

import gdou.laiminghai.ime.model.entity.UserFollowProduct;
import java.util.List;

public interface UserFollowProductMapper {
    int deleteByPrimaryKey(Long userProductId);

    int insert(UserFollowProduct record);

    UserFollowProduct selectByPrimaryKey(Long userProductId);

    List<UserFollowProduct> selectAll();

    int updateByPrimaryKey(UserFollowProduct record);
}