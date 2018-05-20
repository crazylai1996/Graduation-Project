<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/base.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<base href="${basePath }">
<title>用户中心 - 爱美丽</title>
<link rel="stylesheet" href="static\css\bootstrap.min.css">
<link rel="stylesheet" href="static\plugins\Jcrop\jquery.Jcrop.min.css">
<link rel="stylesheet" href="static\plugins\BeAlert\BeAlert.css" />
<link rel="stylesheet" href="static\css\account_setting.css">
</head>
<body>
	<!-- 头部包含 -->
	<jsp:include page="../common/page_header.jsp" />

	<div class="main-wrapped">
		<div class="main-container">
			<!-- 当前位置开始 -->
			<div class="current-pos">
				<a href="${basePath }">首页</a><span class="split-line"></span> <a href="javascript:void(0);">用户中心</a><span
					class="split-line"></span>
				<sapn class="title">账号设置</span>
			</div>
			<!-- 当前位置结束 -->
			<!-- 主体内容开始 -->
			<div class="main-contents">
				<div class="main-left">
					<jsp:include page="user_navi.jsp" />
				</div>
				<div class="main-right">
					<div class="account-setting">
						<h1 class="title">账号设置</h1>
						<hr class="line">
						<div class="setting-list">
							<p>
								<span class="item-label">密码：</span>
								<span class="item-val">******</span>
								<a class="item-btn modify-password-btn" href="javascript:void(0);">修改</a>
							</p>
							<p>
								<span class="item-label">手机：</span>
								<span class="item-val">${userInfoVO.phone }</span>
								<a class="item-btn modify-phone-btn" href="javascript:void(0);">修改</a>
							</p>
							<p>
								<span class="item-label">邮箱：</span>
								<c:if test="${!empty userInfoVO.email }">
									<span class="item-val">${userInfoVO.email }</span>
								</c:if>
								<c:if test="${empty userInfoVO.email }">
									<span class="item-val">未绑定</span>
								</c:if>
								<a class="item-btn modify-email-btn" href="javascript:void(0);">修改</a>
							</p>
						</div>
					</div>
				</div>
			</div>
			<!-- 主体内容结束 -->
		</div>
	</div>
	<!-- 包含尾部 -->
	<jsp:include page="../common/page_footer.jsp" />

	<script>
		var basePath = "${basePath}";
	</script>
	<script src="static\js\jquery.js"></script>
	<script src="static\plugins\Jcrop\jquery.Jcrop.min.js"></script>
	<script src="static\plugins\BeAlert\BeAlert.js"></script>
	<script src="static\js\account_setting.js"></script>
</body>
</html>
