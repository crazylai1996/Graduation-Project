package gdou.laiminghai.ime.service.impl;

import static org.hamcrest.CoreMatchers.endsWith;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import gdou.laiminghai.ime.common.exception.ServiceException;
import gdou.laiminghai.ime.common.exception.ServiceResultEnum;
import gdou.laiminghai.ime.common.setting.AppSetting;
import gdou.laiminghai.ime.common.statics.SkinTextureEnum;
import gdou.laiminghai.ime.common.util.FileUtil;
import gdou.laiminghai.ime.dao.mapper.ProductInfoMapper;
import gdou.laiminghai.ime.model.entity.ProductInfo;
import gdou.laiminghai.ime.model.entity.ProductPicture;
import gdou.laiminghai.ime.model.vo.ProductInfoVO;
import gdou.laiminghai.ime.service.ProductPictureService;
import gdou.laiminghai.ime.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductInfoMapper productInfoMapper;
	@Autowired
	private ProductPictureService productPictureService;
	
	//日志记录
	private static final Logger logger = Logger.getLogger(ProductServiceImpl.class);

	@Override
	public void addNewProduct(ProductInfoVO productInfoVO, 
			MultipartFile coverFile, MultipartFile[] productFiles,
			String coverSavedPath, String picturesSavedPath) {
		//图片为空
		if(coverFile ==null || productFiles.length ==0 ) {
			throw new ServiceException(ServiceResultEnum.PRODUCT_FORM_IMCOMPLETE);
		}
		ProductInfo productInfoPO = productInfoVO2productInfoPO(productInfoVO);
		//插入数据库
		productInfoMapper.insert(productInfoPO);
		//保存封面图片
		String coverName = UUID.randomUUID().toString().replaceAll("-", "") + ".png";
		boolean coverSaveSuccess = FileUtil.save(coverFile, coverSavedPath, coverName);
		//保存失败
		if(!coverSaveSuccess) {
			throw new ServiceException(ServiceResultEnum.PRODUCT_PICTURE_SAVE_FAILURE);
		}
		//保存商品图片
		boolean picturesSaveSuccess = true;
		List<ProductPicture> productPictures = new ArrayList<>();
		for (MultipartFile multipartFile : productFiles) {
			String pictureName = UUID.randomUUID().toString().replaceAll("-", "") + ".png";
			boolean successFlag = FileUtil.save(multipartFile, picturesSavedPath, pictureName);
			if(!successFlag) {
				picturesSaveSuccess = false;
				break;
			}
			productPictures.add(new ProductPicture(productInfoPO.getProductId(),pictureName));
		}
		//商品图片保存失败
		if(!picturesSaveSuccess) {
			//删除已成功保存的封面图片和商品图片
			FileUtil.delete(coverSavedPath, coverName);
			for (ProductPicture productPicture : productPictures) {
				FileUtil.delete(picturesSavedPath, productPicture.getPictureUrl());
			}
			throw new ServiceException(ServiceResultEnum.PRODUCT_PICTURE_SAVE_FAILURE);
		}
		//更新封面地址至数据库
		productInfoPO.setCover(coverName);
		productInfoMapper.updateByPrimaryKey(productInfoPO);
		//添加商品图片地址至数据库
		productPictureService.saveAll(productPictures);
		logger.debug("添加新产品成功！");
	}
	
	@Override
	public ProductInfoVO getProductInfo(Long productId) {
		ProductInfo productInfoPO = productInfoMapper.findProductInfoById(productId);
		//产品不存在
		if(productInfoPO == null) {
			throw new ServiceException(ServiceResultEnum.PRODUCT_NOT_FOUND);
		}
		return productInfoPO2productInfoVO(productInfoPO);
	}

	@Override
	public boolean productIsExist(Long productId) {
		ProductInfo productInfo = productInfoMapper.selectByPrimaryKey(productId);
		return productInfo != null;
	}

	@Override
	public List<ProductInfoVO> findMoreProductInfo(List<Long> productIds) {
		List<ProductInfoVO> productInfoVOs = new ArrayList<>();
		List<ProductInfo> productInfoPOs =  productInfoMapper.findMoreProductInfoByIds(productIds);
		for (ProductInfo productInfo : productInfoPOs) {
			productInfoVOs.add(productInfoPO2productInfoVO(productInfo));
		}
		return productInfoVOs;
	}

	/**
	 * VO=>PO
	 * @param productInfoVO
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月15日 上午8:18:26
	 */
	private ProductInfo productInfoVO2productInfoPO(ProductInfoVO productInfoVO) {
		ProductInfo productInfoPO = new ProductInfo();
		productInfoPO.setBrand(productInfoVO.getBrand());
		productInfoPO.setProductName(productInfoVO.getProductName());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月");
		try {
			String dateText = productInfoVO.getComeInDate();
			if(StringUtils.isNotBlank(dateText)) {
				Date date = sdf.parse(dateText);
				productInfoPO.setComeInDate(date);
			}
		} catch (ParseException e) {
			logger.error("商品上市日期转换错误：",e);
		}
		productInfoPO.setSpec(productInfoVO.getSpec());
		productInfoPO.setReferencePrice(productInfoVO.getReferencePrice());
		productInfoPO.setClassify(productInfoVO.getClassify());
		productInfoPO.setProperty(productInfoVO.getProperty());
		productInfoPO.setEffect(productInfoVO.getEffect());
		productInfoPO.setSkinTexture(productInfoVO.getSkinTexture());
		productInfoPO.setDesc(productInfoVO.getDesc());
		return productInfoPO;
	}
	
	/**
	 * PO=>VO
	 * @param productInfoPO
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月16日 下午12:17:49
	 */
	private ProductInfoVO productInfoPO2productInfoVO(ProductInfo productInfoPO) {
		ProductInfoVO productInfoVO = new ProductInfoVO();
		productInfoVO.setProductId(productInfoPO.getProductId());
		productInfoVO.setProductName(productInfoPO.getProductName());
		productInfoVO.setBrand(productInfoPO.getBrand());
		if(productInfoPO.getProductBrand() != null) {
			productInfoVO.setBrandName(productInfoPO.getProductBrand().getBrandName());
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月");
		//时间转换
		Date comeInDate = productInfoPO.getComeInDate();
		if(comeInDate != null) {
			String dateText = sdf.format(comeInDate);
			productInfoVO.setComeInDate(dateText);
		}
		
		productInfoVO.setSpec(productInfoPO.getSpec());
		productInfoVO.setReferencePrice(productInfoPO.getReferencePrice());
		productInfoVO.setClassify(productInfoPO.getClassify());
		if(productInfoPO.getProductClass() != null) {
			productInfoVO.setClassifyName(productInfoPO.getProductClass().getClassName());
		}
		productInfoVO.setProperty(productInfoPO.getProperty());
		if(productInfoPO.getProductProperty() != null) {
			productInfoVO.setPropertyName(productInfoPO.getProductProperty().getPropertyName());
		}
		productInfoVO.setEffect(productInfoPO.getEffect());
		if(productInfoPO.getProductEffect() != null) {
			productInfoVO.setEffectName(productInfoPO.getProductEffect().getEffectName());
		}
		productInfoVO.setDesc(productInfoPO.getDesc());
		//肤质
		if(StringUtils.isNotBlank(productInfoPO.getSkinTexture())) {
			SkinTextureEnum skinTexture = SkinTextureEnum.of(productInfoPO.getSkinTexture());
			if(skinTexture != null) {
				productInfoVO.setSkinTexture(skinTexture.getName());
			}
		}
		//封面图片地址
		productInfoVO.setCoverImage(AppSetting.APP_ROOT+AppSetting.PRODUCT_COVER_SAVED_PATH+productInfoPO.getCover());
		//产品图片地址
		List<ProductPicture> pictures = productInfoPO.getPictures();
		List<String> pictrueUrls = new ArrayList<>();
		if(pictures != null) {
			for (ProductPicture productPicture : pictures) {
				pictrueUrls.add(AppSetting.APP_ROOT+AppSetting.PRODUCT_PICTURES_SAVED_PATH + productPicture.getPictureUrl());
			}
		}
		productInfoVO.setProductImages(pictrueUrls);
		return productInfoVO;
	}
}
