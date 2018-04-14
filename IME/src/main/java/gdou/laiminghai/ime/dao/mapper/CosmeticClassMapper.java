package gdou.laiminghai.ime.dao.mapper;

import gdou.laiminghai.ime.model.entity.CosmeticClass;
import java.util.List;

public interface CosmeticClassMapper {
    int deleteByPrimaryKey(Integer classId);

    int insert(CosmeticClass record);

    CosmeticClass selectByPrimaryKey(Integer classId);

    List<CosmeticClass> selectAll();

    int updateByPrimaryKey(CosmeticClass record);
}