package gdou.laiminghai.ime.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gdou.laiminghai.ime.common.exception.ServiceException;
import gdou.laiminghai.ime.common.exception.ServiceResultEnum;
import gdou.laiminghai.ime.dao.mapper.CosmeticClassMapper;
import gdou.laiminghai.ime.dao.mapper.UserFollowClassMapper;
import gdou.laiminghai.ime.model.entity.CosmeticClass;
import gdou.laiminghai.ime.model.entity.UserFollowClass;
import gdou.laiminghai.ime.model.vo.SelectItemVO;
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
	
	@Autowired
	private UserFollowClassMapper userFollowClassMapper;

	@Override
	public List<CosmeticClass> findAllClass() {
		return cosmeticClassMapper.getAllCosmeticClasses();
	}

	@Override
	public List<CosmeticClass> findChildCosmeticClass(Integer parentClassId) {
		return cosmeticClassMapper.getChildCosmeticClasses(parentClassId);
	}

	@Override
	public List<CosmeticClass> findFollowedClasses(Long userId) {
		return userFollowClassMapper.findFollowedClasses(userId);
	}

	@Override
	public void unfollowClass(Long userId, Integer classId) {
		// 无效操作
		if (userId == null || classId == null) {
			throw new ServiceException(ServiceResultEnum.USER_INVALID_ACTION);
		}
		Map<String,Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("classId", classId);
		List<UserFollowClass> follows = userFollowClassMapper.selectByCondition(map);
		for (UserFollowClass follow : follows) {
			userFollowClassMapper.deleteByPrimaryKey(follow.getUserClassId());
		}
	}
	
	

	@Override
	public List<SelectItemVO> findClassSelectItems(Long userId) {
		// 无效操作
		if (userId == null) {
			throw new ServiceException(ServiceResultEnum.USER_INVALID_ACTION);
		}
		List<SelectItemVO> selectItems = new ArrayList<>();
		//查询用户的关注
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		List<UserFollowClass> follows = userFollowClassMapper.selectByCondition(map);
		StringBuilder sb = new StringBuilder();
		for (UserFollowClass follow : follows) {
			sb.append("-"+follow.getClassId()+"-");
		}
		String sbString = sb.toString();
		//查询 所有的分类
		List<CosmeticClass> classes = cosmeticClassMapper.getAllChildClasses();
		for (CosmeticClass cosmeticClass : classes) {
			SelectItemVO selectItem = new SelectItemVO(String.valueOf(cosmeticClass.getClassId()),cosmeticClass.getClassName());
			if(sbString.contains("-"+cosmeticClass.getClassId()+"-")) {
				selectItem.setSelected(true);
			}
			selectItems.add(selectItem);
		}
		return selectItems;
	}

	@Override
	public void followMoreClasses(Long userId, Integer[] classIds) {
		// 无效操作
		if (userId == null || classIds == null) {
			throw new ServiceException(ServiceResultEnum.USER_INVALID_ACTION);
		}
		//先删除
		userFollowClassMapper.deleteByUserId(userId);
		//重新关注
		for (Integer classId : classIds) {
			UserFollowClass follow = new UserFollowClass();
			follow.setUserId(userId);
			follow.setClassId(classId);
			userFollowClassMapper.insert(follow);
		}
	}

}
