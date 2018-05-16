package gdou.laiminghai.ime.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import gdou.laiminghai.ime.common.util.ResultDTOUtil;
import gdou.laiminghai.ime.model.dto.ResultDTO;
import gdou.laiminghai.ime.model.entity.CosmeticClass;
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
}
