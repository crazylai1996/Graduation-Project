<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/base.jsp"%>
<c:forEach items="${searchResult.list}" var="commentInfoVO">
	<li><a class="item-title" href="${basePath }comment/info/${commentInfoVO.commentId }.html" target="_blank">${commentInfoVO.articleTitle }</a>
		<div class="item-contents">
			<a href="${basePath }product/info/${commentInfoVO.productInfo.productId }.html" target="_blank"><img src="${commentInfoVO.productInfo.coverImage }" alt="商品图片"></a>
			<div class="contents-right">
				<div class="contents-label">
					<span class="label-user">点评By：<a href="javascript:void(0);">${commentInfoVO.userInfo.nickname }</a></span>
					<span class="label-rank">性价评分：<a class="rank-point"
						href="javascript:void(0);">${commentInfoVO.worthMark }</a></span>
				</div>
				<div class="contents-detail">${commentInfoVO.contentText }</div>
				<div class="item-btns">
					<span class="time-label">${commentInfoVO.sendTime }</span> <a
						class="view-all" href="${basePath }comment/info/${commentInfoVO.commentId }.html" target="_blank">查看全文</a>
				</div>
			</div>
		</div></li>
</c:forEach>
