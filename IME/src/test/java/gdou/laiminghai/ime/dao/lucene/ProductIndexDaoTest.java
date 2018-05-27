package gdou.laiminghai.ime.dao.lucene;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import gdou.laiminghai.ime.BaseTest;
import gdou.laiminghai.ime.model.dto.PageResult;
import gdou.laiminghai.ime.model.vo.ProductInfoVO;
import gdou.laiminghai.ime.service.ProductService;

public class ProductIndexDaoTest extends BaseTest {
	
	@Autowired
	private ProductService productService;
	
	@Test
	public void testSearch() {
		Map<String,String> params = new HashedMap();
//		params.put("keyword", "测试");
//		params.put("effect", "抗氧化");
		PageResult<ProductInfoVO> pageResult = productService.searchProductsByPage(params, 1);
		for (ProductInfoVO p : pageResult.getList()) {
			System.out.println(p.toString());
		}
	}
}
