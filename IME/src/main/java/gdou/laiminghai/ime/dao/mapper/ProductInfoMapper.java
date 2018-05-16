package gdou.laiminghai.ime.dao.mapper;

import gdou.laiminghai.ime.model.entity.ProductInfo;
import java.util.List;

public interface ProductInfoMapper {
    int deleteByPrimaryKey(Long productId);

    int insert(ProductInfo record);

    ProductInfo selectByPrimaryKey(Long productId);

    List<ProductInfo> selectAll();

    int updateByPrimaryKey(ProductInfo record);
    
    //我是分割线
    //根据ID查找产品详情信息
    ProductInfo findProductInfoById(Long productId);
}