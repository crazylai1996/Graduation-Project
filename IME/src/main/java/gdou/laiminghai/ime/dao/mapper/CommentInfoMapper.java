package gdou.laiminghai.ime.dao.mapper;

import gdou.laiminghai.ime.model.entity.CommentInfo;
import java.util.List;
import java.util.Map;

public interface CommentInfoMapper {
    int deleteByPrimaryKey(Long commentId);

    int insert(CommentInfo record);

    CommentInfo selectByPrimaryKey(Long commentId);

    List<CommentInfo> selectAll();

    int updateByPrimaryKey(CommentInfo record);
    
    //我是分割线
    /**
     * 查找用户上一篇心得
     * @param map
     * @return
     * @author: laiminghai
     * @datetime: 2018年5月17日 下午9:06:05
     */
    CommentInfo findLastComment(Map<String, Object> map);
    /**
     * 查找用户下一篇心得
     * @param map
     * @return
     * @author: laiminghai
     * @datetime: 2018年5月17日 下午9:06:42
     */
    CommentInfo findNextComment(Map<String, Object> map);
    /**
     * 查找用户最近3个点评记录
     * @param map
     * @return 点评产品ID
     * @author: laiminghai
     * @datetime: 2018年5月17日 下午10:22:12
     */
    List<Long> findThreeLastestCommentRecords(Map<String, Object> map);
    /**
     * 根据条件查询心得列表
     * @param map
     * @return
     * @author: laiminghai
     * @datetime: 2018年5月19日 上午12:46:20
     */
    List<CommentInfo> selectByCondition(Map<String,Object> map);
    /**
     * 查找用户关注动态
     * @param map
     * @return
     * @author: laiminghai
     * @datetime: 2018年5月24日 上午12:41:21
     */
    List<CommentInfo> findFollowedComments(Map<String,Object> map);
    /**
     * 查找最新使用心得
     * @param map
     * @return
     * @author: laiminghai
     * @datetime: 2018年5月24日 上午7:54:39
     */
    List<CommentInfo> findNewestComments();
}