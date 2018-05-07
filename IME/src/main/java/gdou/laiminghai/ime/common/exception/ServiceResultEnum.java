package gdou.laiminghai.ime.common.exception;

public enum ServiceResultEnum {
	SUCCESS(0,"操作成功"),
	CAPTCHA_SMS_TIMEOUT(101,"验证码失效"),
	CAPTCHA_SMS_NOT_MATCH(102,"短信验证码不匹配"),
	USER_NO_LOGIN(201,"用户未登录"),
	USER_REGISTER_PHONE_BE_USED(202,"手机号已被注册"),
	UNKONWN_ERROR(-1,"未知错误");
	
	private Integer code;//错误码
	private String message;//错误信息
	
	private ServiceResultEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}
	
	public String getMessage() {
		return message;
	}
}
