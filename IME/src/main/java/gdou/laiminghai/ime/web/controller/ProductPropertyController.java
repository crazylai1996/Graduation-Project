package gdou.laiminghai.ime.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import gdou.laiminghai.ime.model.vo.SelectItemVO;
import gdou.laiminghai.ime.service.ProductPropertyService;

/**
 * 产品属性控制类
 * @ClassName: ProductPropertyController
 * @author: laiminghai
 * @datetime: 2018年5月15日 下午3:56:36
 */
@Controller
@RequestMapping("/productProperty")
public class ProductPropertyController {
	
	@Autowired
	private ProductPropertyService productPropertyService;
	
	/**
	 * 获取所有产品属性
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月15日 下午3:58:30
	 */
	@RequestMapping("/getAll")
	public ModelAndView getAllProductProperties() {
		ModelAndView mav = new ModelAndView("common/option_select");
		List<SelectItemVO> selectOptions = productPropertyService.getAllProductProperties();
		mav.addObject("selectOptions", selectOptions);
		return mav;
	}
}
