package gdou.laiminghai.ime.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import gdou.laiminghai.ime.common.util.ResultDTOUtil;
import gdou.laiminghai.ime.model.dto.ResultDTO;
import gdou.laiminghai.ime.model.entity.CosmeticClass;
import gdou.laiminghai.ime.model.vo.SelectItemVO;
import gdou.laiminghai.ime.service.CosmeticClassService;

/**
 * 化妆品品类控制器
 * @ClassName: CosmeticClassController
 * @author: laiminghai
 * @datetime: 2018年5月16日 上午9:20:03
 */
@Controller
@RequestMapping("/cosmeticClass")
public class CosmeticClassController {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private CosmeticClassService cosmeticClassService;
	
	/**
	 * 查找化妆品子分类
	 * @param parentClassId
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月16日 上午9:22:46
	 */
	@ResponseBody
	@RequestMapping("getChildCosmeticClasses.do")
	public ResultDTO getChildCosmeticClasses(Integer parentClassId) {
		List<CosmeticClass> childCosmeticClasses = cosmeticClassService.findChildCosmeticClass(parentClassId);
		return ResultDTOUtil.success(childCosmeticClasses);
	}
	
	/**
	 * 取消关注该分类
	 * @param classId
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月22日 上午9:04:09
	 */
	@ResponseBody
	@RequestMapping("/unfollowClass.do")
	public ResultDTO unfollowClass(Integer classId) {
		// 获取用户登录信息
		HttpSession session = request.getSession();
		Map<String, Object> userInfoMap = (Map<String, Object>) session.getAttribute("userInfo");
		Long userId = (Long)userInfoMap.get("userId");
		cosmeticClassService.unfollowClass(userId, classId);
		return ResultDTOUtil.success(null);
	}
	
	/**
	 * 跳转到化妆品分类选择页面
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月22日 上午10:29:47
	 */
	@RequestMapping("/classSelect")
	public ModelAndView goClassSelect() {
		// 获取用户登录信息
		HttpSession session = request.getSession();
		Map<String, Object> userInfoMap = (Map<String, Object>) session.getAttribute("userInfo");
		Long userId = (Long)userInfoMap.get("userId");
		ModelAndView mav = new ModelAndView("common/option_select_more");
		List<SelectItemVO> selectOptions = cosmeticClassService.findClassSelectItems(userId);
		mav.addObject("selectOptions", selectOptions);
		return mav;
	}
	
	/**
	 * 用户关注更多分类
	 * @param classIds
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月22日 上午9:43:14
	 */
	@ResponseBody
	@RequestMapping("/followMoreClasses.do")
	public ResultDTO followMoreClasses(Integer[] classIds) {
		// 获取用户登录信息
		HttpSession session = request.getSession();
		Map<String, Object> userInfoMap = (Map<String, Object>) session.getAttribute("userInfo");
		Long userId = (Long)userInfoMap.get("userId");
		cosmeticClassService.followMoreClasses(userId, classIds);
		return ResultDTOUtil.success(null);
	}
}
