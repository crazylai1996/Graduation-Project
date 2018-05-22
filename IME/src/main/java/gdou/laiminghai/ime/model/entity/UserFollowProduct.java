package gdou.laiminghai.ime.model.entity;

import java.util.Date;

public class UserFollowProduct {
    private Long userProductId;

    private Long productId;

    private Long userId;

    private Date followTime;
    
    //关联属性
    private ProductInfo productInfo;

    public Long getUserProductId() {
        return userProductId;
    }

    public void setUserProductId(Long userProductId) {
        this.userProductId = userProductId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getFollowTime() {
        return followTime;
    }

    public void setFollowTime(Date followTime) {
        this.followTime = followTime;
    }

	public ProductInfo getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(ProductInfo productInfo) {
		this.productInfo = productInfo;
	}
    
}