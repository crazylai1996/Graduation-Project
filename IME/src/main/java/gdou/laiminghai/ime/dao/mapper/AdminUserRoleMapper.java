package gdou.laiminghai.ime.dao.mapper;

import gdou.laiminghai.ime.model.entity.AdminUserRole;
import java.util.List;

public interface AdminUserRoleMapper {
    int deleteByPrimaryKey(Integer userRoleId);

    int insert(AdminUserRole record);

    AdminUserRole selectByPrimaryKey(Integer userRoleId);

    List<AdminUserRole> selectAll();

    int updateByPrimaryKey(AdminUserRole record);
}