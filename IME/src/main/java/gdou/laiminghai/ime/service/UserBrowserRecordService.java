package gdou.laiminghai.ime.service;

import java.util.List;
import java.util.Map;

import gdou.laiminghai.ime.model.entity.UserBrowserRecord;

public interface UserBrowserRecordService {
	/**
	 * 7天rank
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月26日 上午9:03:50
	 */
	List<Map<String, Long>> find7dBrowserCountRank();
	/**
	 * 添加浏览记录
	 * @param record
	 * @author: laiminghai
	 * @datetime: 2018年5月26日 上午9:04:01
	 */
	void addUserBrowserRecord(UserBrowserRecord record);
}
