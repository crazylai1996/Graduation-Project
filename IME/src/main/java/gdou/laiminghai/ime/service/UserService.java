package gdou.laiminghai.ime.service;

import gdou.laiminghai.ime.model.entity.UserInfo;
import gdou.laiminghai.ime.model.vo.UserVO;

/**
 * 用户业务接口
 * @ClassName: UserService
 * @author: laiminghai
 * @datetime: 2018年5月7日 上午10:06:28
 */
public interface UserService {
	/**
	 * 通过手机号注册
	 * @param userVO
	 * @author: laiminghai
	 * @datetime: 2018年5月7日 上午10:08:51
	 */
	void registerByPhone(UserVO userVO);
	/**
	 * 用户密码登录
	 * @param userVO
	 * @author: laiminghai
	 * @datetime: 2018年5月7日 下午9:19:19
	 */
	UserInfo loginByAccount(UserVO userVO);
}
