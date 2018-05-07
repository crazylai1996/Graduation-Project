package gdou.laiminghai.ime.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

import gdou.laiminghai.ime.common.exception.ServiceException;
import gdou.laiminghai.ime.common.exception.ServiceResultEnum;
import gdou.laiminghai.ime.common.setting.AppSetting;
import gdou.laiminghai.ime.common.statics.Constant;
import gdou.laiminghai.ime.common.util.CaptchaUtil;
import gdou.laiminghai.ime.common.util.RegexUtil;
import gdou.laiminghai.ime.common.util.ResultDTOUtil;
import gdou.laiminghai.ime.model.dto.ResultDTO;
import gdou.laiminghai.ime.model.dto.SmsCaptchaResponseDTO;
import gdou.laiminghai.ime.model.entity.UserInfo;
import gdou.laiminghai.ime.model.vo.UserVO;
import gdou.laiminghai.ime.service.UserService;

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

	/**
	 *  日志记录
	 */
	private final static Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private UserService userService;

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
		if(StringUtils.isBlank(phone) || 
				!RegexUtil.checkPhone(phone)) {
			throw new ServiceException(ServiceResultEnum.USER_PHONE_INVALID);
		}
		String smsCaptcha = CaptchaUtil.getRandomNumberCaptcha(AppSetting.CAPTCHA_SMS_NUMBER_LENGTH);
		// 短信验证码存入session
		session.setAttribute(Constant.WEB_PRE_KEY_SMS_CAPTCHA + phone, smsCaptcha);
		// 验证码获取时间存入session
		session.setAttribute(Constant.WEB_PRE_KEY_SMS_CAPTCHA_TIME + phone, new Date().getTime());
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
		logger.debug("表单参数："+userVO.toString());
		HttpSession session = request.getSession();
		// 验证码
		String smsCaptcha = (String) session.
				getAttribute(Constant.WEB_PRE_KEY_SMS_CAPTCHA + userVO.getPhone());
		//验证码未获取
		if(StringUtils.isEmpty(smsCaptcha)) {
			throw new ServiceException(ServiceResultEnum.CAPTCHA_SMS_NOT_EXIST);
		}
		// 验证码生成时间
		long smsCaptchaTime = (long) session.
				getAttribute(Constant.WEB_PRE_KEY_SMS_CAPTCHA_TIME + userVO.getPhone());
		// 验证码生成时隔
		long distanceTime = new Date().getTime() - smsCaptchaTime;
		//验证码失效
		if (distanceTime > AppSetting.CAPTCHA_SMS_TIMEOUT) {
			throw new ServiceException(ServiceResultEnum.CAPTCHA_SMS_TIMEOUT);
		}
		//验证码不匹配
		if(StringUtils.isBlank(userVO.getSmsCaptcha()) || 
				!userVO.getSmsCaptcha().equals(smsCaptcha)) {
			throw new ServiceException(ServiceResultEnum.CAPTCHA_SMS_NOT_MATCH);
		}
		//注册
		userService.registerByPhone(userVO);
		//从session移除短信验证码
		session.removeAttribute(Constant.WEB_PRE_KEY_SMS_CAPTCHA + userVO.getPhone());
		session.removeAttribute(Constant.WEB_PRE_KEY_SMS_CAPTCHA_TIME + userVO.getPhone());
		return ResultDTOUtil.success(null);
	}
	
	/**
	 * 用户账号登录
	 * @param userVO
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月7日 下午4:58:44
	 */
	@ResponseBody
	@RequestMapping("/accountLogin.do")
	public ResultDTO accountLogin(UserVO userVO) {
		logger.debug("表单参数："+userVO.toString());
		HttpSession session = request.getSession();
		//获取验证码
		String imageCaptcha = (String)session.getAttribute(Constant.WEB_KEY_IMAGE_CAPTCHA);
		//图片验证码不匹配
		if(StringUtils.isBlank(imageCaptcha) || 
				!imageCaptcha.equalsIgnoreCase(userVO.getImageCaptcha())) {
			throw new ServiceException(ServiceResultEnum.CAPTCHA_IMAGE_NOT_MATCH);
		}
		//登录
		UserInfo userInfo = userService.loginByAccount(userVO);
		//当前用户信息存入session
		Map<String,Object> userInfoMap = new HashMap<String,Object>();
		userInfoMap.put("userName", userInfo.getUserName());
		session.setAttribute("userInfo", userInfoMap);
		return ResultDTOUtil.success(null);
	}
	
	/**
	 * 用户手机快捷登录
	 * @param userVO
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月8日 上午1:19:41
	 */
	@ResponseBody
	@RequestMapping("/phoneLogin.do")
	public ResultDTO phoneLogin(UserVO userVO) {
		logger.debug("请求参数："+userVO.toString());
		HttpSession session = request.getSession();
		// 验证码
		String smsCaptcha = (String) session.
				getAttribute(Constant.WEB_PRE_KEY_SMS_CAPTCHA + userVO.getPhone());
		//验证码未获取
		if(StringUtils.isEmpty(smsCaptcha)) {
			throw new ServiceException(ServiceResultEnum.CAPTCHA_SMS_NOT_EXIST);
		}
		// 验证码生成时间
		long smsCaptchaTime = (long) session.
				getAttribute(Constant.WEB_PRE_KEY_SMS_CAPTCHA_TIME + userVO.getPhone());
		// 验证码生成时隔
		long distanceTime = new Date().getTime() - smsCaptchaTime;
		//验证码失效
		if (distanceTime > AppSetting.CAPTCHA_SMS_TIMEOUT) {
			throw new ServiceException(ServiceResultEnum.CAPTCHA_SMS_TIMEOUT);
		}
		//验证码不匹配
		if(StringUtils.isBlank(userVO.getSmsCaptcha()) || 
				!userVO.getSmsCaptcha().equals(smsCaptcha)) {
			throw new ServiceException(ServiceResultEnum.CAPTCHA_SMS_NOT_MATCH);
		}
		// 快捷登录
		UserInfo userInfo = userService.loginBySmsCaptcha(userVO);
		// 当前用户信息存入session
		Map<String, Object> userInfoMap = new HashMap<String, Object>();
		userInfoMap.put("userName", userInfo.getUserName());
		session.setAttribute("userInfo", userInfoMap);
		return ResultDTOUtil.success(null);
	}
	
	/**
	 * 退出登录
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月8日 上午1:13:13
	 */
	@RequestMapping("/logout")
	public String logout() {
		HttpSession session = request.getSession();
		session.removeAttribute("userInfo");
		return "redirect:/index.html";
	}
}
