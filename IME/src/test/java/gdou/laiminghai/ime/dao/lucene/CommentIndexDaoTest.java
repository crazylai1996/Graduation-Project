package gdou.laiminghai.ime.dao.lucene;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import gdou.laiminghai.ime.BaseTest;

public class CommentIndexDaoTest extends BaseTest {
	@Autowired
	private CommentIndexDao commentIndexDao;
	
	@Test
	public void testRecreateAllIndex() {
		commentIndexDao.recreateAllIndex();
	}
}
