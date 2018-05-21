package gdou.laiminghai.ime.common.exception;

public enum ServiceResultEnum {
	SUCCESS(0,"操作成功"),
	CAPTCHA_SMS_TIMEOUT(101,"验证码失效"),
	CAPTCHA_SMS_NOT_MATCH(102,"短信验证码不匹配"),
	CAPTCHA_SMS_NOT_EXIST(103,"短信验证码未获取"),
	CAPTCHA_IMAGE_NOT_MATCH(104,"图片验证码不匹配"),
	USER_NO_LOGIN(201,"用户未登录"),
	USER_REGISTER_PHONE_BE_USED(202,"手机号已被注册"),
	USER_ACCOUNT_NAME_NOT_ALLOWED(203,"账号名非法"),
	USER_ACCOUNT_PASSWORD_NOT_MATCH(204,"账号或密码输入错误"),
	USER_NOT_EXIST(205,"用户不存在"),
	USER_PHONE_INVALID(206,"手机号为空或无效"),
	USER_SESSION_TIMEOUT(207,"用户SESSION已失效过期"),
	USER_INVALID_ACTION(208,"非法请求或操作"),
	USER_PORTRAIT_UPDATE_ERROR(209,"用户头像保存失败"),
	USER_PASSWORD_EMPTY(210,"密码为空"),
	FORGET_PASSWORD_CAPTCHA_ERROR(211,"验证码输入错误，请重试"),
	FORGET_PASSWORD_USER_NOT_EXIST(212,"该用户未注册，请检查是否输入正确"),
	FORGET_PASSWORD_ACCOUNT_NOT_ALLOWED(213,"请输入正确的手机号或者邮箱号"),
	ACCOUNT_BE_BINDED(214,"账号已被绑定"),
	PRODUCT_FORM_IMCOMPLETE(301,"表单必填信息未提供"),
	PRODUCT_PICTURE_SAVE_FAILURE(302,"商品图片文件保存失败"),
	PRODUCT_NOT_FOUND(303,"产品不存在"),
	COMMENT_NOT_FOUND(401,"心得不存在"),
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
