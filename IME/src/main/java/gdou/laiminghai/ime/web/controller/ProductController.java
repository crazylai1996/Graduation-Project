package gdou.laiminghai.ime.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 商品控制器
 * @ClassName: ProductController
 * @author: laiminghai
 * @datetime: 2018年5月14日 上午12:54:51
 */
@Controller
@RequestMapping("/product")
public class ProductController {
	
	/**
	 * 跳转到添加新商品页面
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月14日 上午12:55:20
	 */
	@RequestMapping("/new")
	public ModelAndView goAddProduct() {
		ModelAndView mav = new ModelAndView("product_add");
		return mav;
	}
}
