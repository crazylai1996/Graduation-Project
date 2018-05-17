package gdou.laiminghai.ime.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import gdou.laiminghai.ime.model.vo.CommentInfoVO;

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
	/**
	 * 添加使用心得
	 * @param commentInfoVO
	 * @param commentPictures
	 * @param tmpPath
	 * @param savedPath
	 * @author: laiminghai
	 * @datetime: 2018年5月17日 下午12:29:16
	 */
	public void addNewComment(CommentInfoVO commentInfoVO,List<String> commentPictures,
			String tmpPath,String savedPath);
}
