package gdou.laiminghai.ime.model.entity;

import java.util.Date;

public class CommentReply {
    private Long replyId;

    private Long articleId;

    private Long parentCommentId;

    private Long userId;

    private String replyDetail;

    private Date replyTime;

    private Integer reportCount;
    
    //我是分割线，下面是关联属性
    private UserInfo userInfo;

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
        this.replyDetail = replyDetail == null ? null : replyDetail.trim();
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    public Integer getReportCount() {
        return reportCount;
    }

    public void setReportCount(Integer reportCount) {
        this.reportCount = reportCount;
    }

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	@Override
	public String toString() {
		return "CommentReply [replyId=" + replyId + ", articleId=" + articleId + ", parentCommentId=" + parentCommentId
				+ ", userId=" + userId + ", replyDetail=" + replyDetail + ", replyTime=" + replyTime + ", reportCount="
				+ reportCount + ", userInfo=" + userInfo + "]";
	}
	
}