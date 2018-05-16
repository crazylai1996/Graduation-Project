package gdou.laiminghai.ime.web.controller;

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
import gdou.laiminghai.ime.common.util.ResultDTOUtil;
import gdou.laiminghai.ime.model.dto.ResultDTO;
import gdou.laiminghai.ime.model.entity.CosmeticClass;
import gdou.laiminghai.ime.model.vo.ProductInfoVO;
import gdou.laiminghai.ime.service.CosmeticClassService;
import gdou.laiminghai.ime.service.ProductService;

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

	/**
	 * 跳转到添加新商品页面
	 * 
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月14日 上午12:55:20
	 */
	@RequestMapping("/new")
	public ModelAndView goAddProduct() {
		ModelAndView mav = new ModelAndView("product_add");
		List<CosmeticClass> cosmeticClasses = cosmeticClassService.findAllClass();
		mav.addObject("cosmeticClasses", cosmeticClasses);
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
		ModelAndView mav = new ModelAndView("cover_select");
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
		if (userInfoMap == null) {
			throw new ServiceException(ServiceResultEnum.USER_SESSION_TIMEOUT);
		}
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
		ModelAndView mav = new ModelAndView("product_details");
		return mav;
	}
}
