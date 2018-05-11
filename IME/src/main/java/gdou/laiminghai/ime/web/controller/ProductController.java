package gdou.laiminghai.ime.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@RequestMapping("/new")
	public ModelAndView goAddProduct() {
		ModelAndView mav = new ModelAndView("product_add");
		return mav;
	}
}
