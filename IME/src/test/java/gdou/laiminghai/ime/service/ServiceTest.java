package gdou.laiminghai.ime.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import gdou.laiminghai.ime.BaseTest;
import gdou.laiminghai.ime.common.task.EmailSendTask;
import gdou.laiminghai.ime.model.vo.CommentInfoVO;

public class ServiceTest extends BaseTest{
	
	@Autowired
	private CommentService commentService;
	
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
}
