package gdou.laiminghai.ime.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

import gdou.laiminghai.ime.common.setting.AppSetting;
import gdou.laiminghai.ime.service.CommentService;

/**
 * 使用心得控制器
 * 
 * @ClassName: CommentController
 * @author: laiminghai
 * @datetime: 2018年5月16日 下午8:32:05
 */
@Controller
@RequestMapping("/comment")
public class CommentController {

	// 日志记录
	private static final Logger logger = Logger.getLogger(CommentController.class);

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private CommentService commentService;

	/**
	 * 评论心得图片上传
	 * 
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月16日 下午8:36:42
	 */
	@ResponseBody
	@RequestMapping("/pictureUpload.do")
	public JSONObject uploadCommentPicture(@RequestParam(value = "imgFile", required = true) MultipartFile imgFile) {
		logger.debug("上传的文件名："+imgFile.getOriginalFilename());
		logger.debug("上传的文件大小："+imgFile.getSize());
		HttpSession session = request.getSession();
		String savedPath = session.getServletContext().getRealPath("/" + AppSetting.COMMENT_PICTURE_TMP_PATH);
		JSONObject resultJSON = new JSONObject();
		//允许的上传格式
		List<String> extList = Arrays.asList(AppSetting.COMMENT_PICTURE_FORMAT.split(","));
		//上传的文件格式
		String originalFilename = imgFile.getOriginalFilename();
		String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
		Long fileSize = imgFile.getSize();
		String message = "";
		//文件格式限制 
		if(!extList.contains(extName)) {
			resultJSON.put("error", 1);
			message = "上传失败，文件格式不被允许";
		}else {
			//文件大小限制
			if(fileSize > AppSetting.COMMENT_PICTURE_SIZE) {
				resultJSON.put("error", 1);
				message = "上传失败，单张图片文件大小不能超过2MB";
			}
		}
		//文件校验是否通过
		if(StringUtils.isBlank(message)) {
			String pictureName = commentService.saveCommentPicture(imgFile, savedPath);
			//保存失败
			if (StringUtils.isBlank(pictureName)) {
				resultJSON.put("error", 1);
				message = "图片上传失败";
			} else {
				//保存已经上传的使用心得图片
				List<String> uploadPictures = (List<String>)session.getAttribute("uploadPictures");
				if(uploadPictures == null) {
					uploadPictures = new ArrayList<>();
				}
				uploadPictures.add(pictureName);
				session.setAttribute("uploadPictures", uploadPictures);
				//上传的图片地址，用于前端显示
				String pictureUrl = AppSetting.APP_ROOT + AppSetting.COMMENT_PICTURE_TMP_PATH + pictureName;
				resultJSON.put("error", 0);
				resultJSON.put("url", pictureUrl);
			}
		}else {
			resultJSON.put("message", message);
		}
		return resultJSON;
	}
}
