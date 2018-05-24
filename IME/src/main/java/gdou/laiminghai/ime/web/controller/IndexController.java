package gdou.laiminghai.ime.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import gdou.laiminghai.ime.model.dto.PageResult;
import gdou.laiminghai.ime.model.vo.CommentInfoVO;
import gdou.laiminghai.ime.service.CommentService;

@Controller
public class IndexController {
	
	@Autowired
	private CommentService commentService;
	
	/**
	 * 网站主页
	 * @return
	 */
	@RequestMapping({"/","/index"})
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView("index");
		Map<String,Object> map = new HashMap<>();
		map.put("pageNum", 1);
		PageResult<CommentInfoVO> commentPageResult = commentService.findNewestComments(map);
		mav.addObject("commentPageResult", commentPageResult);
		return mav;
	}
}
