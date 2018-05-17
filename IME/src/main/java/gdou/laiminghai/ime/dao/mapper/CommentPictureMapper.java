package gdou.laiminghai.ime.dao.mapper;

import gdou.laiminghai.ime.model.entity.CommentPicture;
import java.util.List;

public interface CommentPictureMapper {
    int deleteByPrimaryKey(Long pictureId);

    int insert(CommentPicture record);

    CommentPicture selectByPrimaryKey(Long pictureId);

    List<CommentPicture> selectAll();

    int updateByPrimaryKey(CommentPicture record);
    
    //我是分割线
    /**
     * 根据心得ID删除图片关联
     * @param commentId
     * @author: laiminghai
     * @datetime: 2018年5月17日 下午6:41:17
     */
    void deleteByCommentId(Long commentId);
    /**
     * 根据心得ID查找图片关联
     * @param commentId
     * @return
     * @author: laiminghai
     * @datetime: 2018年5月17日 下午6:42:03
     */
    List<CommentPicture> selectByCommentId(Long commentId);
}