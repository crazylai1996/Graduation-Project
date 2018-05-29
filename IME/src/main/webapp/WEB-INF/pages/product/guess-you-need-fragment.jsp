<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/base.jsp"%>
<c:forEach items="${recProductList }" var="productInfo">
	<li><a
		href="${basePaht }product/info/${productInfo.productId}.html"> <img
			src="${productInfo.coverImage }"> <span class="commodity-title">
				${productInfo.productName } </span></a></li>
</c:forEach>