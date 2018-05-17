package gdou.laiminghai.ime.dao.mapper;

import gdou.laiminghai.ime.model.entity.CommentPicture;
import java.util.List;

public interface CommentPictureMapper {
    int deleteByPrimaryKey(Long pictureId);

    int insert(CommentPicture record);

    CommentPicture selectByPrimaryKey(Long pictureId);

    List<CommentPicture> selectAll();

    int updateByPrimaryKey(CommentPicture record);
}