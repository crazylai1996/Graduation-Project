package gdou.laiminghai.ime.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gdou.laiminghai.ime.dao.mapper.CommentPictureMapper;
import gdou.laiminghai.ime.model.entity.CommentPicture;
import gdou.laiminghai.ime.service.CommentPictureService;

/**
 * 心得图片业务实现类
 * @ClassName: CommentPictureServiceImpl
 * @author: laiminghai
 * @datetime: 2018年5月17日 下午6:58:17
 */
@Service
public class CommentPictureServiceImpl implements CommentPictureService{
	
	@Autowired
	private CommentPictureMapper commentPictureMapper;

	@Override
	public void addNewCommentPicture(CommentPicture commentPicture) {
		commentPictureMapper.insert(commentPicture);
	}

	@Override
	public List<CommentPicture> findByCommentId(Long commentId) {
		return commentPictureMapper.selectByCommentId(commentId);
	}

	@Override
	public void deleteByCommentId(Long commentId) {
		commentPictureMapper.deleteByCommentId(commentId);
	}
	
}
