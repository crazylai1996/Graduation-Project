package gdou.laiminghai.ime.service.impl;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.xml.stream.events.Comment;

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
import gdou.laiminghai.ime.dao.mapper.CommentInfoMapper;
import gdou.laiminghai.ime.model.dto.PageResult;
import gdou.laiminghai.ime.model.entity.CommentInfo;
import gdou.laiminghai.ime.model.entity.CommentPicture;
import gdou.laiminghai.ime.model.entity.UserInfo;
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
		contentText = contentText.replaceAll("<img[^>]*/>", "");
		String contentHtml = commentInfoVO.getContentHtml();
		CommentInfo commentInfoPO = commentInfoVO2CommentInfoPO(commentInfoVO);
		if(commentPictures != null && commentPictures.size() > 0) {
			// 替换源文本内容的目录
			contentHtml.replace(AppSetting.COMMENT_PICTURE_TMP_PATH, AppSetting.COMMENT_PICTURE_SAVED_PATH);
			commentInfoPO.setContentHtml(contentHtml);
		}
		// 插入到数据库
		commentInfoMapper.insert(commentInfoPO);
		// 将心得图片从临时图片目录移动到正式目录
		for (String pictureName : commentPictures) {
			if (contentText.contains(pictureName)) {
				FileUtil.moveFile(new File(tmpPath, pictureName), new File(savedPath, pictureName));
				commentPictureService.addNewCommentPicture(
						new CommentPicture(commentInfoPO.getCommentId(),pictureName));
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
		//查找用户上一篇心得
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("commentId", commentInfoVO.getCommentId());
		map.put("userId", commentInfoVO.getUserId());
		CommentInfo lastCommentInfo = commentInfoMapper.findLastComment(map);
		if(lastCommentInfo != null) {
			commentInfoVO.setLastCommentId(lastCommentInfo.getCommentId());
			commentInfoVO.setLastCommentTitle(lastCommentInfo.getArticleTitle());
		}
		//查找用户下一篇心得
		CommentInfo nextCommentInfo = commentInfoMapper.findNextComment(map);
		if(nextCommentInfo != null) {
			commentInfoVO.setNextCommentId(nextCommentInfo.getCommentId());
			commentInfoVO.setNextCommentTitle(nextCommentInfo.getArticleTitle());
		}
		//查找用户最近三个心得记录
		map.put("productId", commentInfoVO.getProductId());
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
		return commentInfoVO;
	}
}
