package gdou.laiminghai.ime.dao.mapper;

import gdou.laiminghai.ime.model.entity.CommentReportRecord;
import java.util.List;

public interface CommentReportRecordMapper {
    int deleteByPrimaryKey(Long reportRecordId);

    int insert(CommentReportRecord record);

    CommentReportRecord selectByPrimaryKey(Long reportRecordId);

    List<CommentReportRecord> selectAll();

    int updateByPrimaryKey(CommentReportRecord record);
}