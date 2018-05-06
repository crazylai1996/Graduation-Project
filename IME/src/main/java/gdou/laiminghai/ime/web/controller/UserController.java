package gdou.laiminghai.ime.web.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import gdou.laiminghai.ime.common.setting.AppSetting;
import gdou.laiminghai.ime.common.util.CaptchaUtil;
import gdou.laiminghai.ime.common.util.ResultDTOUtil;
import gdou.laiminghai.ime.model.dto.ResultDTO;
import gdou.laiminghai.ime.model.dto.SmsCaptchaResponseDTO;
import gdou.laiminghai.ime.model.vo.UserVO;

/**
 * 用户控制器
 * 
 * @ClassName: UserController
 * @author: laiminghai
 * @datetime: 2018年5月4日 下午8:42:21
 */
@Controller
@RequestMapping("/user")
public class UserController {

	// 日志记录
	private final static Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private HttpServletRequest request;

	/**
	 * 跳转到用户登录页面
	 * 
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月4日 下午8:42:59
	 */
	@RequestMapping("/login")
	public ModelAndView goLogin() {
		ModelAndView mav = new ModelAndView("login_or_signup");
		return mav;
	}

	/**
	 * 获取手机短信验证码
	 * 
	 * @param phone
	 *            手机号
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月6日 上午10:13:43
	 */
	@ResponseBody
	@RequestMapping("/getSmsCaptcha.do")
	public ResultDTO getSmsCaptcha(String phone) {
		HttpSession session = request.getSession();
		String smsCaptcha = CaptchaUtil.getRandomNumberCaptcha(AppSetting.CAPTCHA_SMS_NUMBER_LENGTH);
		// 短信验证码存入session
		session.setAttribute("smsCaptcha-" + phone, smsCaptcha);
		// 验证码获取时间存入session
		session.setAttribute("smsCaptcha-time-" + phone, new Date().getTime());
		// 发送短信验证码
		SmsCaptchaResponseDTO resutlDTO = CaptchaUtil.sendSmsCaptcha(phone, smsCaptcha);
		return ResultDTOUtil.success(resutlDTO);
	}

	/**
	 * 手机号快速注册
	 * 
	 * @param userVO
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月6日 下午9:37:10
	 */
	@ResponseBody
	@RequestMapping("/phoneRegister.do")
	public ResultDTO phoneRegister(UserVO userVO) {
		logger.debug(userVO.toString());
		HttpSession session = request.getSession(false);
		// 验证码
		String smsCaptcha = (String) session.getAttribute("smsCaptcha-" + userVO.getPhone());
		// 验证码生成时间
		long smsCaptchaTime = (long) session.getAttribute("smsCaptcha-time-" + userVO.getPhone());
		// 验证码生成时隔
		long distanceTime = new Date().getTime() - smsCaptchaTime;
		//判断验证码是否失效
		if (distanceTime <= AppSetting.CAPTCHA_SMS_TIMEOUT) {
			
		}else {
			
		}
		return ResultDTOUtil.success(null);
	}
}
