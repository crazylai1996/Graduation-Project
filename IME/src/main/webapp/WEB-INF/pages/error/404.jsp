<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<base href="${basePath }">
<title>404 Error</title>
<!--Custom Theme files-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!--//Custom Theme files -->
<link href="static/404/css/style.css" rel="stylesheet" type="text/css" media="all" />
</head>
<body>
	<!-- main -->
	<div class="main">
		<div class="error-page">
			<h1>404<br>ERROR</h1>
			<p>PAGE NOT FOUND</p>
			<p><a href="${basePath }">返航</a></p>
		</div>	
	</div>
	<!--Footer -->
	<jsp:include page="../common/page_footer.jsp"/>
</body>
</html>