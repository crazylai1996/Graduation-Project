package gdou.laiminghai.ime.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gdou.laiminghai.ime.dao.mapper.CosmeticClassMapper;
import gdou.laiminghai.ime.model.entity.CosmeticClass;
import gdou.laiminghai.ime.service.CosmeticClassService;

/**
 * 化妆品分类业务实现类
 * @ClassName: CosmeticClassServiceImpl
 * @author: laiminghai
 * @datetime: 2018年5月15日 下午11:36:36
 */
@Service
public class CosmeticClassServiceImpl implements CosmeticClassService {
	
	//日志记录
	private static final Logger logger = Logger.getLogger(CosmeticClassServiceImpl.class);
	
	@Autowired
	private CosmeticClassMapper cosmeticClassMapper;

	@Override
	public List<CosmeticClass> findAllClass() {
		return cosmeticClassMapper.getAllCosmeticClasses();
	}

	@Override
	public List<CosmeticClass> findChildCosmeticClass(Integer parentClassId) {
		return cosmeticClassMapper.getChildCosmeticClasses(parentClassId);
	}

}
