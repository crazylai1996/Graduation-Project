package gdou.laiminghai.ime.service;

import java.util.List;

import gdou.laiminghai.ime.model.entity.CosmeticClass;

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
}
