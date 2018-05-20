package gdou.laiminghai.ime.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import gdou.laiminghai.ime.common.exception.ServiceException;
import gdou.laiminghai.ime.common.exception.ServiceResultEnum;
import gdou.laiminghai.ime.common.setting.AppSetting;
import gdou.laiminghai.ime.common.statics.Constant;
import gdou.laiminghai.ime.common.statics.GenderEnum;
import gdou.laiminghai.ime.common.util.CaptchaUtil;
import gdou.laiminghai.ime.common.util.EnumUtil;
import gdou.laiminghai.ime.common.util.RegexUtil;
import gdou.laiminghai.ime.common.util.ResultDTOUtil;
import gdou.laiminghai.ime.model.dto.ResultDTO;
import gdou.laiminghai.ime.model.dto.SmsCaptchaResponseDTO;
import gdou.laiminghai.ime.model.entity.Area;
import gdou.laiminghai.ime.model.vo.SelectItemVO;
import gdou.laiminghai.ime.model.vo.UserInfoVO;
import gdou.laiminghai.ime.model.vo.UserVO;
import gdou.laiminghai.ime.service.AreaService;
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
	
	@Autowired
	private AreaService areaService;

	/**
	 * ajax返回用户登录页面
	 * 
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月4日 下午8:42:59
	 */
	@RequestMapping("/login")
	public ModelAndView goAjaxLogin() {
		ModelAndView mav = new ModelAndView("user/login_or_signup");
		return mav;
	}
	
	/**
	 * 跳转到登录页面
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月19日 下午7:15:53
	 */
	@RequestMapping("/page/login")
	public ModelAndView goPageLogin() {
		ModelAndView mav = new ModelAndView("user/login");
		return mav;
	}
	
	/**
	 * 跳转到注册页面
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月19日 下午7:53:25
	 */
	@RequestMapping("/page/register")
	public ModelAndView goPageRegister() {
		ModelAndView mav = new ModelAndView("user/register");
		return mav;
	}
	
	/**
	 * 跳转到忘记密码页面
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月19日 下午10:25:12
	 */
	@RequestMapping("/page/forgetPssword")
	public ModelAndView goForgetPassword() {
		ModelAndView mav = new ModelAndView("user/forget_password");
		return mav;
	}
	
	/**
	 * 忘记密码更改密码
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月19日 下午10:27:41
	 */
	@RequestMapping("/page/forgetPssword/updatePassword")
	public ModelAndView goForgetPasswordStep2(UserVO userVO) {
		logger.debug("忘记密码表单参数："+userVO.toString());
		ModelAndView mav = new ModelAndView("user/forget_password_step2");
		//获取验证码
		HttpSession session = request.getSession();
		String imageCaptcha = (String)session.getAttribute(Constant.WEB_KEY_IMAGE_CAPTCHA);
		if(StringUtils.isNotBlank(userVO.getImageCaptcha()) && 
				!userVO.getImageCaptcha().equalsIgnoreCase(imageCaptcha)) {
			throw new ServiceException(ServiceResultEnum.FORGET_PASSWORD_CAPTCHA_ERROR);
		}
		String captcha = userService.sendCaptcha(userVO);
		// 短信验证码存入session
		session.setAttribute(Constant.WEB_PRE_KEY_FORGET_PASSWORD_CAPTCHA + userVO.getAccount(), captcha);
		// 验证码获取时间存入session
		session.setAttribute(Constant.WEB_PRE_KEY_FORGET_PASSWORD_CAPTCHA_TIME + 
				userVO.getAccount(), new Date().getTime());
		mav.addObject("account",userVO.getAccount());
		mav.addObject("actionMessage", "向你绑定的" + userVO.getAccount() +"发送验证码");
		return mav;
	}
	
	/**
	 * 忘记密码-重发验证码
	 * @param userVO
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月20日 上午7:59:44
	 */
	@ResponseBody
	@RequestMapping("/forgetPssword/reSendCaptcha.do")
	public ResultDTO reSendCaptcha(UserVO userVO) {
		if(userVO == null || StringUtils.isBlank(userVO.getAccount())) {
			throw new ServiceException(ServiceResultEnum.FORGET_PASSWORD_ACCOUNT_NOT_ALLOWED);
		}
		HttpSession session = request.getSession();
		String captcha = userService.sendCaptcha(userVO);
		// 短信验证码存入session
		session.setAttribute(Constant.WEB_PRE_KEY_FORGET_PASSWORD_CAPTCHA + userVO.getAccount(), captcha);
		// 验证码获取时间存入session
		session.setAttribute(Constant.WEB_PRE_KEY_FORGET_PASSWORD_CAPTCHA_TIME + 
				userVO.getAccount(), new Date().getTime());
		return ResultDTOUtil.success(captcha);
	}
	
	/**
	 * 忘记密码-重置密码
	 * @param userVO
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月20日 上午8:27:49
	 */
	@ResponseBody
	@RequestMapping("/forgetPssword/resetPassword.do")
	public ResultDTO doForgetPasswordStep2(UserVO userVO) {
		if(userVO == null || StringUtils.isBlank(userVO.getAccount())) {
			throw new ServiceException(ServiceResultEnum.FORGET_PASSWORD_ACCOUNT_NOT_ALLOWED);
		}
		HttpSession session = request.getSession();
		// 验证码
		String captcha = (String) session.getAttribute(
				Constant.WEB_PRE_KEY_FORGET_PASSWORD_CAPTCHA + userVO.getAccount());
		// 验证码未获取
		if (StringUtils.isEmpty(captcha)) {
			throw new ServiceException(ServiceResultEnum.CAPTCHA_SMS_NOT_EXIST);
		}
		// 验证码生成时间
		long smsCaptchaTime = (long) session.getAttribute(
				Constant.WEB_PRE_KEY_FORGET_PASSWORD_CAPTCHA_TIME + userVO.getAccount());
		// 验证码生成时隔
		long distanceTime = new Date().getTime() - smsCaptchaTime;
		// 验证码失效
		if (distanceTime > AppSetting.CAPTCHA_SMS_TIMEOUT) {
			throw new ServiceException(ServiceResultEnum.CAPTCHA_SMS_TIMEOUT);
		}
		// 验证码不匹配
		if (StringUtils.isBlank(userVO.getSmsCaptcha()) ||
				!userVO.getSmsCaptcha().equals(captcha)) {
			throw new ServiceException(ServiceResultEnum.CAPTCHA_SMS_NOT_MATCH);
		}
		//更改密码
		userService.retrievePassword(userVO);
		return ResultDTOUtil.success(null);
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
		UserInfoVO userInfo = userService.loginByAccount(userVO);
		//当前用户信息存入session
		Map<String,Object> userInfoMap = new HashMap<String,Object>();
		userInfoMap.put("userId", userInfo.getUserId());
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
		UserInfoVO userInfo = userService.loginBySmsCaptcha(userVO);
		// 当前用户信息存入session
		Map<String, Object> userInfoMap = new HashMap<String, Object>();
		userInfoMap.put("userId", userInfo.getUserId());
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
	
	/**
	 * 跳转到更新头像页面
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月9日 上午12:06:14
	 */
	@RequestMapping("/updatePortrait")
	public ModelAndView goUpdatePortrait() {
		ModelAndView mav = new ModelAndView("user/update_portrait");
		return mav;
	}
	
	/**
	 * 头像更新
	 * @param file
	 * @param msg
	 * @param request
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月10日 上午12:52:39
	 */
	@ResponseBody
	@RequestMapping("/updatePortrait.do")
	public ResultDTO updatePortrait(@RequestParam(value="portrait",required=false)MultipartFile portrait) {
		// 获取用户登录信息
		HttpSession session = request.getSession();
		String savedPath = session.getServletContext().getRealPath("/"+AppSetting.PORTRAIT_SAVED_PATH);
		Map<String, Object> userInfoMap = (Map<String, Object>) session.getAttribute("userInfo");
		// 登录失效
		if (userInfoMap == null) {
			throw new ServiceException(ServiceResultEnum.USER_SESSION_TIMEOUT);
		}
		//获取用户ID
		Long userId = (Long)userInfoMap.get("userId");
		//头像更新
		String portraitPath = userService.updateUserPortrait(userId, portrait, savedPath);
		return ResultDTOUtil.success(portraitPath);
	}
	
	/**
	 * 获取用户信息
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月8日 下午11:44:50
	 */
	@RequestMapping("/info")
	public ModelAndView getUserInfo() {
		ModelAndView mav = new ModelAndView("user/user_info");
		//获取用户登录信息
		HttpSession session = request.getSession();
		Map<String, Object> userInfoMap = (Map<String, Object>) session.getAttribute("userInfo");
		//登录失效
		if(userInfoMap == null) {
			throw new ServiceException(ServiceResultEnum.USER_SESSION_TIMEOUT);
		}
		//性别选项获取
		mav.addObject("genderList",EnumUtil.toList(GenderEnum.class));
		//省份列表获取
		List<Area> provinceList = areaService.findAllProvinces();
		mav.addObject("provinceList",provinceList);
		//查找用户信息
		Long userId = (Long) userInfoMap.get("userId");
		
		UserInfoVO userInfoVO = userService.getUserInfoById(userId);
		//获取用户所在城市
		Area area = userInfoVO.getArea();
		if(area == null) {//默认
			mav.addObject("cityList", areaService.findChildAreas(provinceList.get(0).getAreaId()));
		}else {
			mav.addObject("cityList", areaService.findChildAreas(area.getParentAreaId()));
		}
		mav.addObject("userInfoVO", userInfoVO);
		return mav;
	}
	
	/**
	 * 更新用户信息
	 * @param userInfoVO
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月9日 下午10:55:32
	 */
	@ResponseBody
	@RequestMapping("/updateUserInfo.do")
	public ResultDTO updateUserInfo(UserInfoVO userInfoVO) {
		logger.debug("表单信息："+userInfoVO.toString());
		// 获取用户登录信息
		HttpSession session = request.getSession();
		Map<String, Object> userInfoMap = (Map<String, Object>) session.getAttribute("userInfo");
		// 登录失效
		if (userInfoMap == null) {
			throw new ServiceException(ServiceResultEnum.USER_SESSION_TIMEOUT);
		}
		//当前登录用户ID
		userInfoVO.setUserId((Long)userInfoMap.get("userId"));
		//更新
		userService.updateUserInfo(userInfoVO);
		return ResultDTOUtil.success(null);
	}
	
	/**
	 * 跳转到账号设置页面
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月20日 上午10:52:20
	 */
	@RequestMapping("/accountSetting")
	public ModelAndView goAccountSetting() {
		ModelAndView mav = new ModelAndView("user/account_setting");
		//获取用户登录信息
		HttpSession session = request.getSession();
		Map<String, Object> userInfoMap = (Map<String, Object>) session.getAttribute("userInfo");
		//登录失效
		if(userInfoMap == null) {
			throw new ServiceException(ServiceResultEnum.USER_SESSION_TIMEOUT);
		}
		//查找用户信息
		Long userId = (Long) userInfoMap.get("userId");
		
		UserInfoVO userInfoVO = userService.getUserInfoById(userId);
		mav.addObject("userInfoVO",userInfoVO);
		return mav;
	}
	
	/**
	 * 跳转到修改密码页面
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月20日 下午3:59:53
	 */
	@RequestMapping("/accountSetting/modifyPassword")
	public ModelAndView goModifyPassword() {
		ModelAndView mav = new ModelAndView("user/modify_password");
		return mav;
	}
	
	/**
	 * 更改密码
	 * @param userVO
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月20日 下午4:36:15
	 */
	@ResponseBody
	@RequestMapping("/accountSetting/modifyPassword.do")
	public ResultDTO modifyPassword(UserVO userVO) {
		// 获取用户登录信息
		HttpSession session = request.getSession();
		Map<String, Object> userInfoMap = (Map<String, Object>) session.getAttribute("userInfo");
		// 登录失效
		if (userInfoMap == null) {
			throw new ServiceException(ServiceResultEnum.USER_SESSION_TIMEOUT);
		}
		//表单信息为空
		if(userVO == null) {
			throw new ServiceException(ServiceResultEnum.USER_INVALID_ACTION);
		}		
		Long userId = (Long)userInfoMap.get("userId");
		userVO.setUserId(userId);
		userService.modifyPassword(userVO);
		session.removeAttribute("userInfo");
		return ResultDTOUtil.success(null);
	}
	
	/**
	 * 跳转到身份验证页面
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月20日 下午8:16:14
	 */
	@RequestMapping("/accountSetting/identityAuth")
	public ModelAndView goIdentityAuth(Integer type) {
		ModelAndView mav = new ModelAndView("user/identity_auth");
		// 获取用户登录信息
		HttpSession session = request.getSession();
		Map<String, Object> userInfoMap = (Map<String, Object>) session.getAttribute("userInfo");
		// 登录失效
		if (userInfoMap == null) {
			throw new ServiceException(ServiceResultEnum.USER_SESSION_TIMEOUT);
		}
		Long userId = (Long)userInfoMap.get("userId");
		UserInfoVO userInfoVO = userService.getUserInfoById(userId);
		String phone = userInfoVO.getPhone();
		String email = userInfoVO.getEmail();
		List<SelectItemVO> authWays = new ArrayList<>();
		//修改邮箱号
		if(type == 2) {
			authWays.add(new SelectItemVO(phone, "手机号验证"));
			if(StringUtils.isNotBlank(email)) {
				authWays.add(new SelectItemVO(email,"邮箱号验证"));
			}
		}else if(type == 1){
			authWays.add(new SelectItemVO(phone, "手机号验证"));
		}else {
			throw new ServiceException(ServiceResultEnum.USER_INVALID_ACTION);
		}
		mav.addObject("authWays",authWays);
		return mav;
	}
	
	/**
	 * 获取验证码
	 * @param userVO
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月20日 下午10:13:34
	 */
	@ResponseBody
	@RequestMapping("/accountSetting/sendAuthCpatha.do")
	public ResultDTO sendAuthCaptcha(UserVO userVO) {
		logger.debug("表单信息："+userVO.toString());
		// 获取用户登录信息
		HttpSession session = request.getSession();
		Map<String, Object> userInfoMap = (Map<String, Object>) session.getAttribute("userInfo");
		// 登录失效
		if (userInfoMap == null) {
			throw new ServiceException(ServiceResultEnum.USER_SESSION_TIMEOUT);
		}
		String imageCaptcha = (String) session.getAttribute(Constant.WEB_KEY_IMAGE_CAPTCHA);
		if (StringUtils.isNotBlank(userVO.getImageCaptcha())
				&& !userVO.getImageCaptcha().equalsIgnoreCase(imageCaptcha)) {
			throw new ServiceException(ServiceResultEnum.CAPTCHA_IMAGE_NOT_MATCH);
		}
		String captcha = userService.sendAuthCaptcha(userVO.getAccount());
		// 短信验证码存入session
		session.setAttribute(Constant.WEB_PRE_KEY_IDENTITY_AUTH_CAPTCHA + userVO.getAccount(), captcha);
		// 验证码获取时间存入session
		session.setAttribute(Constant.WEB_PRE_KEY_IDENTITY_AUTH_CAPTCHA_TIME + 
				userVO.getAccount(), new Date().getTime());
		return ResultDTOUtil.success(null);
	}
	
	/**
	 * 跳转到验证码输入页面
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月20日 下午9:42:35
	 */
	@RequestMapping("/accountSetting/captchaInput")
	public ModelAndView goAuthCaptchaInput(String account) {
		ModelAndView mav = new ModelAndView("user/identity_auth_captcha_input");
		mav.addObject("account", account);
		return mav;
	}
	
	/**
	 * 验证码验证
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月20日 下午11:06:26
	 */
	@ResponseBody
	@RequestMapping("/accountSetting/authCaptchaVerify.do")
	public ResultDTO verifyAuthCaptcha(UserVO userVO) {
		if(userVO == null) {
			throw new ServiceException(ServiceResultEnum.USER_INVALID_ACTION);
		}
		logger.debug("验证码验证表单："+userVO.toString());
		HttpSession session = request.getSession();
		Map<String, Object> userInfoMap = (Map<String, Object>) session.getAttribute("userInfo");
		// 登录失效
		if (userInfoMap == null) {
			throw new ServiceException(ServiceResultEnum.USER_SESSION_TIMEOUT);
		}
		Long userId = (Long)userInfoMap.get("userId");
		// 验证码
		String captcha = (String) session.getAttribute(
				Constant.WEB_PRE_KEY_IDENTITY_AUTH_CAPTCHA + userVO.getAccount());
		// 验证码未获取
		if (StringUtils.isEmpty(captcha)) {
			throw new ServiceException(ServiceResultEnum.CAPTCHA_SMS_NOT_EXIST);
		}
		// 验证码生成时间
		long smsCaptchaTime = (long) session.getAttribute(
				Constant.WEB_PRE_KEY_IDENTITY_AUTH_CAPTCHA_TIME + userVO.getAccount());
		// 验证码生成时隔
		long distanceTime = new Date().getTime() - smsCaptchaTime;
		// 验证码失效
		if (distanceTime > AppSetting.CAPTCHA_SMS_TIMEOUT) {
			throw new ServiceException(ServiceResultEnum.CAPTCHA_SMS_TIMEOUT);
		}
		// 验证码不匹配
		if (StringUtils.isBlank(userVO.getSmsCaptcha()) ||
				!userVO.getSmsCaptcha().equals(captcha)) {
			throw new ServiceException(ServiceResultEnum.CAPTCHA_SMS_NOT_MATCH);
		}
		session.setAttribute(Constant.WEB_PRE_KEY_IDENTITY_AUTH_PASS+userId, "1");
		return ResultDTOUtil.success(null);
	}
	
	/**
	 * 跳转到邮箱更改页面
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月21日 上午12:48:45
	 */
	@RequestMapping("/accountSetting/modifyEmail")
	public ModelAndView goModifyEmail() {
		ModelAndView mav = new ModelAndView("user/modify_email");
		return mav;
	}
	
	/**
	 * 绑定新手机号和邮箱，验证码获取
	 * @param account
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月21日 上午1:20:23
	 */
	@ResponseBody
	@RequestMapping("/accountSetting/sendBandNewCaptcha.do")
	public ResultDTO sendBandNewCaptcha(String account) {
		// 获取用户登录信息
		HttpSession session = request.getSession();
		Map<String, Object> userInfoMap = (Map<String, Object>) session.getAttribute("userInfo");
		// 登录失效
		if (userInfoMap == null) {
			throw new ServiceException(ServiceResultEnum.USER_SESSION_TIMEOUT);
		}
		String captcha = userService.sendAuthCaptcha(account);
		// 短信验证码存入session
		session.setAttribute(Constant.WEB_PRE_KEY_IDENTITY_AUTH_CAPTCHA + account, captcha);
		// 验证码获取时间存入session
		session.setAttribute(Constant.WEB_PRE_KEY_IDENTITY_AUTH_CAPTCHA_TIME + 
				account, new Date().getTime());
		return ResultDTOUtil.success(null);
	}
	
	/**
	 * 绑定新邮箱
	 * @param userVO
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月21日 上午1:28:06
	 */
	@ResponseBody
	@RequestMapping("/accountSetting/modifyEmail.do")
	public ResultDTO modifyEmail(String account,String smsCaptcha) {
		HttpSession session = request.getSession();
		Map<String, Object> userInfoMap = (Map<String, Object>) session.getAttribute("userInfo");
		// 登录失效
		if (userInfoMap == null) {
			throw new ServiceException(ServiceResultEnum.USER_SESSION_TIMEOUT);
		}
		Long userId = (Long)userInfoMap.get("userId");
		String authFlag = (String)session.getAttribute(Constant.WEB_PRE_KEY_IDENTITY_AUTH_PASS+userId);
		if(!"1".equals(authFlag)) {
			throw new ServiceException(ServiceResultEnum.USER_INVALID_ACTION);
		}
		UserInfoVO userInfoVO = userService.getUserInfoById(userId);
		if(userInfoVO == null) {
			throw new ServiceException(ServiceResultEnum.USER_INVALID_ACTION);
		}
		// 验证码
		String captcha = (String) session.getAttribute(
				Constant.WEB_PRE_KEY_IDENTITY_AUTH_CAPTCHA + account);
		// 验证码未获取
		if (StringUtils.isEmpty(captcha)) {
			throw new ServiceException(ServiceResultEnum.CAPTCHA_SMS_NOT_EXIST);
		}
		// 验证码生成时间
		long smsCaptchaTime = (long) session.getAttribute(
				Constant.WEB_PRE_KEY_IDENTITY_AUTH_CAPTCHA_TIME + account);
		// 验证码生成时隔
		long distanceTime = new Date().getTime() - smsCaptchaTime;
		// 验证码失效
		if (distanceTime > AppSetting.CAPTCHA_SMS_TIMEOUT) {
			throw new ServiceException(ServiceResultEnum.CAPTCHA_SMS_TIMEOUT);
		}
		// 验证码不匹配
		if (StringUtils.isBlank(smsCaptcha) ||
				!smsCaptcha.equals(captcha)) {
			throw new ServiceException(ServiceResultEnum.CAPTCHA_SMS_NOT_MATCH);
		}
		UserVO userVO = new UserVO();
		userVO.setUserId(userId);
		userVO.setEmail(account);
		userService.updateAccountSetting(userVO);
		session.removeAttribute(Constant.WEB_PRE_KEY_IDENTITY_AUTH_PASS+userId);
		return ResultDTOUtil.success(null);
	}
	
	/**
	 * 跳转到修改手机号页面
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月21日 上午7:05:03
	 */
	@RequestMapping("/accountSetting/modifyPhone")
	public ModelAndView goModifyPhone() {
		ModelAndView mav = new ModelAndView("user/modify_phone");
		return mav;
	}
	
	/**
	 * 修改手机号
	 * @param account
	 * @param smsCaptcha
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月21日 上午7:06:37
	 */
	@ResponseBody
	@RequestMapping("/accountSetting/modifyPhone.do")
	public ResultDTO modifyPhone(String account,String smsCaptcha) {
		HttpSession session = request.getSession();
		Map<String, Object> userInfoMap = (Map<String, Object>) session.getAttribute("userInfo");
		// 登录失效
		if (userInfoMap == null) {
			throw new ServiceException(ServiceResultEnum.USER_SESSION_TIMEOUT);
		}
		Long userId = (Long)userInfoMap.get("userId");
		String authFlag = (String)session.getAttribute(Constant.WEB_PRE_KEY_IDENTITY_AUTH_PASS+userId);
		if(!"1".equals(authFlag)) {
			throw new ServiceException(ServiceResultEnum.USER_INVALID_ACTION);
		}
		UserInfoVO userInfoVO = userService.getUserInfoById(userId);
		if(userInfoVO == null) {
			throw new ServiceException(ServiceResultEnum.USER_INVALID_ACTION);
		}
		// 验证码
		String captcha = (String) session.getAttribute(
				Constant.WEB_PRE_KEY_IDENTITY_AUTH_CAPTCHA + account);
		// 验证码未获取
		if (StringUtils.isEmpty(captcha)) {
			throw new ServiceException(ServiceResultEnum.CAPTCHA_SMS_NOT_EXIST);
		}
		// 验证码生成时间
		long smsCaptchaTime = (long) session.getAttribute(
				Constant.WEB_PRE_KEY_IDENTITY_AUTH_CAPTCHA_TIME + account);
		// 验证码生成时隔
		long distanceTime = new Date().getTime() - smsCaptchaTime;
		// 验证码失效
		if (distanceTime > AppSetting.CAPTCHA_SMS_TIMEOUT) {
			throw new ServiceException(ServiceResultEnum.CAPTCHA_SMS_TIMEOUT);
		}
		// 验证码不匹配
		if (StringUtils.isBlank(smsCaptcha) ||
				!smsCaptcha.equals(captcha)) {
			throw new ServiceException(ServiceResultEnum.CAPTCHA_SMS_NOT_MATCH);
		}
		UserVO userVO = new UserVO();
		userVO.setUserId(userId);
		userVO.setPhone(account);
		userService.updateAccountSetting(userVO);
		session.removeAttribute(Constant.WEB_PRE_KEY_IDENTITY_AUTH_PASS+userId);
		return ResultDTOUtil.success(null);
	}
}
