package gdou.laiminghai.ime.dao.lucene;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.IOContext;
import org.apache.lucene.store.RAMDirectory;

public class BaseIndexDao {
	
	private static final Logger logger = Logger.getLogger(BaseIndexDao.class);
	
	/**
	 * 全量同步
	 * @param fileNames
	 * @param path
	 * @author: laiminghai
	 * @datetime: 2018年5月24日 下午9:39:30
	 */
	protected void fullSync(RAMDirectory ramDirectory,Collection<String> fileNames, Path path) {
        Directory to;
        logger.info("开始全量同步");
        try {
            to = FSDirectory.open(path);
            // 全量备份，直接清空拷贝
            for (File file : path.toFile().listFiles()) {
                file.delete();
            }
            for (String fileName : fileNames) {
                to.copyFrom(ramDirectory, fileName, fileName, IOContext.DEFAULT);
            }
            to.close();
            logger.info("全量同步完成");
        } catch (IOException e) {
            logger.error("全量同步策略异常：",e);
        }
    }
	/**
	 * 增量同步
	 * @param fileNames
	 * @param path
	 * @author: laiminghai
	 * @datetime: 2018年5月24日 下午9:39:20
	 */
    protected void incrSync(RAMDirectory ramDirectory,Collection<String> fileNames, Path path) {
    	logger.info("开始增量同步");
        //fileNames被IndexCommit引用，需要重新构造set集合，进行移除操作
        Set<String> files = new HashSet<>(fileNames);
        for (File file : path.toFile().listFiles()) {
            if (files.contains(file.getName())) {
                //该索引已存在，则不拷贝
                files.remove(file.getName());
            } else {
                //删除已经过时的索引
                file.delete();
            }
        }
        //拷贝全部新增索引
        try {
            Directory to = FSDirectory.open(path);
            for (String file : files) {
                to.copyFrom(ramDirectory, file, file, IOContext.DEFAULT);
            }
            to.close();
            logger.info("增量同步完成");
        } catch (IOException e) {
            logger.error("增量同步策略异常：",e);
        }
    }
}
