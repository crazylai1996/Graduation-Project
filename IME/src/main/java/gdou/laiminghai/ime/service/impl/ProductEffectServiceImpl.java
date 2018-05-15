package gdou.laiminghai.ime.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gdou.laiminghai.ime.dao.mapper.ProductEffectMapper;
import gdou.laiminghai.ime.model.entity.ProductEffect;
import gdou.laiminghai.ime.model.vo.SelectItemVO;
import gdou.laiminghai.ime.service.ProductEffectService;

/**
 * 产品功效业务实现类
 * 
 * @ClassName: ProductEffectServiceImpl
 * @author: laiminghai
 * @datetime: 2018年5月15日 下午4:48:23
 */
@Service
public class ProductEffectServiceImpl implements ProductEffectService {

	// 日志记录
	private static final Logger logger = Logger.getLogger(ProductServiceImpl.class);
	
	@Autowired
	private ProductEffectMapper productEffectMapper;

	@Override
	public List<SelectItemVO> getAllProductEffects() {
		List<SelectItemVO> selectOptions = new ArrayList<>();
		List<ProductEffect> effects = productEffectMapper.selectAll();
		for (ProductEffect productEffect : effects) {
			selectOptions.add(toSelectOption(productEffect));
		}
		return selectOptions;
	}

	/**
	 * PO=VO
	 * @param productEffect
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月15日 下午4:50:04
	 */
	private SelectItemVO toSelectOption(ProductEffect productEffect) {
		SelectItemVO selectOption = new SelectItemVO();
		selectOption.setCode(String.valueOf(productEffect.getEffectId()));
		selectOption.setName(productEffect.getEffectName());
		return selectOption;
	}
}
