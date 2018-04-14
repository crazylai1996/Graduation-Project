package gdou.laiminghai.ime.mapper;

import gdou.laiminghai.ime.model.entity.ProductBrand;
import java.util.List;

public interface ProductBrandMapper {
    int deleteByPrimaryKey(Integer brandId);

    int insert(ProductBrand record);

    ProductBrand selectByPrimaryKey(Integer brandId);

    List<ProductBrand> selectAll();

    int updateByPrimaryKey(ProductBrand record);
}