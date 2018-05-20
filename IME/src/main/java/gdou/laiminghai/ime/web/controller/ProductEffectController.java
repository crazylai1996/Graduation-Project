package gdou.laiminghai.ime.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import gdou.laiminghai.ime.model.vo.SelectItemVO;
import gdou.laiminghai.ime.service.ProductEffectService;

/**
 * 产品功效控制器
 * @ClassName: ProductEffectController
 * @author: laiminghai
 * @datetime: 2018年5月15日 下午4:54:24
 */
@Controller
@RequestMapping("/productEffect")
public class ProductEffectController {
	
	@Autowired
	private ProductEffectService productEffectService;

	/**
	 * 获取所有产品功效列表
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月15日 下午4:54:05
	 */
	@RequestMapping("/getAll")
	public ModelAndView getAllProductEffects() {
		ModelAndView mav = new ModelAndView("common/option_select");
		List<SelectItemVO> selectOptions = productEffectService.getAllProductEffects();
		mav.addObject("selectOptions", selectOptions);
		return mav;
	}
}
