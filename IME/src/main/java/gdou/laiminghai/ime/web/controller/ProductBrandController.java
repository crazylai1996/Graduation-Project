package gdou.laiminghai.ime.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import gdou.laiminghai.ime.model.vo.SelectItemVO;
import gdou.laiminghai.ime.service.ProductBrandService;

/**
 * 产品品牌控制类
 * @ClassName: ProductBrandController
 * @author: laiminghai
 * @datetime: 2018年5月15日 下午4:40:24
 */
@Controller
@RequestMapping("/productBrand")
public class ProductBrandController {
	
	@Autowired
	private ProductBrandService productBrandService;
	
	/**
	 * 获取所有产品品牌
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月15日 下午4:41:33
	 */
	@RequestMapping("/getAll")
	public ModelAndView getAllProductBrands() {
		ModelAndView mav = new ModelAndView("common/option_select");
		List<SelectItemVO> selectOptions = productBrandService.getAllProductBrands();
		mav.addObject("selectOptions", selectOptions);
		return mav;
	}
}
