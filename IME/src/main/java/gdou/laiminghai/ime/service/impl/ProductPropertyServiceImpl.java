package gdou.laiminghai.ime.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gdou.laiminghai.ime.dao.mapper.ProductPropertyMapper;
import gdou.laiminghai.ime.model.entity.ProductProperty;
import gdou.laiminghai.ime.model.vo.SelectItemVO;
import gdou.laiminghai.ime.service.ProductPropertyService;

/**产品属性业务实现类
 * 
 * @ClassName: ProductPropertyServiceImpl
 * @author: laiminghai
 * @datetime: 2018年5月15日 下午3:53:42
 */
@Service
public class ProductPropertyServiceImpl implements ProductPropertyService{
	
	//日志记录
	private static final Logger logger = Logger.getLogger(ProductServiceImpl.class);
	
	@Autowired
	private ProductPropertyMapper productPropertyMapper;
	
	@Override
	public List<SelectItemVO> getAllProductProperties() {
		List<SelectItemVO> selectOptions = new ArrayList<>();
		List<ProductProperty> properties = productPropertyMapper.selectAll();
		for (ProductProperty productProperty : properties) {
			selectOptions.add(toSelectOption(productProperty));
		}
		return selectOptions;
	}

	/**
	 * PO=>VO
	 * @param productProperty
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月15日 下午3:39:47
	 */
	private SelectItemVO toSelectOption(ProductProperty productProperty) {
		SelectItemVO selectOption = new SelectItemVO();
		selectOption.setCode(String.valueOf(productProperty.getPropertyId()));
		selectOption.setName(productProperty.getPropertyName());
		return selectOption;
	}
}
