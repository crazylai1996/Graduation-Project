package gdou.laiminghai.ime.model.entity;

import java.util.Date;

public class CommentInfo {
    private Long commentId;

    private Long userId;

    private Long productId;

    private String articleTitle;

    private String articlaMark;

    private Float worthMark;

    private String buyWay;

    private Date sendTime;

    private Integer articleState;

    private Integer readCount;

    private Integer loveCount;

    private Integer upCount;

    private String contentText;
    
    private String contentHtml;

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle == null ? null : articleTitle.trim();
    }

    public String getArticlaMark() {
        return articlaMark;
    }

    public void setArticlaMark(String articlaMark) {
        this.articlaMark = articlaMark == null ? null : articlaMark.trim();
    }

    public Float getWorthMark() {
        return worthMark;
    }

    public void setWorthMark(Float worthMark) {
        this.worthMark = worthMark;
    }

    public String getBuyWay() {
        return buyWay;
    }

    public void setBuyWay(String buyWay) {
        this.buyWay = buyWay == null ? null : buyWay.trim();
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Integer getArticleState() {
        return articleState;
    }

    public void setArticleState(Integer articleState) {
        this.articleState = articleState;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public Integer getLoveCount() {
        return loveCount;
    }

    public void setLoveCount(Integer loveCount) {
        this.loveCount = loveCount;
    }

    public Integer getUpCount() {
        return upCount;
    }

    public void setUpCount(Integer upCount) {
        this.upCount = upCount;
    }

	public String getContentText() {
		return contentText;
	}

	public void setContentText(String contentText) {
		this.contentText = contentText;
	}

	public String getContentHtml() {
		return contentHtml;
	}

	public void setContentHtml(String contentHtml) {
		this.contentHtml = contentHtml;
	}
}