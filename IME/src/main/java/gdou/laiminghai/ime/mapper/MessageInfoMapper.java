package gdou.laiminghai.ime.mapper;

import gdou.laiminghai.ime.model.entity.MessageInfo;
import java.util.List;

public interface MessageInfoMapper {
    int deleteByPrimaryKey(Long reportRecordId);

    int insert(MessageInfo record);

    MessageInfo selectByPrimaryKey(Long reportRecordId);

    List<MessageInfo> selectAll();

    int updateByPrimaryKey(MessageInfo record);
}