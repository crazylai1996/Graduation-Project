package gdou.laiminghai.ime.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import gdou.laiminghai.ime.common.exception.ServiceException;
import gdou.laiminghai.ime.common.exception.ServiceResultEnum;
import gdou.laiminghai.ime.common.util.ResultDTOUtil;
import gdou.laiminghai.ime.model.dto.PageResult;
import gdou.laiminghai.ime.model.dto.ResultDTO;
import gdou.laiminghai.ime.model.entity.CommentReplyVO;
import gdou.laiminghai.ime.service.CommentReplyService;

/**
 * 心得回复控制器
 * @ClassName: CommentReplyController
 * @author: laiminghai
 * @datetime: 2018年5月18日 下午12:13:06
 */
@Controller
@RequestMapping("/commentReply")
public class CommentReplyController {
	
	//日志记录
	private static final Logger logger = Logger.getLogger(CommentReplyController.class);
	
	@Autowired
	private HttpServletRequest request;

	@Autowired
	private CommentReplyService commentReplyService;

	/**
	 * 添加心得回复
	 * 
	 * @param commentReplyVO
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月18日 上午9:37:04
	 */
	@ResponseBody
	@RequestMapping("/addNewCommentReply.do")
	public ResultDTO addCommentReply(CommentReplyVO commentReplyVO) {
		logger.debug("心得回复表单参数："+commentReplyVO.toString());
		// 获取用户登录信息
		HttpSession session = request.getSession();
		Map<String, Object> userInfoMap = (Map<String, Object>) session.getAttribute("userInfo");
		// 登录失效
		if (userInfoMap == null) {
			throw new ServiceException(ServiceResultEnum.USER_SESSION_TIMEOUT);
		}
		//获取用户ID
		Long userId = (Long)userInfoMap.get("userId");
		commentReplyVO.setUserId(userId);
		CommentReplyVO commentReplyVO2 = commentReplyService.addNewCommentReply(commentReplyVO);
		return ResultDTOUtil.success(commentReplyVO2);
	}
	
	/**
	 * 分页查询心得回复
	 * @param pageNum
	 * @param commentId
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月18日 下午10:22:57
	 */
	@RequestMapping("/loadMoreReply.do")
	public ModelAndView getCommentReplyByPage(Integer pageNum,Long commentId) {
		ModelAndView mav = new ModelAndView("comment/reply_fragment");
		// 查询心得评论
		Map<String, Object> map = new HashMap<>();
		map.put("pageNum", pageNum);
		map.put("commentId", commentId);
		PageResult<CommentReplyVO> pageResult = commentReplyService.findCommentReplyList(map);
		logger.debug("分页查询到的心得回复："+pageResult.toString());
		mav.addObject("pageResult", pageResult);
		return mav;
	}
}
