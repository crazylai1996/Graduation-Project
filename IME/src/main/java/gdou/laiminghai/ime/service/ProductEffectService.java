package gdou.laiminghai.ime.service;

import java.util.List;

import gdou.laiminghai.ime.model.vo.SelectItemVO;

/**
 * 产品功效业务接口
 * @ClassName: ProductEffectService
 * @author: laiminghai
 * @datetime: 2018年5月15日 下午4:46:29
 */
public interface ProductEffectService {
	/**
	 * 获取所有产品功效列表
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月15日 下午4:47:13
	 */
	List<SelectItemVO> getAllProductEffects();
}
