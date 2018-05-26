package gdou.laiminghai.ime.model.vo;

import gdou.laiminghai.ime.model.entity.Area;

/**
 * 用户信息VO类
 * @ClassName: UserInfoVO
 * @author: laiminghai
 * @datetime: 2018年5月17日 上午10:19:19
 */
public class UserInfoVO {
	private Long userId;

    private String userName;
    
    private String nickname;

    private String gender;

    private String skinTexture;
    
    private Integer bornYear;

    private Integer age;

    private String introduction;

    private String portrait;

    private String phone;

    private String email;
    
    private Integer membershipPoint;

    private Integer memberLevel;
    
    private Area area;
    
    private String status;
    
    private boolean follow;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getSkinTexture() {
		return skinTexture;
	}

	public void setSkinTexture(String skinTexture) {
		this.skinTexture = skinTexture;
	}

	/**
	 * @return the bornYear
	 */
	public Integer getBornYear() {
		return bornYear;
	}

	/**
	 * @param bornYear the bornYear to set
	 */
	public void setBornYear(Integer bornYear) {
		this.bornYear = bornYear;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getPortrait() {
		return portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getMembershipPoint() {
		return membershipPoint;
	}

	public void setMembershipPoint(Integer membershipPoint) {
		this.membershipPoint = membershipPoint;
	}

	public Integer getMemberLevel() {
		return memberLevel;
	}

	public void setMemberLevel(Integer memberLevel) {
		this.memberLevel = memberLevel;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isFollow() {
		return follow;
	}

	public void setFollow(boolean follow) {
		this.follow = follow;
	}

	@Override
	public String toString() {
		return "UserInfoVO [userId=" + userId + ", userName=" + userName + ", nickname=" + nickname + ", gender="
				+ gender + ", skinTexture=" + skinTexture + ", bornYear=" + bornYear + ", age=" + age
				+ ", introduction=" + introduction + ", portrait=" + portrait + ", phone=" + phone + ", email=" + email
				+ ", membershipPoint=" + membershipPoint + ", memberLevel=" + memberLevel + ", area=" + area
				+ ", status=" + status + ", follow=" + follow + "]";
	}

}
