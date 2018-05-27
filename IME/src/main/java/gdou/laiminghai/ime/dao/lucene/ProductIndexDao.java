package gdou.laiminghai.ime.dao.lucene;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.IOContext;
import org.apache.lucene.store.RAMDirectory;
import org.springframework.beans.factory.annotation.Autowired;

import gdou.laiminghai.ime.common.setting.AppSetting;
import gdou.laiminghai.ime.model.vo.ProductInfoVO;
import gdou.laiminghai.ime.service.ProductService;

public class ProductIndexDao extends BaseIndexDao {
	// 日志记录
	private static final Logger logger = Logger.getLogger(ProductIndexDao.class);

	private static RAMDirectory ramDirectory;
	private static IndexWriter ramWriter;

	private ProductService productService;

	@Autowired
	public ProductIndexDao(ProductService productService) {
		this.productService = productService;
		this.init();
	}

	// 初始化
	private void init() {
		try {
			logger.info("加载磁盘商品索引");
			if (AppSetting.PRODUCT_INDEX_RECREATE) {
				this.recreateAllIndex();
			}
			FSDirectory fsDirectory = FSDirectory.open(Paths.get(AppSetting.PRODUCT_INDEX_PATH));
			ramDirectory = new RAMDirectory(fsDirectory, IOContext.READONCE);
			fsDirectory.close();

			IndexWriterConfig indexWriterConfig = new IndexWriterConfig(new SmartChineseAnalyzer());// 中文分词器
			indexWriterConfig
					.setIndexDeletionPolicy(new SnapshotDeletionPolicy(new KeepOnlyLastCommitDeletionPolicy()));

			ramWriter = new IndexWriter(ramDirectory, indexWriterConfig);
			logger.info("加载磁盘商品索引完成");
		} catch (IOException e) {
			logger.error("加载商品索引异常：", e);
		}
	}

	/**
	 * 新建商品索引
	 * 
	 * @param productInfoVO
	 * @author: laiminghai
	 * @datetime: 2018年5月27日 上午7:59:00
	 */
	public void addProductIndex(ProductInfoVO productInfoVO) {
		try {
			ramWriter.addDocument(product2Document(productInfoVO));
			ramWriter.commit();
		} catch (IOException e) {
			logger.error("新建商品索引异常：", e);
		}
	}

	/**
	 * 删除商品索引
	 * 
	 * @param productId
	 * @author: laiminghai
	 * @datetime: 2018年5月27日 上午7:59:28
	 */
	public void deleteProductIndex(Long productId) {
		Term term = new Term("ID", String.valueOf(productId));
		try {
			ramWriter.deleteDocuments(term);
			ramWriter.commit();
		} catch (IOException e) {
			logger.error("删除商品索引异常：", e);
		}
	}

	/**
	 * 更新商品索引
	 * 
	 * @param productInfoVO
	 * @author: laiminghai
	 * @datetime: 2018年5月27日 上午8:00:15
	 */
	public void updateCommentIndex(ProductInfoVO productInfoVO) {
		Term term = new Term("ID", String.valueOf(productInfoVO.getProductId()));
		try {
			ramWriter.updateDocument(term, product2Document(productInfoVO));
			ramWriter.commit();
		} catch (IOException e) {
			logger.error("更新商品索引异常：", e);
		}
	}

	/**
	 * 搜索商品索引 
	 * @param params 关键字
	 * @param pageNum
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月27日 上午8:50:21
	 */
	public List<ProductInfoVO> searchByPage(Map<String, String> params, int pageNum) {
		logger.debug("搜索关键字："+params.toString());
		List<ProductInfoVO> productResults = new ArrayList<>();
		try {
			IndexSearcher searcher = new IndexSearcher(DirectoryReader.open(ramDirectory));
			Analyzer analyzer = new SmartChineseAnalyzer();
			// 多条件查询
			BooleanQuery.Builder builder = new BooleanQuery.Builder();
			//关键字
			if (params.containsKey("keyword")) {
				QueryParser parser = new QueryParser("NAME", analyzer);
				Query query = parser.parse(params.get("keyword"));
				builder.add(query, Occur.SHOULD);

				QueryParser parser2 = new QueryParser("DESC", analyzer);
				Query query2 = parser2.parse(params.get("keyword"));
				builder.add(query2, Occur.SHOULD);
			}
			//品牌
			if (params.containsKey("brand")) {
				QueryParser parser = new QueryParser("BRAND", analyzer);
				Query query = parser.parse(params.get("brand"));
				builder.add(query, Occur.MUST);
			}
			//分类
			if (params.containsKey("class")) {
				QueryParser parser = new QueryParser("CLASS", analyzer);
				Query query = parser.parse(params.get("class"));
				builder.add(query, Occur.MUST);
			}
			//属性
			if (params.containsKey("property")) {
				QueryParser parser = new QueryParser("PROPERTY", analyzer);
				Query query = parser.parse(params.get("property"));
				builder.add(query, Occur.MUST);
			}
			//功效
			if (params.containsKey("effect")) {
				QueryParser parser = new QueryParser("EFFECT", analyzer);
				Query query = parser.parse(params.get("effect"));
				builder.add(query, Occur.MUST);
			}
			BooleanQuery query = builder.build();
			ScoreDoc preScore = null;
			if (pageNum > 1) {
				int start = (pageNum - 1) * AppSetting.NUMBER_PER_PAGE;
				// 查询数据， 结束页面自前的数据都会查询到，但是只取本页的数据
				TopDocs topDocs = searcher.search(query, start);
				// 获取到上一页最后一条
				preScore = topDocs.scoreDocs[start - 1];
			}
			TopDocs resultDocs = searcher.searchAfter(preScore, query, AppSetting.NUMBER_PER_PAGE);
			// 搜索结果高亮
			SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<b><font color='red'>", "</font></b>");
			Highlighter highlighter = new Highlighter(simpleHTMLFormatter, new QueryScorer(query));
			for (ScoreDoc scoreDoc : resultDocs.scoreDocs) {
				ProductInfoVO productInfoVO = new ProductInfoVO();
				Document document = searcher.doc(scoreDoc.doc);
				Long id = Long.parseLong(document.get("ID"));
				String name = document.get("NAME");
				String desc = document.get("DESC");
				String nameHtml = highlighter.getBestFragment(analyzer.tokenStream("NAME", new StringReader(name)),
						name);
				String descHtml = highlighter.getBestFragment(analyzer.tokenStream("DESC", new StringReader(desc)),
						desc);
				productInfoVO.setProductId(id);
				productInfoVO.setProductName(nameHtml);
				productInfoVO.setDesc(descHtml);
				productResults.add(productInfoVO);
			}
		} catch (Exception e) {
			logger.error("商品搜索异常：", e);
		}
		return productResults;
	}

	/**
	 * entity=>document
	 * @param productInfoVO
	 * @return
	 * @author: laiminghai
	 * @datetime: 2018年5月27日 上午8:26:49
	 */
	private Document product2Document(ProductInfoVO productInfoVO) {
		Document doc = new Document();
		doc.add(new StringField("ID", String.valueOf(productInfoVO.getProductId()), Field.Store.YES));
		doc.add(new Field("NAME", productInfoVO.getProductName(), TextField.TYPE_STORED));
		doc.add(new Field("DESC", productInfoVO.getDesc(), TextField.TYPE_STORED));
		doc.add(new Field("CLASS", productInfoVO.getClassifyName(), TextField.TYPE_STORED));
		doc.add(new Field("PROPERTY", productInfoVO.getPropertyName(), TextField.TYPE_STORED));
		doc.add(new Field("EFFECT", productInfoVO.getEffectName(), TextField.TYPE_STORED));
		doc.add(new Field("BRAND", productInfoVO.getBrandName(), TextField.TYPE_STORED));
		return doc;
	}

	/**
	 * 索引同步
	 * 
	 * @author: laiminghai
	 * @datetime: 2018年5月27日 上午8:11:17
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
			Path path = Paths.get(AppSetting.PRODUCT_INDEX_PATH);
			// 配置文件决定全量还是增量
			if ("INCR".equals(AppSetting.PRODUCT_INDEX_SYNC_STRATEGY)) {
				this.incrSync(ramDirectory, fileNames, path);
			} else if ("FULL".equals(AppSetting.PRODUCT_INDEX_SYNC_STRATEGY)) {
				this.fullSync(ramDirectory, fileNames, path);
			} else {
				logger.info("不同步");
			}
		} catch (IOException e) {
			logger.error("同步失败");
		} finally {
			try {
				indexDeletionPolicy.release(snapshot);
			} catch (IOException e) {
				logger.error("释放失败");
			}
		}
	}

	/**
	 * 重建所有商品索引
	 * 
	 * @author: laiminghai
	 * @datetime: 2018年5月27日 上午8:10:58
	 */
	public void recreateAllIndex() {
		logger.info("重建所有索引");
		try {
			Path path = Paths.get(AppSetting.PRODUCT_INDEX_PATH);
			// 删除原有索引文件
			for (File file : path.toFile().listFiles()) {
				file.delete();
			}
			FSDirectory fsDirectory = FSDirectory.open(path);
			Analyzer analyzer = new SmartChineseAnalyzer();// 中文分词器
			IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
			IndexWriter writer = new IndexWriter(fsDirectory, indexWriterConfig);
			List<ProductInfoVO> list = productService.findAll();
			logger.info(list.toString());
			for (ProductInfoVO productInfo : list) {
				logger.debug("添加商品索引："+productInfo.toString());
				writer.addDocument(product2Document(productInfo));
			}
			writer.commit();
			writer.close();
			logger.info("重建所有商品索引完成");
		} catch (Exception e) {
			logger.error("重建所有商品索引异常：", e);
		}
	}
}
