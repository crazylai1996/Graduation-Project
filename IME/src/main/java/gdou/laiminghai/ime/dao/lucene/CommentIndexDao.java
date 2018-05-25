package gdou.laiminghai.ime.dao.lucene;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexCommit;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.KeepOnlyLastCommitDeletionPolicy;
import org.apache.lucene.index.SnapshotDeletionPolicy;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.IOContext;
import org.apache.lucene.store.RAMDirectory;
import org.springframework.beans.factory.annotation.Autowired;

import gdou.laiminghai.ime.common.setting.AppSetting;
import gdou.laiminghai.ime.dao.mapper.CommentInfoMapper;
import gdou.laiminghai.ime.model.entity.CommentInfo;
import gdou.laiminghai.ime.model.vo.CommentInfoVO;

public class CommentIndexDao {
	//日志记录
	private static final Logger logger = Logger.getLogger(CommentIndexDao.class);
	
	private static RAMDirectory ramDirectory;
	private static IndexWriter ramWriter;
	
	private CommentInfoMapper commentInfoMapper;
	
	@Autowired
	public CommentIndexDao(CommentInfoMapper commentInfoMapper) {
		this.commentInfoMapper = commentInfoMapper;
		this.init();
	}
	
	//初始化
	private void init(){
		try {
			logger.info("加载磁盘索引");
			if(AppSetting.INDEX_RECREATE) {
				this.recreateAllIndex();
			}
			FSDirectory fsDirectory = FSDirectory.open(Paths.get(AppSetting.COMMENT_INDEX_PATH));
			ramDirectory = new RAMDirectory(fsDirectory,IOContext.READONCE);
			fsDirectory.close();
			
			IndexWriterConfig indexWriterConfig = new IndexWriterConfig(
					new SmartChineseAnalyzer());//中文分词器
			indexWriterConfig.setIndexDeletionPolicy(
					new SnapshotDeletionPolicy(new KeepOnlyLastCommitDeletionPolicy()));
			
			ramWriter = new IndexWriter(ramDirectory, indexWriterConfig);
			logger.info("加载磁盘索引完成");
		} catch (IOException e) {
			logger.error("加载索引异常：",e);
		}
	}
	
	/**
	 * 新建索引 
	 * @param commentInfo
	 * @author: laiminghai
	 * @datetime: 2018年5月24日 下午8:52:53
	 */
	public void addCommentIndex(CommentInfo commentInfo) {
		try {
			ramWriter.addDocument(comment2Document(commentInfo));
			ramWriter.commit();
		} catch (IOException e) {
			logger.error("新建心得索引异常：",e);
		}
	}
	
	/**
	 * 删除索引
	 * @param commentId
	 * @author: laiminghai
	 * @datetime: 2018年5月24日 下午8:54:44
	 */
	public void deleteCommentIndex(Long commentId) {
		Term term = new Term("ID", String.valueOf(commentId));
		try {
			ramWriter.deleteDocuments(term);
			ramWriter.commit();
		} catch (IOException e) {
			logger.error("删除索引异常：",e);
		}
	}
	
	/**
	 * 更新索引 
	 * @param commentInfo
	 * @author: laiminghai
	 * @datetime: 2018年5月24日 下午8:55:40
	 */
	public void updateCommentIndex(CommentInfo commentInfo) {
		Term term = new Term("ID",String.valueOf(commentInfo.getCommentId()));
		try {
			ramWriter.updateDocument(term, comment2Document(commentInfo));
			ramWriter.commit();
		} catch (IOException e) {
			logger.error("更新索引异常：",e);
		}
	}
	
	/**
	 * 分页搜索
	 * @param keyword
	 * @param pageNum
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月24日 下午9:27:44
	 */
	public List<CommentInfoVO> searchByPage(String keyword,int pageNum){
		List<CommentInfoVO> commentResults = new ArrayList<>();
		try {
			IndexSearcher searcher = new IndexSearcher(DirectoryReader.open(ramDirectory));
			String[] fields = { "TITLE", "CONTENT" };
			Analyzer analyzer = new SmartChineseAnalyzer();
			QueryParser queryParser = new MultiFieldQueryParser(fields, analyzer);
			Query query = queryParser.parse(keyword);
			ScoreDoc preScore = null;
			if (pageNum > 1) {
				int start = (pageNum - 1) * AppSetting.NUMBER_PER_PAGE;
				// 查询数据， 结束页面自前的数据都会查询到，但是只取本页的数据
				TopDocs topDocs = searcher.search(query, start);
				//获取到上一页最后一条
				preScore = topDocs.scoreDocs[start - 1];
			}
			TopDocs resultDocs = searcher.searchAfter(preScore, query, AppSetting.NUMBER_PER_PAGE);
			//搜索结果高亮
			SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<b><font color='red'>", "</font></b>");
			Highlighter highlighter = new Highlighter(simpleHTMLFormatter, new QueryScorer(query));
			for (ScoreDoc scoreDoc : resultDocs.scoreDocs) {
				CommentInfoVO commentInfoVO = new CommentInfoVO();
				Document document = searcher.doc(scoreDoc.doc);
				Long id = Long.parseLong(document.get("ID"));
				String title = document.get("TITLE");
				String content = document.get("CONTENT");
				String titleHtml = highlighter.getBestFragment(analyzer.tokenStream("TITLE", new StringReader(title)),
						title);
				String contentHtml = highlighter
						.getBestFragment(analyzer.tokenStream("CONTENT", new StringReader(content)), content);
				commentInfoVO.setCommentId(id);
				commentInfoVO.setArticleTitle(titleHtml);
				commentInfoVO.setContentText(contentHtml);
				commentResults.add(commentInfoVO);
			} 
		} catch (Exception e) {
			logger.error("搜索异常：",e);
		}
		return commentResults;
	}
	
	/**
	 * Comment=>Doucment
	 * @param commentInfo
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月24日 下午8:50:14
	 */
	private Document comment2Document(CommentInfo commentInfo) {
		Document doc = new Document();
		doc.add(new StringField("ID",String.valueOf(commentInfo.getCommentId()),Field.Store.YES));
		doc.add(new Field("TITLE", commentInfo.getArticleTitle(), TextField.TYPE_STORED));
		doc.add(new Field("CONTENT", commentInfo.getContentText(),TextField.TYPE_STORED));
		return doc;
	}
	
	/**
	 * 索引同步
	 * @author: laiminghai
	 * @datetime: 2018年5月24日 下午9:32:21
	 */
	public void indexSync() {
		IndexWriterConfig config;
        SnapshotDeletionPolicy indexDeletionPolicy = null;
        IndexCommit snapshot = null;
        try {
            ramWriter.commit();
            config = (IndexWriterConfig) ramWriter.getConfig();
            indexDeletionPolicy = (SnapshotDeletionPolicy) config.getIndexDeletionPolicy();
            snapshot = indexDeletionPolicy.snapshot();
            config.setIndexCommit(snapshot);
            Collection<String> fileNames = snapshot.getFileNames();
            Path path = Paths.get(AppSetting.COMMENT_INDEX_PATH);
            //配置文件决定全量还是增量
            if("INCR".equals(AppSetting.INDEX_SYNC_STRATEGY)) {
            	this.incrSync(fileNames, path);
            }else if ("FULL".equals(AppSetting.INDEX_SYNC_STRATEGY)) {
				this.fullSync(fileNames, path);
			}else {
				logger.info("不同步");
			}
        }catch (IOException e) {
			logger.error("同步失败");
		}
	}
	
	/**
	 * 全量同步
	 * @param fileNames
	 * @param path
	 * @author: laiminghai
	 * @datetime: 2018年5月24日 下午9:39:30
	 */
	private void fullSync(Collection<String> fileNames, Path path) {
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
    private void incrSync(Collection<String> fileNames, Path path) {
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
    
    /**
     * 重建所有索引
     * @author: laiminghai
     * @datetime: 2018年5月24日 下午10:14:37
     */
    public void recreateAllIndex() {
    	logger.info("重建所有索引");
    	try {
			Path path = Paths.get(AppSetting.COMMENT_INDEX_PATH);
			//删除原有索引文件
			for (File file : path.toFile().listFiles()) {
				file.delete();
			}
			FSDirectory fsDirectory = FSDirectory.open(path);
			Analyzer analyzer = new SmartChineseAnalyzer();//中文分词器
			IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
			IndexWriter writer = new IndexWriter(fsDirectory, indexWriterConfig);
			List<CommentInfo> list = commentInfoMapper.selectAll();
			for (CommentInfo commentInfo : list) {
				writer.addDocument(comment2Document(commentInfo));
			}
			writer.close();
			logger.info("重建所有索引完成");
		} catch (Exception e) {
			logger.error("重建所有索引异常：",e);
		}
    }
}
