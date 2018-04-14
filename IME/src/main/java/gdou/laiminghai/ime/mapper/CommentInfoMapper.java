package gdou.laiminghai.ime.mapper;

import gdou.laiminghai.ime.model.entity.CommentInfo;
import java.util.List;

public interface CommentInfoMapper {
    int deleteByPrimaryKey(Long commentId);

    int insert(CommentInfo record);

    CommentInfo selectByPrimaryKey(Long commentId);

    List<CommentInfo> selectAll();

    int updateByPrimaryKey(CommentInfo record);
}