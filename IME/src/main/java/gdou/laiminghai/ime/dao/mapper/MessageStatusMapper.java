package gdou.laiminghai.ime.dao.mapper;

import gdou.laiminghai.ime.model.entity.MessageStatus;
import java.util.List;

public interface MessageStatusMapper {
    int deleteByPrimaryKey(Long statusId);

    int insert(MessageStatus record);

    MessageStatus selectByPrimaryKey(Long statusId);

    List<MessageStatus> selectAll();

    int updateByPrimaryKey(MessageStatus record);
}