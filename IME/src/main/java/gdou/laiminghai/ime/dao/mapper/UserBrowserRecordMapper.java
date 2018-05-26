package gdou.laiminghai.ime.dao.mapper;

import gdou.laiminghai.ime.model.entity.UserBrowserRecord;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserBrowserRecordMapper {
    int deleteByPrimaryKey(Long browserId);

    int insert(UserBrowserRecord record);

    UserBrowserRecord selectByPrimaryKey(Long browserId);

    List<UserBrowserRecord> selectAll();

    int updateByPrimaryKey(UserBrowserRecord record);
    
    /**
     * top查询
     * @return
     * @author: laiminghai
     * @datetime: 2018年5月26日 上午7:59:41
     */
    List<Map<String,Long>> findBrowserCountRank(Map<String,Date> map);
}