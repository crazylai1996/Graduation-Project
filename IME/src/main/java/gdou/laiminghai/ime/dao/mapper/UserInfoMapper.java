package gdou.laiminghai.ime.dao.mapper;

import gdou.laiminghai.ime.model.entity.UserInfo;
import java.util.List;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(UserInfo record);

    UserInfo selectByPrimaryKey(Long userId);

    List<UserInfo> selectAll();

    int updateByPrimaryKey(UserInfo record);
}