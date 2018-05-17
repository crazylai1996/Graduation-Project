package gdou.laiminghai.ime.service.impl;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import gdou.laiminghai.ime.common.exception.ServiceException;
import gdou.laiminghai.ime.common.exception.ServiceResultEnum;
import gdou.laiminghai.ime.common.setting.AppSetting;
import gdou.laiminghai.ime.common.util.FileUtil;
import gdou.laiminghai.ime.dao.mapper.CommentInfoMapper;
import gdou.laiminghai.ime.model.entity.CommentInfo;
import gdou.laiminghai.ime.model.entity.CommentPicture;
import gdou.laiminghai.ime.model.vo.CommentInfoVO;
import gdou.laiminghai.ime.model.vo.ProductInfoVO;
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
		String contentHtml = commentInfoVO.getContentHtml();
		CommentInfo commentInfoPO = commentInfoVO2CommentInfoPO(commentInfoVO);
		if(commentPictures != null && commentPictures.size() > 0) {
			// 替换源文本内容的目录
			contentText.replace(AppSetting.COMMENT_PICTURE_TMP_PATH, AppSetting.COMMENT_PICTURE_SAVED_PATH);
			contentHtml.replace(AppSetting.COMMENT_PICTURE_TMP_PATH, AppSetting.COMMENT_PICTURE_SAVED_PATH);
			commentInfoPO.setContentText(contentText);
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
		commentInfoVO.setSendTime(commentInfoPO.getSendTime());
		commentInfoVO.setContentHtml(commentInfoPO.getContentHtml());
		return commentInfoVO;
	}
}
