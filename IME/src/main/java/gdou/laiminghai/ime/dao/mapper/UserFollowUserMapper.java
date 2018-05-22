package gdou.laiminghai.ime.dao.mapper;

import gdou.laiminghai.ime.model.entity.UserFollowUser;
import gdou.laiminghai.ime.model.entity.UserInfo;

import java.util.List;
import java.util.Map;

public interface UserFollowUserMapper {
    int deleteByPrimaryKey(Long userUserId);

    int insert(UserFollowUser record);

    UserFollowUser selectByPrimaryKey(Long userUserId);

    List<UserFollowUser> selectAll();

    int updateByPrimaryKey(UserFollowUser record);
    
    //我是分割线
    /**
     * 条件查询用户关注用户记录
     * @param map
     * @return
     * @author: laiminghai
     * @datetime: 2018年5月22日 上午12:13:12
     */
    List<UserFollowUser> selectByCondition(Map<String, Object> map);
    /**
     * 查询关注的用户
     * @param userId
     * @return
     * @author: laiminghai
     * @datetime: 2018年5月22日 上午12:13:06
     */
    List<UserInfo> findFollowedUsers(Long userId);
}