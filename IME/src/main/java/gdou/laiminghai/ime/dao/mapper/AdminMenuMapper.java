package gdou.laiminghai.ime.dao.mapper;

import gdou.laiminghai.ime.model.entity.AdminMenu;
import java.util.List;

public interface AdminMenuMapper {
    int deleteByPrimaryKey(Integer menuId);

    int insert(AdminMenu record);

    AdminMenu selectByPrimaryKey(Integer menuId);

    List<AdminMenu> selectAll();

    int updateByPrimaryKey(AdminMenu record);
}