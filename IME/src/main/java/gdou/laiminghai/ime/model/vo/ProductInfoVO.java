package gdou.laiminghai.ime.model.vo;

import java.util.Date;
import java.util.List;

/**
 * 商品信息VO类
 * @ClassName: ProductInfoVO
 * @author: laiminghai
 * @datetime: 2018年5月14日 上午1:29:06
 */
public class ProductInfoVO {
	private String productName;
	private Integer brand;
	private Date comeInDate;
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
	public Date getComeInDate() {
		return comeInDate;
	}
	public void setComeInDate(Date comeInDate) {
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
	@Override
	public String toString() {
		return "ProductInfoVO [productName=" + productName + ", brand=" + brand + ", comeInDate=" + comeInDate
				+ ", spec=" + spec + ", referencePrice=" + referencePrice + ", classify=" + classify + ", property="
				+ property + ", effect=" + effect + ", skinTexture=" + skinTexture + ", coverImage=" + coverImage
				+ ", productImages=" + productImages + ", desc=" + desc + ", brandName=" + brandName + ", classifyName="
				+ classifyName + ", propertyName=" + propertyName + ", effectName=" + effectName + "]";
	}
}
