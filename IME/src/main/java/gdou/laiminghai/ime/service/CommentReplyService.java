package gdou.laiminghai.ime.service;

import java.util.List;
import java.util.Map;

import gdou.laiminghai.ime.model.dto.PageResult;
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
	/**
	 * 查询心得回复
	 * @param commentId
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月18日 下午4:46:44
	 */
	PageResult<CommentReplyVO> findCommentReplyList(Map<String, Object> map);
}
