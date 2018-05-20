package gdou.laiminghai.ime.common.setting;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 应用参数
 * @ClassName: AppSetting
 * @author: laiminghai
 * @datetime: 2018年5月6日 下午12:51:53
 */
@Component
public class AppSetting {

	/**
	 * 项目根路径
	 */
	public static String APP_ROOT;
	/**
	 * 登录验证码长度
	 */
	public static int CAPTCHA_LOGIN_CHAR_LENGTH;
	/**
	 * 验证码图片宽度
	 */
	public static int CAPTCHA_LOGIN_IMAGE_WIDTH;
	/**
	 * 验证码图片高度
	 */
	public static int CAPTCHA_LOGIN_IMAGE_HEIGHT;
	/**
	 * 短信验证码长度
	 */
	public static int CAPTCHA_SMS_NUMBER_LENGTH;
	/**
	 * 短信验证码失效时间
	 */
	public static long CAPTCHA_SMS_TIMEOUT;
	/**
	 * 秒滴验证码通知短信请求地址
	 */
	public static String MIAODI_SMS_RQUEST_URL;
	/**
	 * 秒滴账号
	 */
	public static String MIAODI_ACCOUNT_SID;
	/**
	 * 秒滴TOKEN
	 */
	public static String MIAODI_AUTH_TOKEN;
	/**
	 * 秒滴短信模板ID
	 */
	public static String MIAODI_SMS_TEMPLATE_ID;
	/**
	 * 用户密码加密盐长度
	 */
	public static int USER_PASSWORD_SALT_LENGTH;
	/**
	 * 用户头像保存地址
	 */
	public static String PORTRAIT_SAVED_PATH;
	/**
	 * 商品封面保存地址
	 */
	public static String PRODUCT_COVER_SAVED_PATH;
	/**
	 * 商品图片保存地址
	 */
	public static String PRODUCT_PICTURES_SAVED_PATH;
	/**
	 * 使用心得图片临时存放目录
	 */
	public static String COMMENT_PICTURE_TMP_PATH;
	/**
	 * 使用心得图片存放目录
	 */
	public static String COMMENT_PICTURE_SAVED_PATH;
	/**
	 * 使用心得图片上传格式限制 
	 */
	public static String COMMENT_PICTURE_FORMAT;
	/**
	 * 单张图片大小限制
	 */
	public static Long COMMENT_PICTURE_SIZE;
	/**
	 * 分页查询每页记录数
	 */
	public static Integer NUMBER_PER_PAGE;
	/**
	 * 邮箱账号
	 */
	public static String MAIL_ACCOUNT;
	/**
	 * 邮箱授权码
	 */
	public static String MAIL_AUTH_CODE;

	@Value("${app.root}")
	public void setAppRoot(String appRoot) {
		AppSetting.APP_ROOT = appRoot;
	}
	@Value("${captcha.login.char.length}")
	public void setCaptchaLoginCodeCount(int captchaLength) {
		AppSetting.CAPTCHA_LOGIN_CHAR_LENGTH = captchaLength;
	}

	@Value("${captcha.login.image.width}")
	public void setCaptchaLoginImageWidth(int captchaImageWidth) {
		AppSetting.CAPTCHA_LOGIN_IMAGE_WIDTH = captchaImageWidth;
	}

	@Value("${captcha.login.image.height}")
	public void setCaptchaLoginImageHeight(int captchaImageHeight) {
		AppSetting.CAPTCHA_LOGIN_IMAGE_HEIGHT = captchaImageHeight;
	}

	@Value("${captcha.sms.number.length}")
	public void setCaptchaSmsNumberLength(int captchaLength) {
		AppSetting.CAPTCHA_SMS_NUMBER_LENGTH = captchaLength;
	}

	@Value("${captcha.sms.timeout}")
	public void setCaptchaSmsTimeout(long captchaSmsTimeout) {
		AppSetting.CAPTCHA_SMS_TIMEOUT = captchaSmsTimeout;
	}

	@Value("${miaodi.sms.request.url}")
	public void setSmsRequestUrl(String smsRequestUrl) {
		AppSetting.MIAODI_SMS_RQUEST_URL = smsRequestUrl;
	}

	@Value("${miaodi.account.sid}")
	public void setMiaodiAccountSid(String accountSid) {
		AppSetting.MIAODI_ACCOUNT_SID = accountSid;
	}

	@Value("${miaodi.auth.token}")
	public void setMiaodiAuthToken(String miaodiAuthToken) {
		AppSetting.MIAODI_AUTH_TOKEN = miaodiAuthToken;
	}

	@Value("${miaodi.sms.template.id}")
	public void setMiaodiSmsTemplateId(String smsTemplateId) {
		AppSetting.MIAODI_SMS_TEMPLATE_ID = smsTemplateId;
	}

	@Value("${user.password.salt.length}")
	public void setUserPasswordSaltLength(int passwordSaltLength) {
		AppSetting.USER_PASSWORD_SALT_LENGTH = passwordSaltLength;
	}
	
	@Value("${user.portrait.saved.path}")
	public void setPortraitSavedPath(String savedPath) {
		AppSetting.PORTRAIT_SAVED_PATH = savedPath;
	}
	
	@Value("${product.cover.saved.path}")
	public void setProductCoverSavedPath(String savedPath) {
		AppSetting.PRODUCT_COVER_SAVED_PATH = savedPath;
	}
	
	@Value("${product.picture.saved.path}")
	public void setProductPictureSavedPath(String savedPath) {
		AppSetting.PRODUCT_PICTURES_SAVED_PATH = savedPath;
	}
	
	@Value("${comment.picture.tmp.path}")
	public void setCommentPictureTmpPath(String tmpPath) {
		AppSetting.COMMENT_PICTURE_TMP_PATH = tmpPath;
	}
	
	@Value("${comment.picture.saved.path}")
	public void setCommentPictureSavedPath(String savedPath) {
		AppSetting.COMMENT_PICTURE_SAVED_PATH = savedPath;
	}
	
	@Value("${comment.picture.format}")
	public void setCommentPictureFormat(String format) {
		AppSetting.COMMENT_PICTURE_FORMAT = format;
	}
	
	@Value("${comment.picture.size}")
	public void setCommentPictureSize(Long size) {
		AppSetting.COMMENT_PICTURE_SIZE = size;
	}
	
	@Value("${number.per.page}")
	public void setNuberPerPage(Integer number) {
		AppSetting.NUMBER_PER_PAGE = number;
	}
	
	@Value("${mail.qq.account}")
	public void setMailAccount(String mailAccount) {
		AppSetting.MAIL_ACCOUNT = mailAccount;
	}
	
	@Value("${mail.qq.auth.code}")
	public void setMailAuthCode(String mailAuthCode) {
		AppSetting.MAIL_AUTH_CODE = mailAuthCode;
	}
}