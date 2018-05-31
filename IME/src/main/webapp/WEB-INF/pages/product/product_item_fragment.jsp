<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/base.jsp"%>
<c:forEach items="${pageResult.list }" var="productInfo">
	<li class="product-item">
		<div class="left">
			<div class="product-img-wrapped">
				<img src="${productInfo.coverImage}">
			</div>
		</div>
		<div class="right">
			<p>
				<a class="product-name" target="_blank"
					href="${basePath }product/info/${productInfo.productId}.html">${productInfo.productName }</a>
			</p>
			<p class="rating-p">
				<span class="rating-label">综合评价</span><span class="rating-wrapped"><input
					value="${productInfo.avgScore }" type="number" class="rating" min=0 max=5 step=0.5
					data-size="xxs" data-only-show="true" data-symbol="&#xe005;"></span>
			</p>
			<p class="smaller-p">
				<span class="p-label">参考价格：</span><span class="p-value">${productInfo.referencePrice }</span>
				<span class="p-label">点评数：</span><span class="p-value">${productInfo.commentCount }</span>
			</p>
			<div class="product-desc">
				<p class="product-introduction">
					<span>产品简介：</span> <a target="_blank" 
						href="${basePath }product/info/${productInfo.productId}.html">${productInfo.desc }</a>
				</p>
				<p class="product-newest-comment">
					<span>最新点评：</span> 
					<c:choose>
						<c:when test="${productInfo.lastestComment != null }">
							<a target="_blank" href="${basePath }comment/info/${productInfo.lastestComment.commentId }.html">${productInfo.lastestComment.contentText }</a>
						</c:when>
						<c:otherwise>
							暂时没有相关点评
						</c:otherwise>
					</c:choose>
				</p>
			</div>
		</div>
	</li>
</c:forEach>