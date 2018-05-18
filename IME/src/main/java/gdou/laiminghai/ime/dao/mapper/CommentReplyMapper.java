package gdou.laiminghai.ime.dao.mapper;

import gdou.laiminghai.ime.model.entity.CommentReply;
import java.util.List;
import java.util.Map;

public interface CommentReplyMapper {
    int deleteByPrimaryKey(Long replyId);

    int insert(CommentReply record);

    CommentReply selectByPrimaryKey(Long replyId);

    List<CommentReply> selectAll();

    int updateByPrimaryKey(CommentReply record);
    
    //我是分割线
    /**
     * 条件查询心得回复
     * @param map
     * @return
     * @author: laiminghai
     * @datetime: 2018年5月18日 下午5:39:28
     */
    List<CommentReply> selectByCondition(Map<String,Object> map);
}