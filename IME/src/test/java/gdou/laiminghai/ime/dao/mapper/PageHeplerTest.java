package gdou.laiminghai.ime.dao.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import gdou.laiminghai.ime.BaseTest;
import gdou.laiminghai.ime.common.setting.AppSetting;
import gdou.laiminghai.ime.model.entity.CommentReply;
import gdou.laiminghai.ime.model.entity.CommentReplyVO;
import gdou.laiminghai.ime.service.CommentReplyService;
import gdou.laiminghai.ime.service.impl.CommentReplyServiceImpl;


public class PageHeplerTest extends BaseTest{
	
	@Autowired
	private CommentReplyMapper commentReplyMapper;
	
	@Autowired
	private CommentReplyService commentReplyService;
	
	private CommentReplyServiceImpl crs = new CommentReplyServiceImpl();
	
	@Test
	public void testPageHelper() {
//		PageHelper.startPage(1, 1);
//		List<CommentReply> list = commentReplyMapper.selectAll();
//		PageInfo<CommentReply> pageInfo = new PageInfo<CommentReply>(list);
//		System.out.println(pageInfo.toString());
		PageHelper.startPage(1, 1);
//		List<CommentReplyVO> commentReplyVOList = commentReplyService.findCommentReplyList(5L);
//		PageInfo<CommentReplyVO> pageInfo = new PageInfo<CommentReplyVO>(commentReplyVOList);
		Map<String,Object> map = new HashMap<>();
		map.put("commentId", 5);
		List<CommentReply> list = commentReplyMapper.selectByCondition(map);
		List<CommentReplyVO> list2 = new ArrayList<>();
		for (CommentReply commentReplyPO : list) {
			list2.add(crs.commentReplyPO2CommentReplyVO(commentReplyPO));
		}
		PageInfo<CommentReplyVO> pageInfo = new PageInfo<CommentReplyVO>(list2);
		System.out.println(pageInfo.toString());
	}
	
	@Test
	public void testStringSub() {
		String str = "中文";
		System.out.println(str.substring(0, 1));
	}
}
