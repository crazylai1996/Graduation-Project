package gdou.laiminghai.ime.mapper;

import gdou.laiminghai.ime.model.entity.ProductEffect;
import java.util.List;

public interface ProductEffectMapper {
    int deleteByPrimaryKey(Integer effectId);

    int insert(ProductEffect record);

    ProductEffect selectByPrimaryKey(Integer effectId);

    List<ProductEffect> selectAll();

    int updateByPrimaryKey(ProductEffect record);
}