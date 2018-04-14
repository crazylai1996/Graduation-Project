package gdou.laiminghai.ime.mapper;

import gdou.laiminghai.ime.model.entity.Area;
import java.util.List;

public interface AreaMapper {
    int deleteByPrimaryKey(Integer areaId);

    int insert(Area record);

    Area selectByPrimaryKey(Integer areaId);

    List<Area> selectAll();

    int updateByPrimaryKey(Area record);
}