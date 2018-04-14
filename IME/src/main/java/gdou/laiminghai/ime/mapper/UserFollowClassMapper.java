package gdou.laiminghai.ime.mapper;

import gdou.laiminghai.ime.model.entity.UserFollowClass;
import java.util.List;

public interface UserFollowClassMapper {
    int deleteByPrimaryKey(Long userClassId);

    int insert(UserFollowClass record);

    UserFollowClass selectByPrimaryKey(Long userClassId);

    List<UserFollowClass> selectAll();

    int updateByPrimaryKey(UserFollowClass record);
}