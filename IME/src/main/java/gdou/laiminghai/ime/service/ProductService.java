package gdou.laiminghai.ime.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import gdou.laiminghai.ime.model.vo.ProductInfoVO;

/**
 * 商品业务接口
 * @ClassName: ProductService
 * @author: laiminghai
 * @datetime: 2018年5月15日 上午7:59:06
 */
public interface ProductService {
	/**
	 * 添加新商品
	 * @param productInfoVO
	 * @param coverFile
	 * @param productFiles
	 * @param coverSavedPath
	 * @param picturesSavedPath
	 * @author: laiminghai
	 * @datetime: 2018年5月15日 上午7:59:16
	 */
	void addNewProduct(ProductInfoVO productInfoVO,
			MultipartFile coverFile,MultipartFile[] productFiles,
			String coverSavedPath,String picturesSavedPath);
	/**
	 * 获取化妆品详情信息
	 * @param productId
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月16日 上午10:12:37
	 */
	ProductInfoVO getProductInfo(Long productId);
	/**
	 * 判断产品是否存在
	 * @param productId
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月17日 下午3:26:55
	 */
	boolean productIsExist(Long productId);
	/**
	 * 查找多个化妆品信息
	 * @param productIds
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月17日 下午10:29:24
	 */
	List<ProductInfoVO> findMoreProductInfo(List<Long> productIds);
}
