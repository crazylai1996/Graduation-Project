package gdou.laiminghai.ime.mapper;

import gdou.laiminghai.ime.model.entity.ProductInfo;
import java.util.List;

public interface ProductInfoMapper {
    int deleteByPrimaryKey(Long productId);

    int insert(ProductInfo record);

    ProductInfo selectByPrimaryKey(Long productId);

    List<ProductInfo> selectAll();

    int updateByPrimaryKey(ProductInfo record);
}