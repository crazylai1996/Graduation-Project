package gdou.laiminghai.ime.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import gdou.laiminghai.ime.model.dto.PageResult;
import gdou.laiminghai.ime.model.entity.CosmeticClass;
import gdou.laiminghai.ime.model.vo.CommentInfoVO;
import gdou.laiminghai.ime.model.vo.ProductInfoVO;
import gdou.laiminghai.ime.model.vo.SelectItemVO;
import gdou.laiminghai.ime.service.CommentService;
import gdou.laiminghai.ime.service.CosmeticClassService;
import gdou.laiminghai.ime.service.ProductBrandService;
import gdou.laiminghai.ime.service.ProductEffectService;
import gdou.laiminghai.ime.service.ProductPropertyService;
import gdou.laiminghai.ime.service.RankService;
import gdou.laiminghai.ime.service.RecommendationService;

@Controller
public class IndexController {
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private RankService rankService;
	
	@Autowired
	private RecommendationService recommendationService;
	
	@Autowired
	private CosmeticClassService cosmeticClassService;
	
	@Autowired
	private ProductPropertyService productPropertyService;
	
	@Autowired
	private ProductEffectService productEffectService;
	
	@Autowired
	private ProductBrandService productBrandService;
	
	@Autowired
	private HttpServletRequest request;
	
	/**
	 * 网站主页
	 * @return
	 */
	@RequestMapping({"/","/index"})
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView("index");
		Map<String,Object> map = new HashMap<>();
		//化妆品分类加载
		List<CosmeticClass> cosmeticClasses = cosmeticClassService.findAllClass();
		mav.addObject("cosmeticClasses", cosmeticClasses);
		//商品属性
		List<SelectItemVO> productProperties = productPropertyService.getAllProductProperties();
		mav.addObject("productProperties", productProperties);
		//商品功效
		List<SelectItemVO> productEffects = productEffectService.getAllProductEffects();
		mav.addObject("productEffects", productEffects);
		//商品品牌
		List<SelectItemVO> productBrands = productBrandService.getAllProductBrands();
		mav.addObject("productBrands", productBrands);
		//最新点评
		map.put("pageNum", 1);
		PageResult<CommentInfoVO> commentPageResult = commentService.findNewestComments(map);
		mav.addObject("commentPageResult", commentPageResult);
		//商品排行
		List<ProductInfoVO> productRank = rankService.getBrowserCountRank();
		mav.addObject("productRank", productRank);
		// 获取用户登录信息
		HttpSession session = request.getSession();
		Map<String, Object> userInfoMap = (Map<String, Object>) session.getAttribute("userInfo");
		//获取商品推荐
		if(userInfoMap != null) {
			Long userId = (Long)userInfoMap.get("userId");
			List<ProductInfoVO> recProductList = recommendationService.getRecommendationResult(userId);
			mav.addObject("recProductList", recProductList);
		}
		return mav;
	}
}
