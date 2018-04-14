package gdou.laiminghai.ime.mapper;

import gdou.laiminghai.ime.model.entity.CommentReply;
import java.util.List;

public interface CommentReplyMapper {
    int deleteByPrimaryKey(Long replyId);

    int insert(CommentReply record);

    CommentReply selectByPrimaryKey(Long replyId);

    List<CommentReply> selectAll();

    int updateByPrimaryKey(CommentReply record);
}