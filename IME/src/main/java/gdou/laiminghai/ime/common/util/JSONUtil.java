package gdou.laiminghai.ime.common.util;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import gdou.laiminghai.ime.model.entity.UserBrowserRecord;

/**
 * JSON工具类
 * @ClassName: JSONUtil
 * @author: laiminghai
 * @datetime: 2018年5月26日 上午12:52:35
 */
public class JSONUtil {
	
	/**
	 * 对象转Map<String,String>
	 * @param object
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月26日 上午12:51:17
	 */
	public static Map<String,String> object2Map(Object object){
		String jsonString = JSONObject.toJSONString(object);
		Map<String, String> map = JSONObject.parseObject(jsonString, new TypeReference<Map<String, String>>(){});
		return map;
	}
	
	/**
	 * Map转对象
	 * @param map
	 * @param clazz
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月26日 上午1:40:49
	 */
	public static <T> T map2Object(Map<Object,Object> map,Class<T> clazz) {
		String jsonMap = JSON.toJSONString(map);
		JSONObject jsonObj = JSONObject.parseObject(jsonMap);
		return JSON.toJavaObject(jsonObj, clazz);
	}
}
