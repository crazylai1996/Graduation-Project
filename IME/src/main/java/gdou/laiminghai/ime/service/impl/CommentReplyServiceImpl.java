package gdou.laiminghai.ime.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gdou.laiminghai.ime.common.setting.AppSetting;
import gdou.laiminghai.ime.common.statics.SkinTextureEnum;
import gdou.laiminghai.ime.dao.mapper.CommentReplyMapper;
import gdou.laiminghai.ime.model.entity.CommentReply;
import gdou.laiminghai.ime.model.entity.CommentReplyVO;
import gdou.laiminghai.ime.model.entity.UserInfo;
import gdou.laiminghai.ime.model.vo.UserInfoVO;
import gdou.laiminghai.ime.service.CommentReplyService;
import gdou.laiminghai.ime.service.UserService;

/**
 * 心得回复业务实现类
 * @ClassName: CommentReplyServiceImpl
 * @author: laiminghai
 * @datetime: 2018年5月18日 上午8:47:48
 */
@Service
public class CommentReplyServiceImpl implements CommentReplyService {
	
	//日志记录
	private static final Logger logger = Logger.getLogger(CommentReplyServiceImpl.class);

	@Autowired
	private CommentReplyMapper commentReplyMapper;
	
	@Autowired
	private UserService userService;
	
	@Override
	public CommentReplyVO addNewCommentReply(CommentReplyVO commentReplyVO) {
		Date currentTime = new Date();
		CommentReply commentReplyPO = new CommentReply();
		commentReplyPO.setArticleId(commentReplyVO.getArticleId());
		commentReplyPO.setParentCommentId(commentReplyVO.getParentCommentId());
		commentReplyPO.setUserId(commentReplyVO.getUserId());
		commentReplyPO.setReplyDetail(commentReplyVO.getReplyDetail());
		commentReplyPO.setReplyTime(currentTime);
		commentReplyMapper.insert(commentReplyPO);
		CommentReplyVO commentReplyVO2 = commentReplyPO2CommentReplyVO(commentReplyPO);
		//查找用户信息
		UserInfoVO userInfoVO = userService.getUserInfoById(commentReplyVO.getUserId());
		commentReplyVO2.setUserInfoVO(userInfoVO);
		return commentReplyVO2;
	}

	/**
	 * PO=>VO
	 * @param commentReplyPO
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月18日 下午12:21:38
	 */
	private CommentReplyVO commentReplyPO2CommentReplyVO(CommentReply commentReplyPO) {
		CommentReplyVO commentReplyVO = new CommentReplyVO();
		commentReplyVO.setReplyId(commentReplyPO.getReplyId());
		commentReplyVO.setArticleId(commentReplyPO.getArticleId());
		commentReplyVO.setParentCommentId(commentReplyPO.getParentCommentId());
		commentReplyVO.setUserId(commentReplyPO.getUserId());
		commentReplyVO.setReplyDetail(commentReplyPO.getReplyDetail());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String replyTime = sdf.format(commentReplyPO.getReplyTime());
		commentReplyVO.setReplyTime(replyTime);
		if(commentReplyPO.getUserInfo() != null) {
			UserInfo userInfo = commentReplyPO.getUserInfo();
			UserInfoVO userInfoVO = new UserInfoVO();
			userInfoVO.setUserId(userInfo.getUserId());
			userInfoVO.setUserName(userInfo.getUserName());
			userInfoVO.setNickname(userInfo.getNickname());
			userInfoVO.setGender(userInfo.getGender());
			//获取用户肤质
			if(StringUtils.isNotBlank(userInfo.getSkinTexture())) {
				SkinTextureEnum skinTexture = SkinTextureEnum.of(userInfo.getSkinTexture());
				if(skinTexture != null) {
					userInfoVO.setSkinTexture(skinTexture.getName());
				}
			}
			//获取用户年龄
			if(userInfo.getBornYear() != null) {
				userInfoVO.setAge(Calendar.getInstance().
					get(Calendar.YEAR)-userInfo.getBornYear());
			}
			
			userInfoVO.setIntroduction(userInfo.getIntroduction());
			userInfoVO.setPortrait(AppSetting.APP_ROOT+AppSetting.PORTRAIT_SAVED_PATH+userInfo.getPortrait());
			userInfoVO.setPhone(userInfo.getPhone());
			userInfoVO.setEmail(userInfo.getEmail());
			userInfoVO.setMembershipPoint(userInfo.getMembershipPoint());
			userInfoVO.setMemberLevel(userInfo.getMemberLevel());
			userInfoVO.setArea(userInfo.getArea());
			
			commentReplyVO.setUserInfoVO(userInfoVO);
		}
		return commentReplyVO;
	}
}
