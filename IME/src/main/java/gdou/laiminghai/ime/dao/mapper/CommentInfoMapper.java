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
    /**
     * 查找最近一个点评 
     * @param productId
     * @return
     * @author: laiminghai
     * @datetime: 2018年5月27日 下午8:41:56
     */
    CommentInfo findLatestCommentByProductId(Long productId);
    /**
     * 统计商品心得数量 
     * @param productId
     * @return
     * @author: laiminghai
     * @datetime: 2018年5月29日 上午12:51:07
     */
    Long countCommentByProductId(Long productId);
    /**
     * 评分统计
     * @param productId
     * @return
     * @author: laiminghai
     * @datetime: 2018年5月29日 上午10:33:13
     */
    Long countCommentHeart(Map<String,Object> map);
    
    /**
     * 用户肤质统计
     * @param map
     * @return
     * @author: laiminghai
     * @datetime: 2018年5月29日 下午12:11:21
     */
    Long countUserSkinTexture(Map<String,Object> map);
    /**
     * 用户年龄统计
     * @param map
     * @return
     * @author: laiminghai
     * @datetime: 2018年5月29日 下午12:30:33
     */
    Long countUserAge(Map<String,Object> map);
    /**
     * 统计性价综合评分
     * @param productId
     * @return
     * @author: laiminghai
     * @datetime: 2018年5月29日 下午3:06:29
     */
    Float countAvgScore(Long productId);
}