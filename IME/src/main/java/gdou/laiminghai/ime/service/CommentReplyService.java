package gdou.laiminghai.ime.service;

import java.util.List;

import gdou.laiminghai.ime.model.entity.CommentReply;
import gdou.laiminghai.ime.model.entity.CommentReplyVO;

/**
 * 心得回复业务接口
 * @ClassName: CommentReplyService
 * @author: laiminghai
 * @datetime: 2018年5月18日 上午8:45:42
 */
public interface CommentReplyService {
	/**
	 * 添加新回复
	 * @param commentReply
	 * @author: laiminghai
	 * @datetime: 2018年5月18日 上午8:46:38
	 */
	CommentReplyVO addNewCommentReply(CommentReplyVO commentReplyVO);
}
