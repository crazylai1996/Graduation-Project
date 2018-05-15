package gdou.laiminghai.ime.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gdou.laiminghai.ime.dao.mapper.ProductPictureMapper;
import gdou.laiminghai.ime.model.entity.ProductPicture;
import gdou.laiminghai.ime.service.ProductPictureService;

@Service
public class ProductPictureServiceImpl implements ProductPictureService {
	
	@Autowired
	private ProductPictureMapper productPictureMapper;

	@Override
	public void saveAll(List<ProductPicture> productPictures) {
		for (ProductPicture productPicture : productPictures) {
			productPictureMapper.insert(productPicture);
		}
	}

}
