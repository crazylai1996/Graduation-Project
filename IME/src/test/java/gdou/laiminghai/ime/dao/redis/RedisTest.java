package gdou.laiminghai.ime.dao.redis;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import gdou.laiminghai.ime.BaseTest;
import gdou.laiminghai.ime.common.util.JSONUtil;
import gdou.laiminghai.ime.model.entity.UserBrowserRecord;

public class RedisTest extends BaseTest {
	
	@Autowired
	private RedisDao redisDao;
	
	@Test
	public void testZSET() {
//		String val = redisDao.get("test");
//		System.out.println(val);
//		double result = redisDao.zIncrementScore("lai", "ming", 2);
//		redisDao.zIncrementScore("lai", "ming2", 2);
		redisDao.zAdd("testzsetdd", "ming2", 2);
//		Long result2 = redisDao.zRank("lai", 3+"");//判断是否存在
//		System.out.println(result2);
//		Set<String> set = redisDao.zReverseRange("lai", 0, 1);
//		System.out.println(set.toString());
	}
	
	@Test
	public void testJSON() {
		UserBrowserRecord record = new UserBrowserRecord();
		record.setUserId(1l);
		record.setProductId(2l);
		record.setBrowserTime(new Date());
		String jsonStr = JSONObject.toJSONString(record);
		JSONObject jsonObj = JSONObject.parseObject(jsonStr);
		System.out.println(JSON.toJavaObject(jsonObj, UserBrowserRecord.class).toString());
//		System.out.println(jsonStr);
//		Map<String, String> params = JSONObject.parseObject(jsonStr, new TypeReference<Map<String, String>>(){});
//		System.out.println(params);
//		redisDao.hPutAll("minghai", params);
//		String val = (String)redisDao.hGet("minghai", "id");
//		Map<Object, Object> map = redisDao.hGetAll("minghai");
//		String jsonMap = JSON.toJSONString(map);
//		UserBrowserRecord record2 = JSON.parseObject(jsonMap, new TypeReference<UserBrowserRecord>() {});
//		JSONObject json = JSONObject.parseObject(jsonMap);
//		UserBrowserRecord record2 = JSON.toJavaObject(json, UserBrowserRecord.class);
//		UserBrowserRecord record2 = JSONUtil.map2Object(map, UserBrowserRecord.class);
//		System.out.println(record2.getUserId());
//		System.out.println(jsonMap);//null
	}
	
	@Test
	public void test33() {
//		long l = 3;
//		Map<Long,String> map = new HashMap<>();
//		map.put(l, "lai");
//		System.out.println(map.get(l));
		List<String> list = null;
		for (String string : list) {
			System.out.println(string);
		}
	}
}
