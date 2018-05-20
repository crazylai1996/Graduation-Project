package gdou.laiminghai.ime.service;

import org.junit.Test;

import gdou.laiminghai.ime.BaseTest;
import gdou.laiminghai.ime.common.task.EmailSendTask;

public class ServiceTest extends BaseTest{
	
	@Test
	public void testEmail() throws Exception {
		new Thread(new EmailSendTask("1284753520@qq.com", "测试", "赖明海测试")).start();
		Thread.sleep(4000);
	}
}
