package gdou.laiminghai.ime.model.vo;

public class UserVO {
	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 账号
	 */
	private String account;
	/**
	 * 手机号
	 */
	private String phone;
	/**
	 * 邮箱号
	 */
	private String email;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 图片验证码
	 */
	private String imageCaptcha;
	/**
	 * 短信验证码
	 */
	private String smsCaptcha;
	/**
	 * 记住我
	 */
	private boolean rememberMe;
	/**
	 * 新密码
	 */
	private String newPassword;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getImageCaptcha() {
		return imageCaptcha;
	}
	public void setImageCaptcha(String imageCaptcha) {
		this.imageCaptcha = imageCaptcha;
	}
	public String getSmsCaptcha() {
		return smsCaptcha;
	}
	public void setSmsCaptcha(String smsCaptcha) {
		this.smsCaptcha = smsCaptcha;
	}
	public boolean isRememberMe() {
		return rememberMe;
	}
	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	@Override
	public String toString() {
		return "UserVO [userId=" + userId + ", account=" + account + ", phone=" + phone + ", email=" + email
				+ ", password=" + password + ", imageCaptcha=" + imageCaptcha + ", smsCaptcha=" + smsCaptcha
				+ ", rememberMe=" + rememberMe + ", newPassword=" + newPassword + "]";
	}
}
