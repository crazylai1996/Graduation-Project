package gdou.laiminghai.ime.dao.mapper;

import gdou.laiminghai.ime.model.entity.AdminUser;
import java.util.List;

public interface AdminUserMapper {
    int deleteByPrimaryKey(String adminUser);

    int insert(AdminUser record);

    AdminUser selectByPrimaryKey(String adminUser);

    List<AdminUser> selectAll();

    int updateByPrimaryKey(AdminUser record);
}