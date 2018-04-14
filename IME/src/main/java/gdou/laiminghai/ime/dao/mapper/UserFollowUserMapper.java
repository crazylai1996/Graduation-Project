package gdou.laiminghai.ime.dao.mapper;

import gdou.laiminghai.ime.model.entity.UserFollowUser;
import java.util.List;

public interface UserFollowUserMapper {
    int deleteByPrimaryKey(Long userUserId);

    int insert(UserFollowUser record);

    UserFollowUser selectByPrimaryKey(Long userUserId);

    List<UserFollowUser> selectAll();

    int updateByPrimaryKey(UserFollowUser record);
}