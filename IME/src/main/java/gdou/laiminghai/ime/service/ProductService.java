package gdou.laiminghai.ime.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import gdou.laiminghai.ime.model.dto.PageResult;
import gdou.laiminghai.ime.model.dto.ResultDTO;
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
	/**
	 * 关注产品
	 * @param userId
	 * @param productId
	 * @author: laiminghai
	 * @datetime: 2018年5月21日 上午10:17:34
	 */
	ResultDTO followProduct(Long userId,Long productId);
	
	/**
	 * 取消关注产品
	 * @param userId
	 * @param productId
	 * @author: laiminghai
	 * @datetime: 2018年5月21日 下午12:20:42
	 */
	ResultDTO unfollowProduct(Long userId,Long productId);
	
	/**
	 * 判断当前产品是否已经被关注
	 * @param userId
	 * @param productId
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月21日 下午4:50:32
	 */
	boolean isFolloedProduct(Long userId,Long productId);
	
	/**
	 * 查询关注的产品
	 * @param userId
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月22日 上午12:54:07
	 */
	List<ProductInfoVO> findFollowedProducts(Long userId);
	/**
	 * 查找所有
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月27日 上午8:02:44
	 */
	List<ProductInfoVO> findAll();
	/**
	 * 商品搜索
	 * @param params
	 * @param keyword
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月27日 上午8:34:35
	 */
	PageResult<ProductInfoVO> searchProductsByPage(Map<String,String> params,int pageNum);
	
	/**
	 * 统计商品关注量
	 * @param productId
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月29日 上午8:29:34
	 */
	Long countUserFollow(Long productId);
}
