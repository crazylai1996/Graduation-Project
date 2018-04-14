package gdou.laiminghai.ime.mapper;

import gdou.laiminghai.ime.model.entity.UserReportRecord;
import java.util.List;

public interface UserReportRecordMapper {
    int deleteByPrimaryKey(Long reportId);

    int insert(UserReportRecord record);

    UserReportRecord selectByPrimaryKey(Long reportId);

    List<UserReportRecord> selectAll();

    int updateByPrimaryKey(UserReportRecord record);
}