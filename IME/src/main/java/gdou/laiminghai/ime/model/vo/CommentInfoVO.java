package gdou.laiminghai.ime.model.vo;

import java.util.Date;
import java.util.List;

/**
 * 评论心得VO类
 * @ClassName: CommentInfoVO
 * @author: laiminghai
 * @datetime: 2018年5月17日 上午10:19:36
 */
public class CommentInfoVO {
	/**
	 * 心得编号 
	 */
	private Long commentId;
	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 产品ID
	 */
    private Long productId;
    /**
     * 评论标题
     */
    private String articleTitle;
    /**
     * 性价评分
     */
    private Float worthMark;
    /**
     * 购买方式
     */
    private String buyWay;
    /**
     * 发表时间
     */
    private Date sendTime;
    /**
     * 纯文本内容
     */
    private String contentText;
    /**
     * html内容
     */
    private String contentHtml;
    
    //我是分割线，下面是拓展属性
    /**
     * 上一篇心得ID
     */
    private Long lastCommentId;
    /**
     * 上一篇心得标题
     */
    private String lastCommentTitle;
    /**
     * 下一篇心得ID
     */
    private Long nextCommentId;
    /**
     * 下一篇心得标题
     */
    private String nextCommentTitle;
    /**
     * 用户最近三条心得记录
     */
    private List<ProductInfoVO> productInfoVOs;
    /**
     * 点击量
     */
    private Integer readCount;
    /**
     * 点赞量
     */
    private Integer upCount;
    
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
		this.articleTitle = articleTitle;
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
		this.buyWay = buyWay;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
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
	public Long getLastCommentId() {
		return lastCommentId;
	}
	public void setLastCommentId(Long lastCommentId) {
		this.lastCommentId = lastCommentId;
	}
	public String getLastCommentTitle() {
		return lastCommentTitle;
	}
	public void setLastCommentTitle(String lastCommentTitle) {
		this.lastCommentTitle = lastCommentTitle;
	}
	public Long getNextCommentId() {
		return nextCommentId;
	}
	public void setNextCommentId(Long nextCommentId) {
		this.nextCommentId = nextCommentId;
	}
	public String getNextCommentTitle() {
		return nextCommentTitle;
	}
	public void setNextCommentTitle(String nextCommentTitle) {
		this.nextCommentTitle = nextCommentTitle;
	}
	public List<ProductInfoVO> getProductInfoVOs() {
		return productInfoVOs;
	}
	public void setProductInfoVOs(List<ProductInfoVO> productInfoVOs) {
		this.productInfoVOs = productInfoVOs;
	}
	public Integer getReadCount() {
		return readCount;
	}
	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}
	public Integer getUpCount() {
		return upCount;
	}
	public void setUpCount(Integer upCount) {
		this.upCount = upCount;
	}
	
	@Override
	public String toString() {
		return "CommentInfoVO [commentId=" + commentId + ", userId=" + userId + ", productId=" + productId
				+ ", articleTitle=" + articleTitle + ", worthMark=" + worthMark + ", buyWay=" + buyWay + ", sendTime="
				+ sendTime + ", contentText=" + contentText + ", contentHtml=" + contentHtml + ", lastCommentId="
				+ lastCommentId + ", lastCommentTitle=" + lastCommentTitle + ", nextCommentId=" + nextCommentId
				+ ", nextCommentTitle=" + nextCommentTitle + ", productInfoVOs=" + productInfoVOs + ", readCount="
				+ readCount + ", upCount=" + upCount + "]";
	}
	
}
