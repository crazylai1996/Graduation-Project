package gdou.laiminghai.ime.service;

import java.util.List;

import gdou.laiminghai.ime.model.entity.CosmeticClass;
import gdou.laiminghai.ime.model.entity.UserFollowClass;
import gdou.laiminghai.ime.model.vo.SelectItemVO;

/**
 * 化妆品分类业务接口
 * @ClassName: CosmeticClassService
 * @author: laiminghai
 * @datetime: 2018年5月15日 下午11:33:13
 */
public interface CosmeticClassService {
	/**
	 * 查找化妆品品所有分类
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月15日 下午11:34:46
	 */
	List<CosmeticClass> findAllClass();
	/**
	 * 查找化妆品子分类
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月15日 下午11:35:35
	 */
	List<CosmeticClass> findChildCosmeticClass(Integer parentClassId);
	/**
	 * 查询用户关注的分类
	 * @param userId
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月22日 上午8:01:21
	 */
	List<CosmeticClass> findFollowedClasses(Long userId);
	/**
	 * 取消关注分类
	 * @param userId
	 * @param classId
	 * @author: laiminghai
	 * @datetime: 2018年5月22日 上午8:51:27
	 */
	void unfollowClass(Long userId,Integer classId);
	
	/**
	 * 查询分类选择
	 * @param userId
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月22日 上午10:39:05
	 */
	List<SelectItemVO> findClassSelectItems(Long userId);
	
	/**
	 * 关注更多分类
	 * @param userId
	 * @param classIds
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月22日 上午9:10:54
	 */
	void followMoreClasses(Long userId,Integer [] classIds);
}
