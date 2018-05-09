package gdou.laiminghai.ime.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import gdou.laiminghai.ime.common.util.ResultDTOUtil;
import gdou.laiminghai.ime.model.dto.ResultDTO;
import gdou.laiminghai.ime.model.entity.Area;
import gdou.laiminghai.ime.service.AreaService;

@Controller
@RequestMapping("/area")
public class AreaController {
	
	@Autowired
	private AreaService areaService;
	
	/**
	 * 根据省份ID获取城市列表
	 * @param provinceId
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月9日 下午10:28:44
	 */
	@ResponseBody
	@RequestMapping("/getCities.do")
	public ResultDTO getCities(Integer provinceId) {
		List<Area> cities = areaService.findChildAreas(provinceId);
		return ResultDTOUtil.success(cities);
	}
}
