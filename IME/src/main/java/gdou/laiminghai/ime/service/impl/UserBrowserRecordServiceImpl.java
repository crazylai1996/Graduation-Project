package gdou.laiminghai.ime.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gdou.laiminghai.ime.dao.mapper.UserBrowserRecordMapper;
import gdou.laiminghai.ime.model.entity.UserBrowserRecord;
import gdou.laiminghai.ime.service.UserBrowserRecordService;

@Service
public class UserBrowserRecordServiceImpl implements UserBrowserRecordService {
	
	@Autowired
	private UserBrowserRecordMapper userBrowserRecordMapper;

	@Override
	public List<Map<String, Long>> find7dBrowserCountRank() {
		Map<String,Date> map = new HashMap<>();
		Calendar c = Calendar.getInstance();
		map.put("endTime", c.getTime());
		c.add(Calendar.DAY_OF_MONTH, -7);
		map.put("startTime", c.getTime());
		return userBrowserRecordMapper.findBrowserCountRank(map);
	}

	@Override
	public void addUserBrowserRecord(UserBrowserRecord record) {
		userBrowserRecordMapper.insert(record);
	}

}
