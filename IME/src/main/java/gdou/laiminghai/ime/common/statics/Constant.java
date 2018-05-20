package gdou.laiminghai.ime.common.statics;

public interface Constant {
	/**
	 * session范围key：图片验证码
	 */
	static final String WEB_KEY_IMAGE_CAPTCHA = "IMAGE_CAPTCHA";
	/**
	 * session范围key前缀：短信验证码
	 */
	static final String WEB_PRE_KEY_SMS_CAPTCHA = "SMS_CAPTCHA_";
	/**
	 * session范围key前缀：短信验证码获取时间
	 */
	static final String WEB_PRE_KEY_SMS_CAPTCHA_TIME = "SMS_CAPTCHA_TIME_";
	/**
	 * 忘记密码，验证码存取key
	 */
	static final String WEB_PRE_KEY_FORGET_PASSWORD_CAPTCHA = "FORGET_PASSWORD_CAPTCHA_";
	/**
	 * 忘记密码，验证码获取时间Key
	 */
	static final String WEB_PRE_KEY_FORGET_PASSWORD_CAPTCHA_TIME = "FORGET_PASSWORD_CAPTCHA_TIME_";
	/**
	 * 身份验证，验证码存取key
	 */
	static final String WEB_PRE_KEY_IDENTITY_AUTH_CAPTCHA = "IDENTITY_AUTH_CAPTCHA_";
	/**
	 * 身份验证，验证码获取时间Key
	 */
	static final String WEB_PRE_KEY_IDENTITY_AUTH_CAPTCHA_TIME = "IDENTITY_AUTH_CAPTCHA_TIME_";
	/**
	 * 身份验证，通过标识
	 */
	static final String WEB_PRE_KEY_IDENTITY_AUTH_PASS = "WEB_PRE_KEY_IDENTITY_AUTH_PASS_";
}
