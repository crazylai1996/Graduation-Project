package gdou.laiminghai.ime.model.entity;

import java.util.Date;

public class ProductInfo {
	private Long productId;

    private String productName;

    private Integer brand;

    private Integer classify;

    private Integer property;

    private Integer effect;

    private String skinTexture;

    private String spec;

    private Date comeInDate;

    private Float referencePrice;

    private Float worthCount;

    private Long commentCount;

    private Long browserCount;

    private Long followCount;

    private String desc;
    
    private String cover;

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
        this.productName = productName == null ? null : productName.trim();
    }

    public Integer getBrand() {
        return brand;
    }

    public void setBrand(Integer brand) {
        this.brand = brand;
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

	public String getSkintexture() {
        return skinTexture;
    }

    public void setSkintexture(String skinTexture) {
        this.skinTexture = skinTexture == null ? null : skinTexture.trim();
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec == null ? null : spec.trim();
    }

    public Date getComeInDate() {
        return comeInDate;
    }

    public void setComeInDate(Date comeInDate) {
        this.comeInDate = comeInDate;
    }

    public Float getReferencePrice() {
        return referencePrice;
    }

    public void setReferencePrice(Float referencePrice) {
        this.referencePrice = referencePrice;
    }

    public Float getWorthCount() {
        return worthCount;
    }

    public void setWorthCount(Float worthCount) {
        this.worthCount = worthCount;
    }

    public Long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

    public Long getBrowserCount() {
        return browserCount;
    }

    public void setBrowserCount(Long browserCount) {
        this.browserCount = browserCount;
    }

    public Long getFollowCount() {
        return followCount;
    }

    public void setFollowCount(Long followCount) {
        this.followCount = followCount;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}
}