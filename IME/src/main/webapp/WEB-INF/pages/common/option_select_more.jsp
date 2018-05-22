<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/base.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=Edge">
  	<base href="${basePath }">
    <title>选项列表</title>
    <link rel="stylesheet" href="static\css\option_select.css">
  </head>
  <body>
    <ul class="option-list">
    	<c:forEach items="${selectOptions }" var="option">
    		<c:choose>
    			<c:when test="${option.selected }">
    				<li><a class="selected-option" href="javascript:void(0);" data-value="${option.code }">${option.name }</a></li>
    			</c:when>
    			<c:otherwise>
    				<li><a href="javascript:void(0);" data-value="${option.code }">${option.name }</a></li>
    			</c:otherwise>
    		</c:choose>
    		
    	</c:forEach>
    </ul>

    <script src="static\js\jquery.js"></script>
    <script src="static\js\option_select_more.js"></script>
  </body>
</html>
