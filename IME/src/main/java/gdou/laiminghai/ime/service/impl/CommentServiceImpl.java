package gdou.laiminghai.ime.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import gdou.laiminghai.ime.common.exception.ServiceException;
import gdou.laiminghai.ime.common.exception.ServiceResultEnum;
import gdou.laiminghai.ime.common.setting.AppSetting;
import gdou.laiminghai.ime.common.statics.BuyWayEnum;
import gdou.laiminghai.ime.common.statics.SkinTextureEnum;
import gdou.laiminghai.ime.common.util.FileUtil;
import gdou.laiminghai.ime.dao.lucene.CommentIndexDao;
import gdou.laiminghai.ime.dao.mapper.CommentInfoMapper;
import gdou.laiminghai.ime.dao.mapper.UserFollowClassMapper;
import gdou.laiminghai.ime.dao.mapper.UserFollowProductMapper;
import gdou.laiminghai.ime.dao.mapper.UserFollowUserMapper;
import gdou.laiminghai.ime.model.dto.PageResult;
import gdou.laiminghai.ime.model.entity.CommentInfo;
import gdou.laiminghai.ime.model.entity.CommentPicture;
import gdou.laiminghai.ime.model.entity.ProductInfo;
import gdou.laiminghai.ime.model.entity.ProductPicture;
import gdou.laiminghai.ime.model.entity.UserFollowClass;
import gdou.laiminghai.ime.model.entity.UserFollowProduct;
import gdou.laiminghai.ime.model.entity.UserFollowUser;
import gdou.laiminghai.ime.model.entity.UserInfo;
import gdou.laiminghai.ime.model.vo.CommentAnalysisVO;
import gdou.laiminghai.ime.model.vo.CommentInfoVO;
import gdou.laiminghai.ime.model.vo.ProductInfoVO;
import gdou.laiminghai.ime.model.vo.UserInfoVO;
import gdou.laiminghai.ime.service.CommentPictureService;
import gdou.laiminghai.ime.service.CommentService;
import gdou.laiminghai.ime.service.ProductService;
import net.coobird.thumbnailator.Thumbnails;

@Service
public class CommentServiceImpl implements CommentService {

	// 日志记录
	private static final Logger logger = Logger.getLogger(CommentServiceImpl.class);

	@Autowired
	private CommentInfoMapper commentInfoMapper;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CommentPictureService commentPictureService;
	
	@Autowired
	private UserFollowUserMapper userFollowUserMapper;
	
	@Autowired
	private UserFollowProductMapper userFollowProductMapper;
	
	@Autowired
	private UserFollowClassMapper userFollowClassMapper;
	
	@Autowired
	private CommentIndexDao commentIndexDao;

	@Override
	public String saveCommentPicture(MultipartFile imgFile, String savedPath) {
		logger.debug("使用心得图片临时保存地址：" + savedPath);
		String fileBody = UUID.randomUUID().toString().replaceAll("-", "");
		// 获取文件名后缀
		int fileExtIndex = imgFile.getOriginalFilename().lastIndexOf(".");
		String fileExt = imgFile.getOriginalFilename().substring(fileExtIndex);
		// 重命名图片文件
		String fileName = fileBody + fileExt;
		// boolean success = FileUtil.save(imgFile, savedPath, fileName);
		try {
			// 图片压缩并保存
			File targetDir = new File(savedPath);
			if (!targetDir.exists()) {
				targetDir.mkdirs();
			}
			Thumbnails.of(imgFile.getInputStream()).scale(1f).outputQuality(0.25f).toFile(savedPath + fileName);
		} catch (Exception e) {// 图片文件保存失败
			fileName = "";
			logger.error("使用心得图片保存失败：", e);
		}
		return fileName;
	}

	@Override
	public void addNewComment(CommentInfoVO commentInfoVO, 
			List<String> commentPictures, 
			String tmpPath,
			String savedPath) {
		//评价产品不存在
		if(!productService.productIsExist(commentInfoVO.getProductId())) {
			throw new ServiceException(ServiceResultEnum.PRODUCT_NOT_FOUND);
		}
		String contentText = commentInfoVO.getContentText();
		//删除img标签
		String contentTextNew = contentText.replaceAll("<img[^>]*/>", "");
		String contentHtml = commentInfoVO.getContentHtml();
		CommentInfo commentInfoPO = commentInfoVO2CommentInfoPO(commentInfoVO);
		commentInfoPO.setContentText(contentTextNew);
		commentInfoPO.setContentHtml(contentHtml);
		if(commentPictures != null && commentPictures.size() > 0) {
			// 替换源文本内容的目录
			String comtentHtmlNew = contentHtml.replace(AppSetting.COMMENT_PICTURE_TMP_PATH, AppSetting.COMMENT_PICTURE_SAVED_PATH);
			commentInfoPO.setContentHtml(comtentHtmlNew);
		}
		// 插入到数据库
		commentInfoMapper.insert(commentInfoPO);
		//建立索引 
		commentIndexDao.addCommentIndex(commentInfoPO);
		// 将心得图片从临时图片目录移动到正式目录
		if(commentPictures != null) {
			for (String pictureName : commentPictures) {
				if (contentText.contains(pictureName)) {
					FileUtil.moveFile(new File(tmpPath, pictureName), new File(savedPath, pictureName));
					commentPictureService.addNewCommentPicture(
							new CommentPicture(commentInfoPO.getCommentId(),pictureName));
				}
			}
		}
	}

	@Override
	public CommentInfoVO getCommentInfo(Long commentId) {
		CommentInfo commentInfoPO = commentInfoMapper.selectByPrimaryKey(commentId);
		//心得不存在
		if(commentInfoPO == null) {
			throw new ServiceException(ServiceResultEnum.COMMENT_NOT_FOUND);
		}
		CommentInfoVO commentInfoVO = commentInfoPO2CommentInfoVO(commentInfoPO);
		//查找产品上一篇心得
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("commentId", commentInfoVO.getCommentId());
		map.put("userId", commentInfoVO.getUserId());
		map.put("productId", commentInfoVO.getProductId());
		CommentInfo lastCommentInfo = commentInfoMapper.findLastComment(map);
		if(lastCommentInfo != null) {
			commentInfoVO.setLastCommentId(lastCommentInfo.getCommentId());
			commentInfoVO.setLastCommentTitle(lastCommentInfo.getArticleTitle());
		}
		//查找产品下一篇心得
		CommentInfo nextCommentInfo = commentInfoMapper.findNextComment(map);
		if(nextCommentInfo != null) {
			commentInfoVO.setNextCommentId(nextCommentInfo.getCommentId());
			commentInfoVO.setNextCommentTitle(nextCommentInfo.getArticleTitle());
		}
		//查找用户最近三个心得记录
		List<Long> productIds = commentInfoMapper.findThreeLastestCommentRecords(map);
		if(productIds.size() > 0) {
			List<ProductInfoVO> productInfoVOs = productService.findMoreProductInfo(productIds);
			commentInfoVO.setProductInfoVOs(productInfoVOs);
		}
		return commentInfoVO;
	}

	@Override
	public PageResult<CommentInfoVO> findCommentList(Map<String, Object> map) {
		Integer pageNum = (Integer)map.get("pageNum");
		PageHelper.startPage(pageNum, AppSetting.NUMBER_PER_PAGE);
		List<CommentInfo> comentInfoList = commentInfoMapper.selectByCondition(map);
		PageInfo<CommentInfo> pageInfo = new PageInfo<CommentInfo>(comentInfoList);
		List<CommentInfoVO> commentInfoVOList = new ArrayList<CommentInfoVO>();
		for (CommentInfo commentInfo : comentInfoList) {
			CommentInfoVO commentInfoVO = commentInfoPO2CommentInfoVO(commentInfo);
			String contentText = commentInfo.getContentText();
			if(StringUtils.isNotBlank(contentText) && contentText.length() > 120) {
				contentText = contentText.substring(0, 120);
			}
			commentInfoVO.setContentText(contentText);
			commentInfoVOList.add(commentInfoVO);
		}
		//构建分页结果并返回
		PageResult<CommentInfoVO> pageResult = new PageResult<CommentInfoVO>(commentInfoVOList);
		pageResult.setPages(pageInfo.getPages());
		return pageResult;
	}
	
	@Override
	public PageResult<CommentInfoVO> findMyFollowedComments(Map<String, Object> map) {
		Integer pageNum = (Integer)map.get("pageNum");
		//获取关注的用户
		List<Long> followedUserIds = new ArrayList<>();
		List<UserFollowUser> followedUsers = userFollowUserMapper.selectByCondition(map);
		for (UserFollowUser follow : followedUsers) {
			followedUserIds.add(follow.getFollowedUserId());
		}
		map.put("followedUserIds", followedUserIds);
		//获取关注的商品
		List<Long> followedProductIds = new ArrayList<>();
		List<UserFollowProduct> followedProducts = userFollowProductMapper.selectByCondition(map);
		for (UserFollowProduct follow : followedProducts) {
			followedProductIds.add(follow.getProductId());
		}
		map.put("followedProductIds", followedProductIds);
		//获取关注的品类
		List<Integer> followedClassIds = new ArrayList<>();
		List<UserFollowClass> followedClasses = userFollowClassMapper.selectByCondition(map);
		for (UserFollowClass follow : followedClasses) {
			followedClassIds.add(follow.getClassId());
		}
		map.put("followedClassIds", followedClassIds);
		PageHelper.startPage(pageNum, AppSetting.NUMBER_PER_PAGE);
		List<CommentInfo> commentList = commentInfoMapper.findFollowedComments(map);
		//构建分页结果并返回
		PageInfo<CommentInfo> pageInfo = new PageInfo<CommentInfo>(commentList);
		List<CommentInfoVO> commentInfoVOList = new ArrayList<CommentInfoVO>();
		for (CommentInfo commentInfo : commentList) {
			CommentInfoVO commentInfoVO = commentInfoPO2CommentInfoVO(commentInfo);
			String contentText = commentInfo.getContentText();
			if(StringUtils.isNotBlank(contentText) && contentText.length() > 120) {
				contentText = contentText.substring(0, 120);
			}
			commentInfoVO.setContentText(contentText);
			commentInfoVOList.add(commentInfoVO);
		}
		//构建分页结果并返回
		PageResult<CommentInfoVO> pageResult = new PageResult<CommentInfoVO>(commentInfoVOList);
		pageResult.setPages(pageInfo.getPages());
		return pageResult;
	}
	
	@Override
	public PageResult<CommentInfoVO> findNewestComments(Map<String,Object> map) {
		Integer pageNum = (Integer)map.get("pageNum");
		PageHelper.startPage(pageNum, AppSetting.NUMBER_PER_PAGE);
		List<CommentInfo> commentList = commentInfoMapper.findNewestComments();
		//构建分页结果并返回
		PageInfo<CommentInfo> pageInfo = new PageInfo<CommentInfo>(commentList);
		List<CommentInfoVO> commentInfoVOList = new ArrayList<CommentInfoVO>();
		for (CommentInfo commentInfo : commentList) {
			CommentInfoVO commentInfoVO = commentInfoPO2CommentInfoVO(commentInfo);
			String contentText = commentInfo.getContentText();
			if (StringUtils.isNotBlank(contentText) && contentText.length() > 120) {
				contentText = contentText.substring(0, 120);
			}
			commentInfoVO.setContentText(contentText);
			commentInfoVOList.add(commentInfoVO);
		}
		// 构建分页结果并返回
		PageResult<CommentInfoVO> pageResult = new PageResult<CommentInfoVO>(commentInfoVOList);
		pageResult.setPages(pageInfo.getPages());
		return pageResult;
	}

	@Override
	public PageResult<CommentInfoVO> searchCommentsByPage(int pageNum, String keyword) {
		List<CommentInfoVO> commentResult = commentIndexDao.searchByPage(keyword, pageNum);
		List<CommentInfoVO> pageList = new ArrayList<>();
		Map<String,Object> map = new HashMap<>();
		for (CommentInfoVO comment : commentResult) {
			map.put("commentId", comment.getCommentId());
			List<CommentInfo> commentInfoPOs = commentInfoMapper.selectByCondition(map);
			if(commentInfoPOs != null && commentInfoPOs.size() > 0) {
				CommentInfo comentInfoPO = commentInfoPOs.get(0);
				CommentInfoVO commentInfoVO = commentInfoPO2CommentInfoVO(comentInfoPO);
				if(StringUtils.isNotBlank(comment.getContentText())) {
					commentInfoVO.setContentText(comment.getContentText());
				}
				if(StringUtils.isNotBlank(comment.getArticleTitle())) {
					commentInfoVO.setArticleTitle(comment.getArticleTitle());
				}
				pageList.add(commentInfoVO);
			}
		}
		PageResult<CommentInfoVO> searchResult = new PageResult<>(pageList);
		searchResult.setPageSize(AppSetting.NUMBER_PER_PAGE);
		return searchResult;
	}

	@Override
	public CommentInfoVO findLatestCommentByProductId(Long productId) {
		CommentInfo commentInfo = commentInfoMapper.findLatestCommentByProductId(productId);
		if(commentInfo == null) {
			return null;
		}
		return commentInfoPO2CommentInfoVO(commentInfo);
	}
	

	@Override
	public Long countCommentByProductId(Long productId) {
		return commentInfoMapper.countCommentByProductId(productId);
	}

	@Override
	public CommentAnalysisVO commentAnalyze(Long productId) {
		CommentAnalysisVO commentAnalysis = new CommentAnalysisVO();
		long commentCount = commentInfoMapper.countCommentByProductId(productId);
		logger.debug("评分个数："+commentCount);
		commentAnalysis.setCommentCount(commentCount);
		if(commentCount == 0) {
			return commentAnalysis;
		}
		Map<String,Object> params = new HashMap<>();
		params.put("productId", productId);
		//统计综合评分
		float avgScore = commentInfoMapper.countAvgScore(productId);
		float avgScoreFmt = (float)(Math.round(avgScore*100))/100;
		commentAnalysis.setAvgScore(avgScoreFmt);
		//统计评分
		Map<String,Float> scoreAnalysis = new HashMap<>();
		params.put("heart", 1);
		scoreAnalysis.put("oneHeart", countProportion(commentInfoMapper.countCommentHeart(params),commentCount));
		params.put("heart", 2);
		scoreAnalysis.put("twoHearts", countProportion(commentInfoMapper.countCommentHeart(params),commentCount));
		params.put("heart", 3);
		scoreAnalysis.put("threeHearts", countProportion(commentInfoMapper.countCommentHeart(params),commentCount));
		params.put("heart", 4);
		scoreAnalysis.put("fourHearts", countProportion(commentInfoMapper.countCommentHeart(params),commentCount));
		params.put("heart", 5);
		scoreAnalysis.put("fiveHearts", countProportion(commentInfoMapper.countCommentHeart(params),commentCount));
		logger.debug("评分统计："+scoreAnalysis.toString());
		commentAnalysis.setScoreAnalysis(scoreAnalysis);
		//用户肤质统计
		Map<String,Float> skinTextureAnalysis = new HashMap<>();
		params.put("skinTexture", SkinTextureEnum.DRY_SKIN.getCode());
		scoreAnalysis.put("drySkin", countProportion(commentInfoMapper.countUserSkinTexture(params), commentCount));
		params.put("skinTexture", SkinTextureEnum.NEUTRAL_SKIN.getCode());
		scoreAnalysis.put("neutralSkin", countProportion(commentInfoMapper.countUserSkinTexture(params), commentCount));
		params.put("skinTexture", SkinTextureEnum.OILY_SKIN.getCode());
		scoreAnalysis.put("oilySkin", countProportion(commentInfoMapper.countUserSkinTexture(params), commentCount));
		params.put("skinTexture", SkinTextureEnum.MIXED_SKIN.getCode());
		scoreAnalysis.put("mixedSkin", countProportion(commentInfoMapper.countUserSkinTexture(params), commentCount));
		params.put("skinTexture", SkinTextureEnum.SENSITIVE_SKIN.getCode());
		scoreAnalysis.put("sensitiveSkin", countProportion(commentInfoMapper.countUserSkinTexture(params), commentCount));
		commentAnalysis.setScoreAnalysis(scoreAnalysis);
		//用户年龄统计
		Map<String,Float> ageAnalysis = new HashMap<>();
		Calendar c = Calendar.getInstance();
		int currentYear = c.get(Calendar.YEAR);
		int minYear = currentYear - 25;
		int maxYear = currentYear - 0;
		params.put("minYear", minYear);
		params.put("maxYear", maxYear);
		ageAnalysis.put("phase1", countProportion(commentInfoMapper.countUserAge(params), commentCount));
		minYear = currentYear - 30;
		maxYear = currentYear - 25;
		params.put("minYear", minYear);
		params.put("maxYear", maxYear);
		ageAnalysis.put("phase2", countProportion(commentInfoMapper.countUserAge(params), commentCount));
		minYear = currentYear - 40;
		maxYear = currentYear -30;
		params.put("minYear", minYear);
		params.put("maxYear", maxYear);
		ageAnalysis.put("phase3", countProportion(commentInfoMapper.countUserAge(params), commentCount));
		minYear = currentYear - 45;
		maxYear = currentYear -40;
		params.put("minYear", minYear);
		params.put("maxYear", maxYear);
		ageAnalysis.put("phase4", countProportion(commentInfoMapper.countUserAge(params), commentCount));
		minYear = currentYear - 100;
		maxYear = currentYear -45;
		params.put("minYear", minYear);
		params.put("maxYear", maxYear);
		ageAnalysis.put("phase5", countProportion(commentInfoMapper.countUserAge(params), commentCount));
		commentAnalysis.setAgeAnalysis(ageAnalysis);
		return commentAnalysis;
	}
	
	
	
	@Override
	public float countAvgScore(Long productId) {
		Float avgScore = commentInfoMapper.countAvgScore(productId);
		if(avgScore == null) {
			return 0F;
		}
		return avgScore;
	}
	
	
	@Override
	public long countCommentCount(Long productId) {
		Long commentCount = commentInfoMapper.countCommentByProductId(productId);
		if(commentCount == null){
			return 0L;
		}
		return commentCount;
	}

	/**
	 * 计算占比 
	 * @param a
	 * @param sum 总
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月29日 上午10:56:42
	 */
	private float countProportion(long a,long sum) {
		if(sum == 0) {
			return 0.0F;
		}
		float proportion = a/(float)sum;
		logger.debug(a+"/"+sum+"="+proportion);
		return (float)(Math.round(proportion*100))/100;
	}

	/**
	 * VO=>PO
	 * @param commentInfoVO
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月17日 下午12:47:04
	 */
	private CommentInfo commentInfoVO2CommentInfoPO(CommentInfoVO commentInfoVO) {
		CommentInfo commentInfoPO = new CommentInfo();
		commentInfoPO.setUserId(commentInfoVO.getUserId());
		commentInfoPO.setProductId(commentInfoVO.getProductId());
		commentInfoPO.setArticleTitle(commentInfoVO.getArticleTitle());
		commentInfoPO.setWorthMark(commentInfoVO.getWorthMark());
		commentInfoPO.setBuyWay(commentInfoVO.getBuyWay());
		commentInfoPO.setSendTime(new Date());
		return commentInfoPO;
	}
	
	/**
	 * PO=>VO
	 * @param commentInfoPO
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月17日 下午7:49:34
	 */
	private CommentInfoVO commentInfoPO2CommentInfoVO(CommentInfo commentInfoPO) {
		CommentInfoVO commentInfoVO = new CommentInfoVO();
		commentInfoVO.setUserId(commentInfoPO.getUserId());
		commentInfoVO.setCommentId(commentInfoPO.getCommentId());
		commentInfoVO.setProductId(commentInfoPO.getProductId());
		commentInfoVO.setArticleTitle(commentInfoPO.getArticleTitle());
		commentInfoVO.setWorthMark(commentInfoPO.getWorthMark());
		commentInfoVO.setBuyWay(commentInfoPO.getBuyWay());
		commentInfoVO.setContentText(commentInfoPO.getContentText());
		commentInfoVO.setContentHtml(commentInfoPO.getContentHtml());
		//购买方式转换
		if(StringUtils.isNotBlank(commentInfoPO.getBuyWay())) {
			commentInfoVO.setBuyWayName(BuyWayEnum.of(commentInfoPO.getBuyWay()).getName());
		}
		//发表时间转换
		if(commentInfoPO.getSendTime() != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String replyTime = sdf.format(commentInfoPO.getSendTime());
			commentInfoVO.setSendTime(replyTime);
			commentInfoVO.setContentHtml(commentInfoPO.getContentHtml());
		}
		//用户信息转换
		if(commentInfoPO.getUserInfo() != null) {
			UserInfo userInfo = commentInfoPO.getUserInfo();
			UserInfoVO userInfoVO = new UserInfoVO();
			userInfoVO.setUserId(userInfo.getUserId());
			userInfoVO.setUserName(userInfo.getUserName());
			userInfoVO.setNickname(userInfo.getNickname());
			userInfoVO.setGender(userInfo.getGender());
			//获取用户肤质
			if(StringUtils.isNotBlank(userInfo.getSkinTexture())) {
				SkinTextureEnum skinTexture = SkinTextureEnum.of(userInfo.getSkinTexture());
				if(skinTexture != null) {
					userInfoVO.setSkinTexture(skinTexture.getName());
				}
			}
			//获取用户年龄
			if(userInfo.getBornYear() != null) {
				userInfoVO.setAge(Calendar.getInstance().
					get(Calendar.YEAR)-userInfo.getBornYear());
			}
			
			userInfoVO.setIntroduction(userInfo.getIntroduction());
			userInfoVO.setPortrait(AppSetting.APP_ROOT+AppSetting.PORTRAIT_SAVED_PATH+userInfo.getPortrait());
			userInfoVO.setPhone(userInfo.getPhone());
			userInfoVO.setEmail(userInfo.getEmail());
			userInfoVO.setMembershipPoint(userInfo.getMembershipPoint());
			userInfoVO.setMemberLevel(userInfo.getMemberLevel());
			userInfoVO.setArea(userInfo.getArea());
			
			commentInfoVO.setUserInfo(userInfoVO);
		}
		//商品信息转换
		if(commentInfoPO.getProductInfo() != null) {
			ProductInfo productInfoPO = commentInfoPO.getProductInfo();
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
			commentInfoVO.setProductInfo(productInfoVO);
		}
		return commentInfoVO;
	}
}
