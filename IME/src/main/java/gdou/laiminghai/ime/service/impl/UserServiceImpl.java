package gdou.laiminghai.ime.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.catalina.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gdou.laiminghai.ime.common.exception.ServiceException;
import gdou.laiminghai.ime.common.exception.ServiceResultEnum;
import gdou.laiminghai.ime.common.setting.AppSetting;
import gdou.laiminghai.ime.common.util.CaptchaUtil;
import gdou.laiminghai.ime.common.util.RegexUtil;
import gdou.laiminghai.ime.dao.mapper.UserInfoMapper;
import gdou.laiminghai.ime.model.entity.UserInfo;
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

	@Override
	public void registerByPhone(UserVO userVO) {
		Map<String,Object> map = new HashMap<String,Object>();
		String phone = userVO.getPhone();
		//注册手机号是否为空
		if(StringUtils.isBlank(phone)) {
			logger.warn("注册手机为空！");
			return ;
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
	public UserInfo loginByAccount(UserVO userVO) {
		Map<String,Object> map = new HashMap<String,Object>();
		//账号为手机号
		if(RegexUtil.checkMobile(userVO.getAccount())) {
			map.put("phone", userVO.getAccount());
		}else if(RegexUtil.checkEmail(userVO.getAccount())) {//账号为邮箱
			map.put("email", userVO.getAccount());
		}else {
			throw new ServiceException(ServiceResultEnum.USER_ACCOUNT_NAME_NOT_ALLOWED);
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
		return userInfo;
	}
}
