package gdou.laiminghai.ime.dao.mapper;

import gdou.laiminghai.ime.model.entity.CosmeticClass;
import gdou.laiminghai.ime.model.entity.UserFollowClass;
import java.util.List;
import java.util.Map;

public interface UserFollowClassMapper {
    int deleteByPrimaryKey(Long userClassId);

    int insert(UserFollowClass record);

    UserFollowClass selectByPrimaryKey(Long userClassId);

    List<UserFollowClass> selectAll();

    int updateByPrimaryKey(UserFollowClass record);
    
    //我是分割线
    /**
     * 条件查询关注的品类记录
     * @param map
     * @return
     * @author: laiminghai
     * @datetime: 2018年5月22日 上午8:19:56
     */
    List<UserFollowClass> selectByCondition(Map<String,Object> map);
    /**
     * 查询用户关注的品类
     * @param userId
     * @return
     * @author: laiminghai
     * @datetime: 2018年5月22日 上午8:21:17
     */
    List<CosmeticClass> findFollowedClasses(Long userId);
    /**
     * 根据用户ID删除用户关注的品类
     * @param userId
     * @author: laiminghai
     * @datetime: 2018年5月22日 上午9:33:15
     */
    void deleteByUserId(Long userId);
}