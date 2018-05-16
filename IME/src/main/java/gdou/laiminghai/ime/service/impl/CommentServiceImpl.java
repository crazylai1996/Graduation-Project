package gdou.laiminghai.ime.service.impl;

import java.io.File;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import gdou.laiminghai.ime.common.util.FileUtil;
import gdou.laiminghai.ime.service.CommentService;
import net.coobird.thumbnailator.Thumbnails;

@Service
public class CommentServiceImpl implements CommentService {
	
	//日志记录
	private static final Logger logger = Logger.getLogger(CommentServiceImpl.class);

	@Override
	public String saveCommentPicture(MultipartFile imgFile, String savedPath) {
		logger.debug("使用心得图片临时保存地址："+savedPath);
		String fileBody = UUID.randomUUID().toString().replaceAll("-", "");
		//获取文件名后缀
		int fileExtIndex = imgFile.getOriginalFilename().lastIndexOf(".");
		String fileExt = imgFile.getOriginalFilename().substring(fileExtIndex);
		//重命名图片文件
		String fileName = fileBody + fileExt;
//		boolean success = FileUtil.save(imgFile, savedPath, fileName);
		try {
			//图片压缩并保存
			File targetDir = new File(savedPath);
			if (!targetDir.exists()) {
				targetDir.mkdirs();
			}
			Thumbnails.of(imgFile.getInputStream()).scale(1f).outputQuality(0.25f).toFile(savedPath + fileName); 
		}catch(Exception e) {//图片文件保存失败
			fileName = "";
			logger.error("使用心得图片保存失败：",e);
		}
		return fileName;
	}

}
