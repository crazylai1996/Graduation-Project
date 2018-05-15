package gdou.laiminghai.ime.service;

import java.util.List;

import gdou.laiminghai.ime.model.vo.SelectItemVO;

/**
 * 产品品牌业务接口
 * @ClassName: ProductBrandService
 * @author: laiminghai
 * @datetime: 2018年5月15日 下午4:34:46
 */
public interface ProductBrandService {
	/**
	 * 获取所有产品品牌
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月15日 下午4:35:24
	 */
	List<SelectItemVO> getAllProductBrands();
}
