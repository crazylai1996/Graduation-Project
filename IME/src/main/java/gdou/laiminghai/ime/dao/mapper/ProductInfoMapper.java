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
    /**
     * 根据ID查找产品详情信息
     * @param productId
     * @return
     * @author: laiminghai
     * @datetime: 2018年5月17日 下午10:32:16
     */
    ProductInfo findProductInfoById(Long productId);
    /**
     * 
     * @param productIds
     * @return
     * @author: laiminghai
     * @datetime: 2018年5月17日 下午10:32:03
     */
    List<ProductInfo> findMoreProductInfoByIds(List<Long> productIds);
    /**
     * 查找所有商品
     * @return
     * @author: laiminghai
     * @datetime: 2018年5月27日 上午8:06:02
     */
    List<ProductInfo> findAllProducts();
}