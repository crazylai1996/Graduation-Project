package gdou.laiminghai.ime.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import gdou.laiminghai.ime.model.dto.PageResult;
import gdou.laiminghai.ime.model.vo.CommentAnalysisVO;
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
	
	/**
	 * 查询用户关注的使用心得
	 * @param map
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月23日 下午10:55:15
	 */
	PageResult<CommentInfoVO> findMyFollowedComments(Map<String,Object> map);
	
	/**
	 * 查找最新点评 
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月24日 上午7:47:15
	 */
	PageResult<CommentInfoVO> findNewestComments(Map<String,Object> map);
	
	/**
	 * 分页搜索心得
	 * @param pageNum
	 * @param keyword
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月24日 下午11:31:39
	 */
	PageResult<CommentInfoVO> searchCommentsByPage(int pageNum,String keyword);
	
	/**
	 * 查找商品最近的一个点评
	 * @param productId
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月27日 下午8:40:19
	 */
	CommentInfoVO findLatestCommentByProductId(Long productId);
	
	/**
	 * 统计商品心得数量
	 * @param productId
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月29日 上午12:49:58
	 */
	Long countCommentByProductId(Long productId);
	
	/**
	 * 商品点评统计分析
	 * @param productId
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月29日 上午10:30:33
	 */
	CommentAnalysisVO commentAnalyze(Long productId);
	
	/**
	 * 统计综合评分
	 * @param productId
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月31日 上午9:19:12
	 */
	float countAvgScore(Long productId);
	
	/**
	 * 统计点评量
	 * @param productId
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月31日 下午3:42:08
	 */
	long countCommentCount(Long productId);
}
