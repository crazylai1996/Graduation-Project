package gdou.laiminghai.ime.service;

import java.util.List;

import gdou.laiminghai.ime.model.entity.Area;

public interface AreaService {
	/**
	 * 查询所有省份
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月9日 下午9:24:19
	 */
	List<Area> findAllProvinces();
	/**
	 * 查询子区域
	 * @param parentAreaId
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月9日 下午9:24:26
	 */
	List<Area> findChildAreas(Integer parentAreaId);
}
