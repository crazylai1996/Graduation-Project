package gdou.laiminghai.ime.model.entity;

import java.util.Date;

public class UserBrowserRecord {
    private Long browserId;

    private Long userId;

    private Long productId;

    private Date browserTime;
    
    public UserBrowserRecord() {
		
	}
    
    public UserBrowserRecord(Long userId,Long productId) {
    	this.userId = userId;
    	this.productId = productId;
    	this.browserTime = new Date();
    }

    public Long getBrowserId() {
        return browserId;
    }

    public void setBrowserId(Long browserId) {
        this.browserId = browserId;
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

    public Date getBrowserTime() {
        return browserTime;
    }

    public void setBrowserTime(Date browserTime) {
        this.browserTime = browserTime;
    }

	@Override
	public String toString() {
		return "UserBrowserRecord [browserId=" + browserId + ", userId=" + userId + ", productId=" + productId
				+ ", browserTime=" + browserTime + "]";
	}
    
}