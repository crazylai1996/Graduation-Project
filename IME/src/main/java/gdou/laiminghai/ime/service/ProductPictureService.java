package gdou.laiminghai.ime.service;

import java.util.List;

import gdou.laiminghai.ime.model.entity.ProductPicture;

/**
 * 商品图片业务实现类
 * @ClassName: ProductPictureService
 * @author: laiminghai
 * @datetime: 2018年5月15日 上午9:15:17
 */
public interface ProductPictureService {
	//保存多个商品图片
	void saveAll(List<ProductPicture> productPictures);
}
