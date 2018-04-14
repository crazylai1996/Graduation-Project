package gdou.laiminghai.ime.mapper;

import gdou.laiminghai.ime.model.entity.ReplyReportRecord;
import java.util.List;

public interface ReplyReportRecordMapper {
    int deleteByPrimaryKey(Long recordId);

    int insert(ReplyReportRecord record);

    ReplyReportRecord selectByPrimaryKey(Long recordId);

    List<ReplyReportRecord> selectAll();

    int updateByPrimaryKey(ReplyReportRecord record);
}