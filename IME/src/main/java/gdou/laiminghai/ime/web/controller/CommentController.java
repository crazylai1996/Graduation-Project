package gdou.laiminghai.ime.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import gdou.laiminghai.ime.common.exception.ServiceException;
import gdou.laiminghai.ime.common.exception.ServiceResultEnum;
import gdou.laiminghai.ime.common.setting.AppSetting;
import gdou.laiminghai.ime.common.util.ResultDTOUtil;
import gdou.laiminghai.ime.model.dto.PageResult;
import gdou.laiminghai.ime.model.dto.ResultDTO;
import gdou.laiminghai.ime.model.entity.CommentReplyVO;
import gdou.laiminghai.ime.model.vo.CommentInfoVO;
import gdou.laiminghai.ime.model.vo.ProductInfoVO;
import gdou.laiminghai.ime.model.vo.UserInfoVO;
import gdou.laiminghai.ime.service.CommentReplyService;
import gdou.laiminghai.ime.service.CommentService;
import gdou.laiminghai.ime.service.ProductService;
import gdou.laiminghai.ime.service.UserService;

/**
 * 使用心得控制器
 * 
 * @ClassName: CommentController
 * @author: laiminghai
 * @datetime: 2018年5月16日 下午8:32:05
 */
@Controller
@RequestMapping("/comment")
public class CommentController {

	// 日志记录
	private static final Logger logger = Logger.getLogger(CommentController.class);

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private CommentService commentService;

	@Autowired
	private ProductService productService;

	@Autowired
	private UserService userService;

	@Autowired
	private CommentReplyService commentReplyService;

	/**
	 * 评论心得图片上传
	 * 
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月16日 下午8:36:42
	 */
	@ResponseBody
	@RequestMapping("/pictureUpload.do")
	public JSONObject uploadCommentPicture(@RequestParam(value = "imgFile", required = true) MultipartFile imgFile) {
		logger.debug("上传的文件名：" + imgFile.getOriginalFilename());
		logger.debug("上传的文件大小：" + imgFile.getSize());
		HttpSession session = request.getSession();
		String savedPath = session.getServletContext().getRealPath("/" + AppSetting.COMMENT_PICTURE_TMP_PATH);
		JSONObject resultJSON = new JSONObject();
		String message = "";
		Map<String, Object> userInfoMap = (Map<String, Object>) session.getAttribute("userInfo");
		// 登录失效
		if (userInfoMap == null) {
			resultJSON.put("error", 1);
			resultJSON.put("message", "未登录，请先登录");
			return resultJSON;
		}
		// 允许的上传格式
		List<String> extList = Arrays.asList(AppSetting.COMMENT_PICTURE_FORMAT.split(","));
		// 上传的文件格式
		String originalFilename = imgFile.getOriginalFilename();
		String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
		Long fileSize = imgFile.getSize();
		// 文件格式限制
		if (!extList.contains(extName)) {
			resultJSON.put("error", 1);
			message = "上传失败，文件格式不被允许";
		} else {
			// 文件大小限制
			if (fileSize > AppSetting.COMMENT_PICTURE_SIZE) {
				resultJSON.put("error", 1);
				message = "上传失败，单张图片文件大小不能超过2MB";
			}
		}
		// 文件校验是否通过
		if (StringUtils.isBlank(message)) {
			String pictureName = commentService.saveCommentPicture(imgFile, savedPath);
			// 保存失败
			if (StringUtils.isBlank(pictureName)) {
				resultJSON.put("error", 1);
				message = "图片上传失败";
			} else {
				// 保存已经上传的使用心得图片
				List<String> uploadPictures = (List<String>) session.getAttribute("uploadPictures");
				if (uploadPictures == null) {
					uploadPictures = new ArrayList<>();
				}
				uploadPictures.add(pictureName);
				session.setAttribute("uploadPictures", uploadPictures);
				// 上传的图片地址，用于前端显示
				String pictureUrl = AppSetting.APP_ROOT + AppSetting.COMMENT_PICTURE_TMP_PATH + pictureName;
				resultJSON.put("error", 0);
				resultJSON.put("url", pictureUrl);
			}
		} else {
			resultJSON.put("message", message);
		}
		return resultJSON;
	}

	/**
	 * 添加使用心得
	 * 
	 * @param commentInfoVO
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月17日 上午10:33:39
	 */
	@ResponseBody
	@RequestMapping("/new.do")
	public ResultDTO addComment(CommentInfoVO commentInfoVO) {
		logger.debug("添加使用心得表单参数：" + commentInfoVO.toString());
		// 获取用户登录信息
		HttpSession session = request.getSession();
		Map<String, Object> userInfoMap = (Map<String, Object>) session.getAttribute("userInfo");
		// 登录失效
//		if (userInfoMap == null) {
//			throw new ServiceException(ServiceResultEnum.USER_SESSION_TIMEOUT);
//		}
		// 表单信息未提供
		if (commentInfoVO == null || commentInfoVO.getProductId() == null) {
			throw new ServiceException(ServiceResultEnum.USER_INVALID_ACTION);
		}
		String tmpPath = session.getServletContext().getRealPath("/" + AppSetting.COMMENT_PICTURE_TMP_PATH);
		String savedPath = session.getServletContext().getRealPath("/" + AppSetting.COMMENT_PICTURE_SAVED_PATH);
		// 获取用户ID
		Long userId = (Long) userInfoMap.get("userId");
		commentInfoVO.setUserId(userId);
		List<String> commentPictures = (List<String>) session.getAttribute("uploadPictures");
		commentService.addNewComment(commentInfoVO, commentPictures, tmpPath, savedPath);
		return ResultDTOUtil.success(null);
	}

	/**
	 * 获取使用心得详情信息
	 * 
	 * @param commentId
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月17日 下午4:20:58
	 */
	@RequestMapping("/info/{commentId}")
	public ModelAndView getCommentDetails(@PathVariable("commentId") Long commentId) {
		ModelAndView mav = new ModelAndView("comment/comment_details");
		// 心得详情
		CommentInfoVO commentInfoVO = commentService.getCommentInfo(commentId);
		mav.addObject("commentInfoVO", commentInfoVO);
		// 产品详情
		ProductInfoVO productInfoVO = productService.getProductInfo(commentInfoVO.getProductId());
		mav.addObject("productInfoVO", productInfoVO);
		// 心得用户详情
		UserInfoVO userInfoVO = userService.getUserInfoById(commentInfoVO.getUserId());
		mav.addObject("userInfoVO", userInfoVO);
		// 查询心得评论
		Map<String, Object> map = new HashMap<>();
		map.put("pageNum", 1);
		map.put("commentId", commentId);
		PageResult<CommentReplyVO> pageResult = commentReplyService.findCommentReplyList(map);
		logger.debug("分页查询到的心得回复："+pageResult.toString());
		mav.addObject("pageResult", pageResult);
		return mav;
	}
	
	/**
	 * 分页查询产品使用心得
	 * @param pageNum
	 * @param productId
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月19日 上午9:51:29
	 */
	@RequestMapping("/loadMoreComments.do")
	public ModelAndView getCommentByPage(Integer pageNum,Long productId) {
		// 获取用户登录信息
		HttpSession session = request.getSession();
		Map<String, Object> userInfoMap = (Map<String, Object>) session.getAttribute("userInfo");
		ModelAndView mav = new ModelAndView("comment/comment_fragment");
		//条件查询使用心得
		Map<String, Object> map = new HashMap<>();
		map.put("pageNum", pageNum);
		map.put("commentId", productId);
		PageResult<CommentInfoVO> pageResult = commentService.findCommentList(map);
		if(userInfoMap != null) {
			Long userId = (Long)userInfoMap.get("userId");
			for (CommentInfoVO commentInfoVO : pageResult.getList()) {
				UserInfoVO userInfoVO = commentInfoVO.getUserInfo();
				if(userInfoVO != null) {
					if(userService.isFollowedUser(userId, userInfoVO.getUserId())) {
						userInfoVO.setFollow(true);
					}
				}
			}
		}
		logger.debug("分页查询到的心得回复："+pageResult.toString());
		mav.addObject("pageResult", pageResult);
		return mav;
	}
	
	/**
	 * 查找最新点评
	 * @param pageNum
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月24日 上午9:43:59
	 */
	@RequestMapping("/newestComments/loadMore.do")
	public ModelAndView getNewestComments(Integer pageNum) {
		ModelAndView mav = new ModelAndView("comment/comment_fragment_index");
		Map<String, Object> map = new HashMap<>();
		map.put("pageNum", pageNum);
		PageResult<CommentInfoVO> commentPageResult = commentService.findNewestComments(map);
		mav.addObject("commentPageResult", commentPageResult);
		return mav;
	}
	
	/**
	 * 查找我的关注
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月24日 上午7:44:13
	 */
	@RequestMapping("/myFollowed/loadMore.do")
	public ModelAndView getMyFollowedComments(Integer pageNum) {
		// 获取用户登录信息
		HttpSession session = request.getSession();
		Map<String, Object> userInfoMap = (Map<String, Object>) session.getAttribute("userInfo");
		Long userId = (Long) userInfoMap.get("userId");
		ModelAndView mav = new ModelAndView("comment/comment_fragment_index");
		Map<String, Object> map = new HashMap<>();
		map.put("pageNum", pageNum);
		map.put("userId", userId);
		PageResult<CommentInfoVO> commentPageResult = commentService.findMyFollowedComments(map);
		mav.addObject("commentPageResult", commentPageResult);
		return mav;
	}
	
}
