package gdou.laiminghai.ime.common.task;

import org.springframework.beans.factory.annotation.Autowired;

import gdou.laiminghai.ime.dao.lucene.CommentIndexDao;
import gdou.laiminghai.ime.dao.lucene.ProductIndexDao;

public class IndexSyncTask {
	
	@Autowired
	private ProductIndexDao productIndexDao;
	
	@Autowired
	private CommentIndexDao commentIndexDao;

	public void syncIndex() {
		productIndexDao.indexSync();
		commentIndexDao.indexSync();
	}
}
