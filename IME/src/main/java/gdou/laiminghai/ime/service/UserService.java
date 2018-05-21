package gdou.laiminghai.ime.service;

import org.springframework.web.multipart.MultipartFile;

import gdou.laiminghai.ime.model.dto.ResultDTO;
import gdou.laiminghai.ime.model.vo.UserInfoVO;
import gdou.laiminghai.ime.model.vo.UserVO;

/**
 * 用户业务接口
 * 
 * @ClassName: UserService
 * @author: laiminghai
 * @datetime: 2018年5月7日 上午10:06:28
 */
public interface UserService {
	/**
	 * 通过手机号注册
	 * 
	 * @param userVO
	 * @author: laiminghai
	 * @datetime: 2018年5月7日 上午10:08:51
	 */
	void registerByPhone(UserVO userVO);

	/**
	 * 用户密码登录
	 * 
	 * @param userVO
	 * @author: laiminghai
	 * @datetime: 2018年5月7日 下午9:19:19
	 */
	UserInfoVO loginByAccount(UserVO userVO);

	/**
	 * 手机号快捷登录
	 * 
	 * @param userVO
	 * @author: laiminghai
	 * @datetime: 2018年5月8日 上午1:38:13
	 */
	UserInfoVO loginBySmsCaptcha(UserVO userVO);

	/**
	 * 根据用户ID查找用户信息
	 * 
	 * @param userId
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月9日 下午1:13:59
	 */
	UserInfoVO getUserInfoById(Long userId);

	/**
	 * 更新用户信息
	 * 
	 * @param userInfoVO
	 * @author: laiminghai
	 * @datetime: 2018年5月9日 下午11:00:10
	 */
	void updateUserInfo(UserInfoVO userInfoVO);

	/**
	 * 更新用户头像
	 * 
	 * @param portrait
	 * @param savedPath
	 * @author: laiminghai
	 * @datetime: 2018年5月10日 上午12:59:07
	 */
	String updateUserPortrait(Long userId, MultipartFile portrait, String savedPath);
	
	/**
	 * 忘记密码 - 发送验证码
	 * @param userVO
	 * @author: laiminghai
	 * @datetime: 2018年5月20日 上午1:07:07
	 */
	String sendCaptcha(UserVO userVO);
	
	/**
	 * 忘记密码 - 重置密码
	 * @param userVO
	 * @author: laiminghai
	 * @datetime: 2018年5月20日 上午8:37:07
	 */
	void retrievePassword(UserVO userVO);
	
	/**
	 * 修改密码
	 * @param userVO
	 * @author: laiminghai
	 * @datetime: 2018年5月20日 下午4:37:39
	 */
	void modifyPassword(UserVO userVO);
	
	/**
	 * 发送身份验证码
	 * @param account
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月20日 下午10:20:21
	 */
	String sendAuthCaptcha(String account);
	
	/**
	 * 更改账户设置
	 * @param userVO
	 * @author: laiminghai
	 * @datetime: 2018年5月21日 上午1:32:15
	 */
	void updateAccountSetting(UserVO userVO);
	
	/**
	 * 关注用户
	 * @param userId
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月21日 下午7:22:14
	 */
	ResultDTO followUser(Long userId,Long followedUserId);
	
	/**
	 * 取消关注用户
	 * @param userId
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月21日 下午7:22:40
	 */
	ResultDTO unfollowUser(Long userId,Long followedUserId);
	
	/**
	 * 判断当前用户是否已被关注
	 * @param userId
	 * @param followedUserId
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月21日 下午7:52:28
	 */
	boolean isFollowedUser(Long userId,Long followedUserId);
}
