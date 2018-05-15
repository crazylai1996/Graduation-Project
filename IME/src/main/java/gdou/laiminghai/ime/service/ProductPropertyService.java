package gdou.laiminghai.ime.service;

import java.util.List;

import gdou.laiminghai.ime.model.vo.SelectItemVO;

/**
 * 产品属性业务接口
 * @ClassName: ProductPropertyService
 * @author: laiminghai
 * @datetime: 2018年5月15日 下午3:33:51
 */
public interface ProductPropertyService {
	/**
	 * 获取所有产品属性
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月15日 下午3:53:58
	 */
	List<SelectItemVO> getAllProductProperties();
}
