package gdou.laiminghai.ime.mapper;

import gdou.laiminghai.ime.model.entity.AdminRole;
import java.util.List;

public interface AdminRoleMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(AdminRole record);

    AdminRole selectByPrimaryKey(Integer roleId);

    List<AdminRole> selectAll();

    int updateByPrimaryKey(AdminRole record);
}