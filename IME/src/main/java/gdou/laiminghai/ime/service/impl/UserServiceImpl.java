package gdou.laiminghai.ime.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.catalina.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import gdou.laiminghai.ime.common.exception.ServiceException;
import gdou.laiminghai.ime.common.exception.ServiceResultEnum;
import gdou.laiminghai.ime.common.setting.AppSetting;
import gdou.laiminghai.ime.common.statics.SkinTextureEnum;
import gdou.laiminghai.ime.common.task.EmailSendTask;
import gdou.laiminghai.ime.common.util.CaptchaUtil;
import gdou.laiminghai.ime.common.util.FileUtil;
import gdou.laiminghai.ime.common.util.RegexUtil;
import gdou.laiminghai.ime.dao.mapper.UserInfoMapper;
import gdou.laiminghai.ime.model.dto.SmsCaptchaResponseDTO;
import gdou.laiminghai.ime.model.entity.UserInfo;
import gdou.laiminghai.ime.model.vo.UserInfoVO;
import gdou.laiminghai.ime.model.vo.UserVO;
import gdou.laiminghai.ime.service.UserService;

/**
 * 用户业务实现类
 * @ClassName: UserServiceImpl
 * @author: laiminghai
 * @datetime: 2018年5月7日 上午10:06:39
 */
@Service
public class UserServiceImpl implements UserService {
	
	/**
	 * 日志记录
	 */
	private final static Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Autowired
	private TaskExecutor taskExecutor;

	@Override
	public void registerByPhone(UserVO userVO) {
		Map<String,Object> map = new HashMap<String,Object>();
		String phone = userVO.getPhone();
		//注册手机号是否为空
		if(StringUtils.isBlank(phone) || 
				!RegexUtil.checkPhone(phone)) {
			throw new ServiceException(ServiceResultEnum.USER_PHONE_INVALID);
		}
		//当前手机号是否已被注册
		map.put("phone", phone);
		UserInfo userInfo = userInfoMapper.selectByCondition(map);
		if(userInfo != null) {
			logger.info("手机号"+phone+"已被注册！");
			throw new ServiceException(ServiceResultEnum.USER_REGISTER_PHONE_BE_USED);
		}
		//密码加密
		//随机生成加密盐
		String passwrodSalt = CaptchaUtil.getRandomCharCaptcha(AppSetting.USER_PASSWORD_SALT_LENGTH);
		//密码加密
		String passwordCodec = DigestUtils.md5Hex(userVO.getPassword()+passwrodSalt);
		//保存用户信息
		UserInfo userInfoPO = new UserInfo();
		userInfoPO.setPhone(phone);
		userInfoPO.setPassword(passwordCodec);
		userInfoPO.setPasswordSalt(passwrodSalt);
		userInfoPO.setRegisterTime(new Date());
		userInfoMapper.insert(userInfoPO);
		//生成默认用户名
		String defaultUserName = "IME用户"+userInfoPO.getUserId();
		userInfoPO.setUserName(defaultUserName);
		//更新用户名至数据库
		userInfoMapper.updateByPrimaryKey(userInfoPO);
	}

	@Override
	public UserInfoVO loginByAccount(UserVO userVO) {
		Map<String,Object> map = new HashMap<String,Object>();
		//账号为手机号
		if(RegexUtil.checkMobile(userVO.getAccount())) {
			map.put("phone", userVO.getAccount());
		}else if(RegexUtil.checkEmail(userVO.getAccount())) {//账号为邮箱
			map.put("email", userVO.getAccount());
		}else {
			throw new ServiceException(ServiceResultEnum.USER_ACCOUNT_NAME_NOT_ALLOWED);
		}
		//用户密码为空
		if(StringUtils.isBlank(userVO.getPassword())) {
			throw new ServiceException(ServiceResultEnum.USER_PASSWORD_EMPTY);
		}
		//查找用户信息
		UserInfo userInfo = userInfoMapper.selectByCondition(map);
		//用户不存在
		if(userInfo == null) {
			throw new ServiceException(ServiceResultEnum.USER_NOT_EXIST);
		}
		//用户密码校验
		String password = DigestUtils.md5Hex(userVO.getPassword()+userInfo.getPasswordSalt());
		if(!password.equals(userInfo.getPassword())) {
			throw new ServiceException(ServiceResultEnum.USER_ACCOUNT_PASSWORD_NOT_MATCH);
		}
		return userInfo2UserInfoVO(userInfo);
	}

	@Override
	public UserInfoVO loginBySmsCaptcha(UserVO userVO) {
		Map<String,Object> map = new HashMap<String,Object>();
		//用户手机号为空
		if(StringUtils.isBlank(userVO.getPhone()) || 
				!RegexUtil.checkPhone(userVO.getPhone())) {
			throw new ServiceException(ServiceResultEnum.USER_PHONE_INVALID);
		}
		map.put("phone", userVO.getPhone());
		// 查找用户信息
		UserInfo userInfoPO = userInfoMapper.selectByCondition(map);
		// 用户不存在，则自动注册
		if (userInfoPO == null) {
			userInfoPO = new UserInfo();
			userInfoPO.setPhone(userVO.getPhone());
			userInfoPO.setRegisterTime(new Date());
			userInfoMapper.insert(userInfoPO);
			//生成默认用户名
			String defaultUserName = "IME用户"+userInfoPO.getUserId();
			userInfoPO.setUserName(defaultUserName);
			//更新用户名至数据库
			userInfoMapper.updateByPrimaryKey(userInfoPO);
		}
		return userInfo2UserInfoVO(userInfoPO);
	}
	
	@Override
	public UserInfoVO getUserInfoById(Long userId) {
		//构造查询参数
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		// 查找用户信息
		UserInfo userInfoPO = userInfoMapper.selectByCondition(map);
		//用户不存在
		if(userInfoPO == null) {
			throw new ServiceException(ServiceResultEnum.USER_NOT_EXIST);
		}
		return userInfo2UserInfoVO(userInfoPO);
	}

	@Override
	public void updateUserInfo(UserInfoVO userInfoVO) {
		//无效动作
		if(userInfoVO == null || 
				userInfoVO.getUserId() == null) {
			throw new ServiceException(ServiceResultEnum.USER_INVALID_ACTION);
		}
		UserInfo userInfoPO = userInfoMapper.selectByPrimaryKey(userInfoVO.getUserId());
		//用户不存在
		if(userInfoPO == null) {
			throw new ServiceException(ServiceResultEnum.USER_NOT_EXIST);
		}
		//设置新值
		userInfoPO.setNickname(userInfoVO.getNickname());
		userInfoPO.setGender(userInfoVO.getGender());
		userInfoPO.setIntroduction(userInfoVO.getIntroduction());
		if(userInfoVO.getArea() != null) {
			userInfoPO.setAreaId(userInfoVO.getArea().getAreaId());
		}
		//更新至数据库
		userInfoMapper.updateByPrimaryKey(userInfoPO);
	}

	@Override
	public String updateUserPortrait(Long userId , MultipartFile portrait, String savedPath) {
		logger.debug("头像保存的路径："+savedPath);
		//生成随机文件名
		String fileName = UUID.randomUUID().toString().replaceAll("-", "") + ".png";
		//保存图片
		boolean success = FileUtil.save(portrait, savedPath, fileName);
		//保存失败
		if(!success) {
			throw new ServiceException(ServiceResultEnum.USER_PORTRAIT_UPDATE_ERROR);
		}
		//更新用户头像地址
		String portraitPath = AppSetting.PORTRAIT_SAVED_PATH + fileName;
		UserInfo userInfoPO = userInfoMapper.selectByPrimaryKey(userId);
		String oldPortraitName = userInfoPO.getPortrait();
		userInfoPO.setPortrait(fileName);
		userInfoMapper.updateByPrimaryKey(userInfoPO);
		//从磁盘删除旧头像
		if(StringUtils.isNotBlank(oldPortraitName)) {
			FileUtil.delete(savedPath, oldPortraitName);
		}
		return portraitPath;
	}

	@Override
	public String sendCaptcha(UserVO userVO) {
		String captcha = "";
		Map<String,Object> map = new HashMap<>();
		UserInfo userInfo ;
		String account = userVO.getAccount();
		if(StringUtils.isBlank(account)) {
			throw new ServiceException(ServiceResultEnum.FORGET_PASSWORD_USER_NOT_EXIST);
		}
		//输入是手机号
		if(RegexUtil.checkPhone(account)) {
			map.put("phone", account);
			userInfo = userInfoMapper.selectByCondition(map);
			if(userInfo == null) {
				throw new ServiceException(ServiceResultEnum.FORGET_PASSWORD_USER_NOT_EXIST);
			}
			//发送手机验证码
			captcha = CaptchaUtil.getRandomNumberCaptcha(AppSetting.CAPTCHA_SMS_NUMBER_LENGTH);
			SmsCaptchaResponseDTO resutlDTO = CaptchaUtil.sendSmsCaptcha(account, captcha);
		}else if(RegexUtil.checkEmail(account)) {
			map.put("email", account);
			userInfo = userInfoMapper.selectByCondition(map);
			if(userInfo == null) {
				throw new ServiceException(ServiceResultEnum.FORGET_PASSWORD_USER_NOT_EXIST);
			}
			//发送邮箱验证码
			long timeout = AppSetting.CAPTCHA_SMS_TIMEOUT/(1000*60);
			captcha = CaptchaUtil.getRandomNumberCaptcha(AppSetting.CAPTCHA_SMS_NUMBER_LENGTH);
			taskExecutor.execute(new EmailSendTask(account, 
					"爱美丽 － 密码找回", "亲爱的"+
					userInfo.getNickname()
					+"<br/>你的验证码为："+captcha+"，请于"+timeout+"分钟内输入并验证"));
		}else {
			throw new ServiceException(ServiceResultEnum.FORGET_PASSWORD_ACCOUNT_NOT_ALLOWED);
		}
		return captcha;
	}

	@Override
	public void retrievePassword(UserVO userVO) {
		String account = userVO.getAccount();
		if(StringUtils.isBlank(account)) {
			throw new ServiceException(ServiceResultEnum.FORGET_PASSWORD_ACCOUNT_NOT_ALLOWED);
		}
		UserInfo userInfo ;
		Map<String,Object> map = new HashMap<>();
		//输入是手机号
		if(RegexUtil.checkPhone(account)) {
			map.put("phone", account);
			userInfo = userInfoMapper.selectByCondition(map);
			if(userInfo == null) {
				throw new ServiceException(ServiceResultEnum.FORGET_PASSWORD_USER_NOT_EXIST);
			}
		}else if(RegexUtil.checkEmail(account)) {
			map.put("email", account);
			userInfo = userInfoMapper.selectByCondition(map);
			if(userInfo == null) {
				throw new ServiceException(ServiceResultEnum.FORGET_PASSWORD_USER_NOT_EXIST);
			}
		}else {
			throw new ServiceException(ServiceResultEnum.FORGET_PASSWORD_ACCOUNT_NOT_ALLOWED);
		}
		//密码加密
		//随机生成加密盐
		String passwordSalt = CaptchaUtil.getRandomCharCaptcha(AppSetting.USER_PASSWORD_SALT_LENGTH);
		//密码加密
		String passwordCodec = DigestUtils.md5Hex(userVO.getPassword()+passwordSalt);
		//更改密码
		userInfo.setPasswordSalt(passwordSalt);
		userInfo.setPassword(passwordCodec);
		userInfoMapper.updateByPrimaryKey(userInfo);
	}

	/**
	 * PO转VO
	 * @param userInfo
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月9日 上午10:07:57
	 */
	private UserInfoVO userInfo2UserInfoVO(UserInfo userInfo) {
		UserInfoVO userInfoVO = new UserInfoVO();
		userInfoVO.setUserId(userInfo.getUserId());
		userInfoVO.setUserName(userInfo.getUserName());
		userInfoVO.setNickname(userInfo.getNickname());
		userInfoVO.setGender(userInfo.getGender());
		//获取用户肤质
		if(StringUtils.isNotBlank(userInfo.getSkinTexture())) {
			SkinTextureEnum skinTexture = SkinTextureEnum.of(userInfo.getSkinTexture());
			if(skinTexture != null) {
				userInfoVO.setSkinTexture(skinTexture.getName());
			}
		}
		//获取用户年龄
		if(userInfo.getBornYear() != null) {
			userInfoVO.setAge(Calendar.getInstance().
				get(Calendar.YEAR)-userInfo.getBornYear());
		}
		
		userInfoVO.setIntroduction(userInfo.getIntroduction());
		userInfoVO.setPortrait(AppSetting.APP_ROOT+AppSetting.PORTRAIT_SAVED_PATH+userInfo.getPortrait());
		userInfoVO.setPhone(userInfo.getPhone());
		userInfoVO.setEmail(userInfo.getEmail());
		userInfoVO.setMembershipPoint(userInfo.getMembershipPoint());
		userInfoVO.setMemberLevel(userInfo.getMemberLevel());
		userInfoVO.setArea(userInfo.getArea());
		return userInfoVO;
	}
}
