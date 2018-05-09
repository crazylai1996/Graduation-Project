package gdou.laiminghai.ime.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gdou.laiminghai.ime.dao.mapper.AreaMapper;
import gdou.laiminghai.ime.model.entity.Area;
import gdou.laiminghai.ime.service.AreaService;

@Service
public class AreaServiceImpl implements AreaService {
	
	@Autowired
	private AreaMapper areaMapper;

	@Override
	public List<Area> findAllProvinces() {
		return areaMapper.selectChildNodes(null);
	}

	@Override
	public List<Area> findChildAreas(Integer parentAreaId) {
		if(parentAreaId == null) {
			return new ArrayList<>();
		}
		return areaMapper.selectChildNodes(parentAreaId);
	}

}
