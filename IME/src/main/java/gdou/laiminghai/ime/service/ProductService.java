package gdou.laiminghai.ime.service;

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
}
