package gdou.laiminghai.ime.service;

import java.util.List;

import gdou.laiminghai.ime.model.entity.CommentPicture;

/**
 * 心得图片业务接口
 * @ClassName: CommentPictureService
 * @author: laiminghai
 * @datetime: 2018年5月17日 下午6:50:53
 */
public interface CommentPictureService {
	/**
	 * 添加新心得图片关联
	 * @param commentPicture
	 * @author: laiminghai
	 * @datetime: 2018年5月17日 下午8:11:02
	 */
	void addNewCommentPicture(CommentPicture commentPicture);
	/**
	 * 根据心得ID查找相关图片关联
	 * @param commentId
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月17日 下午6:56:52
	 */
	List<CommentPicture> findByCommentId(Long commentId);
	/**
	 * 根据心得ID删除相关图片关联
	 * @param commentId
	 * @author: laiminghai
	 * @datetime: 2018年5月17日 下午6:57:12
	 */
	void deleteByCommentId(Long commentId);
}
