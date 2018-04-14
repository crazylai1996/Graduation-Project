package gdou.laiminghai.ime.mapper;

import gdou.laiminghai.ime.model.entity.UserUpRecord;
import java.util.List;

public interface UserUpRecordMapper {
    int deleteByPrimaryKey(Long upRecordId);

    int insert(UserUpRecord record);

    UserUpRecord selectByPrimaryKey(Long upRecordId);

    List<UserUpRecord> selectAll();

    int updateByPrimaryKey(UserUpRecord record);
}