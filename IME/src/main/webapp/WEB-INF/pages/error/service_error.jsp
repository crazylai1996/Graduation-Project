<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="robots" content="noindex,nofollow">
<title>出错啦 - 爱美丽</title>
<style>
body {
	font-size: 14px;
	font-family: 'helvetica neue', tahoma, arial, 'hiragino sans gb',
		'microsoft yahei', 'Simsun', sans-serif;
	background-color: #fff;
	color: #808080;
}

.wrap {
	margin: 20px auto;
	width: 1000px;
	min-height: 260px;
}

td {
	text-align: left;
	padding: 2px 10px;
}

td.header {
	font-size: 22px;
	padding-bottom: 10px;
	color: #000;
}

td.check-info {
	padding-top: 20px;
}

a {
	color: #328ce5;
	text-decoration: none;
}

a:hover {
	text-decoration: underline;
}

.error-ico img {
	width: 120px;
}

.op-tips {
	font-size: 12px;
	color: #393D49;
	font-weight: bold;
}
.op-tips-a a{
	color: #0092d2;
}
.op-tips-a a:hover{
	color: #CC99CC;
}
</style>
</head>
<body>
	<jsp:include page="../common/page_header.jsp" />
	<div class="wrap">
		<table>
			<tr>
				<td rowspan="7" class="error-ico"><img
					src="static/img/error.png" alt="又一个极简的错误页面"></td>
				<td class="header">出错啦！当前页面找不到了</td>
			</tr>
			<tr>
				<td>错误信息：${errorMessage }</td>
			</tr>
			<tr>
				<td class="op-tips">你可能：</td>
			</tr>
			<tr>
				<td>敲错了网址；</td>
			</tr>
			<tr>
				<td>没有访问权限；</td>
			</tr>
			<tr>
				<td class="op-tips">你可以：</td>
			</tr>
			<tr>
				<td class="op-tips-a"><a href="${url }">重试</a>，或者回到<a href="/IME/">主页</a></td>
			</tr>
		</table>
	</div>
	<jsp:include page="../common/page_footer.jsp" />
</body>
</html>
