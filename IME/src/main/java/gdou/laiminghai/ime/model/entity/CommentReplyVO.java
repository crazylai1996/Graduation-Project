package gdou.laiminghai.ime.model.entity;

import java.util.Date;

import gdou.laiminghai.ime.model.vo.UserInfoVO;

public class CommentReplyVO {
	private Long replyId;

    private Long articleId;

    private Long parentCommentId;

    private Long userId;

    private String replyDetail;

    private String replyTime;
    
    //我是分割线，下面是拓展属性
    private Integer reportCount;
    
    private UserInfoVO userInfoVO;

	public Long getReplyId() {
		return replyId;
	}

	public void setReplyId(Long replyId) {
		this.replyId = replyId;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public Long getParentCommentId() {
		return parentCommentId;
	}

	public void setParentCommentId(Long parentCommentId) {
		this.parentCommentId = parentCommentId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getReplyDetail() {
		return replyDetail;
	}

	public void setReplyDetail(String replyDetail) {
		this.replyDetail = replyDetail;
	}

	public String getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(String replyTime) {
		this.replyTime = replyTime;
	}

	public Integer getReportCount() {
		return reportCount;
	}

	public void setReportCount(Integer reportCount) {
		this.reportCount = reportCount;
	}

	public UserInfoVO getUserInfoVO() {
		return userInfoVO;
	}

	public void setUserInfoVO(UserInfoVO userInfoVO) {
		this.userInfoVO = userInfoVO;
	}

	@Override
	public String toString() {
		return "CommentReplyVO [replyId=" + replyId + ", articleId=" + articleId + ", parentCommentId="
				+ parentCommentId + ", userId=" + userId + ", replyDetail=" + replyDetail + ", replyTime=" + replyTime
				+ ", reportCount=" + reportCount + ", userInfoVO=" + userInfoVO + "]";
	}
}
