package gdou.laiminghai.ime.common.setting;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppSetting {
	
	/**
	 * 验证码数量
	 */
	public static int CAPTCHA_CODE_COUNT; 
	/**
	 * 验证码图片宽度
	 */
	public static int CAPTCHA_IMAGE_WIDTH;
	/**
	 * 验证码图片高度
	 */
	public static int CAPTCHA_IMAGE_HEIGHT;
	
	@Value("${captcha.code.count}") 
	public void setCaptchaCodeCount(int captchaCodeCount) {
		AppSetting.CAPTCHA_CODE_COUNT = captchaCodeCount;
	}
	
	@Value("${captcha.image.width}")
	public void setCaptchaImageWidth(int captchaImageWidth) {
		AppSetting.CAPTCHA_IMAGE_WIDTH = captchaImageWidth;
	}
	
	@Value("${captcha.image.height}")
	public void setCaptchaImageHeight(int captchaImageHeight) {
		AppSetting.CAPTCHA_IMAGE_HEIGHT = captchaImageHeight;
	}
}