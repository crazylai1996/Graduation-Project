package gdou.laiminghai.ime.model.vo;

import java.util.List;

/**
 * 商品信息VO类
 * @ClassName: ProductInfoVO
 * @author: laiminghai
 * @datetime: 2018年5月14日 上午1:29:06
 */
public class ProductInfoVO {
	private Long productId;
	private String productName;
	private Integer brand;
	private String comeInDate;
	private String spec;
	private Float referencePrice;
	private Integer classify;
	private Integer property;
    private Integer effect;
    private String skinTexture;
    private String coverImage;
    private List<String> productImages;
    private String desc;
    /*我是分割线*/
    private String brandName;
    private String classifyName;
    private String propertyName;
    private String effectName;
    private boolean follow;
    private String followTime;
    
    private CommentInfoVO lastestComment;
    
    private long browserCount;
    private long followCount;
    private long commentCount;
    private float avgScore;
    
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getBrand() {
		return brand;
	}
	public void setBrand(Integer brand) {
		this.brand = brand;
	}
	public String getComeInDate() {
		return comeInDate;
	}
	public void setComeInDate(String comeInDate) {
		this.comeInDate = comeInDate;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public Float getReferencePrice() {
		return referencePrice;
	}
	public void setReferencePrice(Float referencePrice) {
		this.referencePrice = referencePrice;
	}
	public Integer getClassify() {
		return classify;
	}
	public void setClassify(Integer classify) {
		this.classify = classify;
	}
	public Integer getProperty() {
		return property;
	}
	public void setProperty(Integer property) {
		this.property = property;
	}
	public Integer getEffect() {
		return effect;
	}
	public void setEffect(Integer effect) {
		this.effect = effect;
	}
	public String getSkinTexture() {
		return skinTexture;
	}
	public void setSkinTexture(String skinTexture) {
		this.skinTexture = skinTexture;
	}
	public String getCoverImage() {
		return coverImage;
	}
	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}
	public List<String> getProductImages() {
		return productImages;
	}
	public void setProductImages(List<String> productImages) {
		this.productImages = productImages;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getClassifyName() {
		return classifyName;
	}
	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public String getEffectName() {
		return effectName;
	}
	public void setEffectName(String effectName) {
		this.effectName = effectName;
	}
	public boolean isFollow() {
		return follow;
	}
	public void setFollow(boolean follow) {
		this.follow = follow;
	}
	public String getFollowTime() {
		return followTime;
	}
	public void setFollowTime(String followTime) {
		this.followTime = followTime;
	}
	/**
	 * @return the lastestComment
	 */
	public CommentInfoVO getLastestComment() {
		return lastestComment;
	}
	/**
	 * @param lastestComment the lastestComment to set
	 */
	public void setLastestComment(CommentInfoVO lastestComment) {
		this.lastestComment = lastestComment;
	}
	public long getBrowserCount() {
		return browserCount;
	}
	public void setBrowserCount(long browserCount) {
		this.browserCount = browserCount;
	}
	public long getFollowCount() {
		return followCount;
	}
	public void setFollowCount(long followCount) {
		this.followCount = followCount;
	}
	public long getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(long commentCount) {
		this.commentCount = commentCount;
	}
	
	public float getAvgScore() {
		return avgScore;
	}
	public void setAvgScore(float avgScore) {
		this.avgScore = avgScore;
	}
	
	@Override
	public String toString() {
		return "ProductInfoVO [productId=" + productId + ", productName=" + productName + ", brand=" + brand
				+ ", comeInDate=" + comeInDate + ", spec=" + spec + ", referencePrice=" + referencePrice + ", classify="
				+ classify + ", property=" + property + ", effect=" + effect + ", skinTexture=" + skinTexture
				+ ", coverImage=" + coverImage + ", productImages=" + productImages + ", desc=" + desc + ", brandName="
				+ brandName + ", classifyName=" + classifyName + ", propertyName=" + propertyName + ", effectName="
				+ effectName + ", follow=" + follow + ", followTime=" + followTime + ", lastestComment="
				+ lastestComment + ", browserCount=" + browserCount + ", followCount=" + followCount + ", commentCount="
				+ commentCount + ", avgScore=" + avgScore + "]";
	}
	
}
