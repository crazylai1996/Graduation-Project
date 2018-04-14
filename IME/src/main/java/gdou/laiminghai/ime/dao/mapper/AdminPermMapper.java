package gdou.laiminghai.ime.dao.mapper;

import gdou.laiminghai.ime.model.entity.AdminPerm;
import java.util.List;

public interface AdminPermMapper {
    int deleteByPrimaryKey(Integer permId);

    int insert(AdminPerm record);

    AdminPerm selectByPrimaryKey(Integer permId);

    List<AdminPerm> selectAll();

    int updateByPrimaryKey(AdminPerm record);
}