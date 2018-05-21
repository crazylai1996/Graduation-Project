package gdou.laiminghai.ime.dao.mapper;

import gdou.laiminghai.ime.model.entity.UserFollowUser;
import java.util.List;
import java.util.Map;

public interface UserFollowUserMapper {
    int deleteByPrimaryKey(Long userUserId);

    int insert(UserFollowUser record);

    UserFollowUser selectByPrimaryKey(Long userUserId);

    List<UserFollowUser> selectAll();

    int updateByPrimaryKey(UserFollowUser record);
    
    //我是分割线
    List<UserFollowUser> selectByCondition(Map<String, Object> map);
}