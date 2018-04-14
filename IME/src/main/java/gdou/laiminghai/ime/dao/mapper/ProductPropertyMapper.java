package gdou.laiminghai.ime.dao.mapper;

import gdou.laiminghai.ime.model.entity.ProductProperty;
import java.util.List;

public interface ProductPropertyMapper {
    int deleteByPrimaryKey(Integer propertyId);

    int insert(ProductProperty record);

    ProductProperty selectByPrimaryKey(Integer propertyId);

    List<ProductProperty> selectAll();

    int updateByPrimaryKey(ProductProperty record);
}