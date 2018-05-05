package gdou.laiminghai.ime.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import gdou.laiminghai.ime.common.setting.AppSetting;
import gdou.laiminghai.ime.common.util.CaptchaUtil;

/**
 * 登录验证码生成
 * @ClassName: CaptchaController
 * @author: laiminghai
 * @datetime: 2018年5月5日 下午6:48:52
 */
@Controller
public class CaptchaController {

	private static Logger logger = Logger.getLogger(CaptchaController.class);
	
	/**
	 * 获取登录验证码，返回验证码图片
	 * @param response
	 * @param session
	 * @author: laiminghai
	 * @datetime: 2018年5月5日 下午6:57:43
	 */
	@RequestMapping("/security/getCaptchaImage.action")
	public void getCaptchaImage(HttpServletResponse response,HttpSession session) {
		//获取随机验证码
		String captcha = CaptchaUtil.getRandomCodes(AppSetting.CAPTCHA_CODE_COUNT);
		logger.debug(captcha);
		//验证码存入session
		session.setAttribute("captcha", captcha);
		
		//不作缓存
		response.setHeader("Expires", "-1");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setHeader("Pragma",  "no-cache"); 
		response.setContentType("image/jpeg");
		
		try {
			CaptchaUtil.renderCaptchaImage(captcha, 
					AppSetting.CAPTCHA_IMAGE_WIDTH, AppSetting.CAPTCHA_IMAGE_HEIGHT, 
					response.getOutputStream());
		}catch(IOException e) {
			logger.error("获取验证码出错：",e);
		}
	}
}
