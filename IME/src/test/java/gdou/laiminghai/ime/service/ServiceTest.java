package gdou.laiminghai.ime.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import gdou.laiminghai.ime.BaseTest;
import gdou.laiminghai.ime.common.task.EmailSendTask;
import gdou.laiminghai.ime.dao.mapper.CommentInfoMapper;
import gdou.laiminghai.ime.model.entity.CommentInfo;
import gdou.laiminghai.ime.model.vo.CommentInfoVO;

public class ServiceTest extends BaseTest{
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private CommentInfoMapper commentInfoMapper;
	
	@Autowired
	private UserBrowserRecordService browserRecordService;
	
	@Test
	public void testEmail() throws Exception {
		new Thread(new EmailSendTask("1284753520@qq.com", "测试", "赖明海测试")).start();
		Thread.sleep(4000);
	}
	
	@Test
	public void testFindLastestComment() {
		CommentInfoVO commentInfoVO = commentService.findLatestCommentByProductId(6L);
		System.out.println(commentInfoVO.toString());
	}
	
	@Test
	public void testBrowserRecordCount() {
		Long count = browserRecordService.countBrowserByProductId(6L);
		System.out.println(count);
	}
	
	@Test
	public void testCommentCount() {
		Long count = commentService.countCommentByProductId(6L);
		System.out.println(count);
	}
	
	@Test
	public void testCommentDetail() {
//		CommentInfoVO comment = commentService.getCommentInfo(12L);
//		System.out.println(comment.toString());
		CommentInfo comment2 = commentInfoMapper.selectByPrimaryKey(12L);
		System.out.println(comment2.getContentHtml());
	}
}
