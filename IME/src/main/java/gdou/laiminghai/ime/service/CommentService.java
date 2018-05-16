package gdou.laiminghai.ime.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 使用心得业务接口
 * @ClassName: CommentService
 * @author: laiminghai
 * @datetime: 2018年5月16日 下午9:38:09
 */
public interface CommentService {
	/**
	 * 临时保存使用心得图片
	 * @param imgFile
	 * @param savedPath
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月16日 下午9:40:45
	 */
	public String saveCommentPicture(MultipartFile imgFile,String savedPath);
}
