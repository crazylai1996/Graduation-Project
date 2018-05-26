package gdou.laiminghai.ime.model.dto;

public class UserPrefDTO {
	private long userId;
	private long productId;
	private float value;
	private int bornYear;
	private String skinTexture;
	
	public UserPrefDTO() {
		
	}
	
	public UserPrefDTO(long userId, long productId, float value, int bornYear, String skinTexture) {
		super();
		this.userId = userId;
		this.productId = productId;
		this.value = value;
		this.bornYear = bornYear;
		this.skinTexture = skinTexture;
	}

	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	public int getBornYear() {
		return bornYear;
	}
	public void setBornYear(int bornYear) {
		this.bornYear = bornYear;
	}
	public String getSkinTexture() {
		return skinTexture;
	}
	public void setSkinTexture(String skinTexture) {
		this.skinTexture = skinTexture;
	}
	
	@Override
	public String toString() {
		return "UserPrefDTO [userId=" + userId + ", productId=" + productId + ", value=" + value + ", bornYear="
				+ bornYear + ", skinTexture=" + skinTexture + "]";
	}
}
