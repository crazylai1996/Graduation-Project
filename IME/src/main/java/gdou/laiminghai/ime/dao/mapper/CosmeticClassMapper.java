package gdou.laiminghai.ime.dao.mapper;

import gdou.laiminghai.ime.model.entity.CosmeticClass;
import java.util.List;

public interface CosmeticClassMapper {
    int deleteByPrimaryKey(Integer classId);

    int insert(CosmeticClass record);

    CosmeticClass selectByPrimaryKey(Integer classId);

    List<CosmeticClass> selectAll();

    int updateByPrimaryKey(CosmeticClass record);
    
    //我是分割线
    /**
     * 递归查找所有化妆品品类
     * @return
     * @author: laiminghai
     * @datetime: 2018年5月16日 上午8:40:19
     */
    List<CosmeticClass> getAllCosmeticClasses();
    /**
     * 查找子分类
     * @param parentId
     * @return
     * @author: laiminghai
     * @datetime: 2018年5月16日 上午8:42:32
     */
    List<CosmeticClass> getChildCosmeticClasses(Integer parentId);
    
    /**
     * 查询所有二级分类
     * @return
     * @author: laiminghai
     * @datetime: 2018年5月22日 上午10:48:17
     */
    List<CosmeticClass> getAllChildClasses();
}