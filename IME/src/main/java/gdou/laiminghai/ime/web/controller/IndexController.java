package gdou.laiminghai.ime.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
	
	/**
	 * 网站主页
	 * @return
	 */
	@RequestMapping({"/","/index"})
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView("index");
		
		return mav;
	}
}
