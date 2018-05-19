package gdou.laiminghai.ime.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import gdou.laiminghai.ime.model.dto.PageResult;
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
	String saveCommentPicture(MultipartFile imgFile,String savedPath);
	/**
	 * 添加使用心得
	 * @param commentInfoVO
	 * @param commentPictures
	 * @param tmpPath
	 * @param savedPath
	 * @author: laiminghai
	 * @datetime: 2018年5月17日 下午12:29:16
	 */
	void addNewComment(CommentInfoVO commentInfoVO,List<String> commentPictures,
			String tmpPath,String savedPath);
	/**
	 * 获取心得详情信息
	 * @param commentId
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月17日 下午7:07:29
	 */
	CommentInfoVO getCommentInfo(Long commentId);
	/**
	 * 条件查询心得列表
	 * @param map
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月19日 上午12:43:05
	 */
	PageResult<CommentInfoVO> findCommentList(Map<String,Object> map);
}
