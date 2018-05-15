package gdou.laiminghai.ime.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gdou.laiminghai.ime.dao.mapper.ProductBrandMapper;
import gdou.laiminghai.ime.model.entity.ProductBrand;
import gdou.laiminghai.ime.model.vo.SelectItemVO;
import gdou.laiminghai.ime.service.ProductBrandService;

/**
 * 产品品牌业务实现类
 * 
 * @ClassName: ProductBrandServiceImpl
 * @author: laiminghai
 * @datetime: 2018年5月15日 下午4:36:22
 */
@Service
public class ProductBrandServiceImpl implements ProductBrandService {

	// 日志记录
	private static final Logger logger = Logger.getLogger(ProductBrandServiceImpl.class);
	
	@Autowired
	private ProductBrandMapper productBrandMapper;

	@Override
	public List<SelectItemVO> getAllProductBrands() {
		List<SelectItemVO> selectOptions = new ArrayList<>();
		List<ProductBrand> productBrands = productBrandMapper.selectAll();
		for (ProductBrand productBrand : productBrands) {
			selectOptions.add(toSelectOption(productBrand));
		}
		return selectOptions;
	}

	/**
	 * PO=>VO
	 * @param productBrand
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月15日 下午4:37:59
	 */
	private SelectItemVO toSelectOption(ProductBrand productBrand) {
		SelectItemVO selectOption = new SelectItemVO();
		selectOption.setCode(String.valueOf(productBrand.getBrandId()));
		selectOption.setName(productBrand.getBrandName());
		return selectOption;
	}
}
