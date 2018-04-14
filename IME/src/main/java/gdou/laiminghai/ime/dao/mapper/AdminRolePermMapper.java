package gdou.laiminghai.ime.dao.mapper;

import gdou.laiminghai.ime.model.entity.AdminRolePerm;
import java.util.List;

public interface AdminRolePermMapper {
    int deleteByPrimaryKey(Integer rolePermId);

    int insert(AdminRolePerm record);

    AdminRolePerm selectByPrimaryKey(Integer rolePermId);

    List<AdminRolePerm> selectAll();

    int updateByPrimaryKey(AdminRolePerm record);
}