package gdou.laiminghai.ime.dao.mapper;

import gdou.laiminghai.ime.model.entity.UserInfo;
import java.util.List;
import java.util.Map;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(UserInfo record);

    UserInfo selectByPrimaryKey(Long userId);

    List<UserInfo> selectAll();

    int updateByPrimaryKey(UserInfo record);
    
    //我是分割线
    UserInfo selectByCondition(Map<String, Object> map);
}