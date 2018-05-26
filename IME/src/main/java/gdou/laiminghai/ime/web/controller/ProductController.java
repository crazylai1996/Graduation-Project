package gdou.laiminghai.ime.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import gdou.laiminghai.ime.common.exception.ServiceException;
import gdou.laiminghai.ime.common.exception.ServiceResultEnum;
import gdou.laiminghai.ime.common.setting.AppSetting;
import gdou.laiminghai.ime.common.statics.BuyWayEnum;
import gdou.laiminghai.ime.common.statics.SkinTextureEnum;
import gdou.laiminghai.ime.common.util.EnumUtil;
import gdou.laiminghai.ime.common.util.ResultDTOUtil;
import gdou.laiminghai.ime.model.dto.PageResult;
import gdou.laiminghai.ime.model.dto.ResultDTO;
import gdou.laiminghai.ime.model.entity.CosmeticClass;
import gdou.laiminghai.ime.model.entity.UserBrowserRecord;
import gdou.laiminghai.ime.model.vo.CommentInfoVO;
import gdou.laiminghai.ime.model.vo.ProductInfoVO;
import gdou.laiminghai.ime.model.vo.SelectItemVO;
import gdou.laiminghai.ime.model.vo.UserInfoVO;
import gdou.laiminghai.ime.service.CommentService;
import gdou.laiminghai.ime.service.CosmeticClassService;
import gdou.laiminghai.ime.service.ProductService;
import gdou.laiminghai.ime.service.RankService;
import gdou.laiminghai.ime.service.UserService;

/**
 * 商品控制器
 * 
 * @ClassName: ProductController
 * @author: laiminghai
 * @datetime: 2018年5月14日 上午12:54:51
 */
@Controller
@RequestMapping("/product")
public class ProductController {

	// 日志记录
	private static final Logger logger = Logger.getLogger(ProductController.class);
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CosmeticClassService cosmeticClassService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RankService rankService;

	/**
	 * 跳转到添加新商品页面
	 * 
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月14日 上午12:55:20
	 */
	@RequestMapping("/new")
	public ModelAndView goAddProduct() {
		ModelAndView mav = new ModelAndView("product/product_add");
		//化妆品品类
		List<CosmeticClass> cosmeticClasses = cosmeticClassService.findAllClass();
		//肤质列表
		List<SelectItemVO> skinTextures = EnumUtil.toList(SkinTextureEnum.class);
		mav.addObject("cosmeticClasses", cosmeticClasses);
		mav.addObject("skinTextures", skinTextures);
		return mav;
	}

	/**
	 * 跳转到封面选择页面
	 * 
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月14日 下午3:49:28
	 */
	@RequestMapping("/coverSelect")
	public ModelAndView goCoverSelect() {
		ModelAndView mav = new ModelAndView("product/cover_select");
		return mav;
	}

	/**
	 * 添加新商品
	 * 
	 * @param productInfoVO
	 * @param portrait
	 * @param productFiles
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月15日 上午12:11:32
	 */
	@ResponseBody
	@RequestMapping("/new.do")
	public ResultDTO addProduct(ProductInfoVO productInfoVO,
			@RequestParam(value = "coverFile", required = false) MultipartFile coverFile,
			@RequestParam(value = "productFiles", required = false) MultipartFile[] productFiles) {
		logger.debug("商品表单信息：" + productInfoVO.toString());
		// 获取用户登录信息
		HttpSession session = request.getSession();
		String coverSavedPath = session.getServletContext().getRealPath("/" + AppSetting.PRODUCT_COVER_SAVED_PATH);
		String picturesSavedPath = session.getServletContext().getRealPath("/" + AppSetting.PRODUCT_PICTURES_SAVED_PATH);
		Map<String, Object> userInfoMap = (Map<String, Object>) session.getAttribute("userInfo");
		// 登录失效
//		if (userInfoMap == null) {
//			throw new ServiceException(ServiceResultEnum.USER_SESSION_TIMEOUT);
//		}
		productService.addNewProduct(productInfoVO, 
				coverFile, productFiles, 
				coverSavedPath, picturesSavedPath);
		return ResultDTOUtil.success(null);
	}
	
	/**
	 * 查看产品详情
	 * @param productId
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月15日 下午7:17:06
	 */
	@RequestMapping("/info/{productId}")
	public ModelAndView goProductDetails(@PathVariable("productId") Long productId) {
		ModelAndView mav = new ModelAndView("product/product_details");
		ProductInfoVO productInfoVO = productService.getProductInfo(productId);
		logger.debug(productInfoVO.toString());
		//判断当前产品是否已被关注
		HttpSession session = request.getSession();
		Map<String, Object> userInfoMap = (Map<String, Object>) session.getAttribute("userInfo");
		Long userId = null;
		if(userInfoMap != null) {
			userId = (Long)userInfoMap.get("userId");
			productInfoVO.setFollow(productService.isFolloedProduct(userId, productId));
		}
		mav.addObject("productInfoVO", productInfoVO);
		// 购买方式
		List<SelectItemVO> buyWays = EnumUtil.toList(BuyWayEnum.class);
		mav.addObject("buyWays", buyWays);
		// 查询产品相关使用心得
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("productId", productId);
		map.put("pageNum", 1);
		PageResult<CommentInfoVO> pageResult = commentService.findCommentList(map);
		if(userInfoMap != null) {
			for (CommentInfoVO commentInfoVO : pageResult.getList()) {
				UserInfoVO userInfoVO = commentInfoVO.getUserInfo();
				if(userInfoVO != null) {
					if(userService.isFollowedUser(userId, userInfoVO.getUserId())) {
						userInfoVO.setFollow(true);
					}
				}
			}
		}
		
		logger.debug("分页结果："+pageResult.toString());
		mav.addObject("pageResult", pageResult);
		//添加浏览记录
		rankService.addBrowserRecord(new UserBrowserRecord(userId,productId));
		return mav;
	}
	
	
	/**
	 * 关注产品
	 * @param productId
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月21日 上午10:15:04
	 */
	@ResponseBody
	@RequestMapping("/followProduct.do")
	public ResultDTO followProduct(Long productId) {
		// 获取用户登录信息
		HttpSession session = request.getSession();
		Map<String, Object> userInfoMap = (Map<String, Object>) session.getAttribute("userInfo");
		Long userId = (Long)userInfoMap.get("userId");
		ResultDTO resultDTO = productService.followProduct(userId, productId);
		return resultDTO;
	}
	
	/**
	 * 取消关注产品
	 * @param productId
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月21日 下午12:17:49
	 */
	@ResponseBody
	@RequestMapping("/unfollowProduct.do")
	public ResultDTO unfollowProduct(Long productId) {
		// 获取用户登录信息
		HttpSession session = request.getSession();
		Map<String, Object> userInfoMap = (Map<String, Object>) session.getAttribute("userInfo");
		Long userId = (Long)userInfoMap.get("userId");
		ResultDTO resultDTO = productService.unfollowProduct(userId, productId);
		return resultDTO;
	}
 }
