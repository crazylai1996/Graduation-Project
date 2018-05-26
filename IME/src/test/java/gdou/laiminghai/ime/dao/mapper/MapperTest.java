package gdou.laiminghai.ime.dao.mapper;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import gdou.laiminghai.ime.BaseTest;

public class MapperTest extends BaseTest{
	
	@Autowired
	private UserBrowserRecordMapper userBrowserRecordMapper;
	
	@Test
	public void testCount() {
		Map<String,Date> map = new HashMap<>();
		Calendar c = Calendar.getInstance();
		map.put("endTime", c.getTime());
		c.add(Calendar.DAY_OF_MONTH, -7);
		map.put("startTime", c.getTime());
		List<Map<String,Long>> result = userBrowserRecordMapper.findBrowserCountRank(map);
		for (Map<String, Long> map2 : result) {
			System.out.println(map2.toString());
		}
	}
	
}
