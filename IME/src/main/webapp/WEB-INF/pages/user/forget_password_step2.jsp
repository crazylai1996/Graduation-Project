<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/base.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<base href="${basePath }">
<title>找回密码 - 爱美丽</title>
<link rel="stylesheet" href="static\css\forget_password.css">
<link rel="stylesheet" href="static\plugins\layui\css\layui.css" />
<script type="text/javascript" src="static\plugins\layui\layui.all.js"></script>
<link rel="stylesheet" href="static\plugins\BeAlert\BeAlert.css"/>
</head>

<body>

	<div class="forget-container">
		<div class="forget-label">
			<div class="logo-title">
				<a href="${basePath }index.html"><img src="static\img\logo.png" alt=""></a><span class="left">
					| 忘记密码</span>
			</div>
			<span class="right"><a href="${basePath }user/page/forgetPssword.html">上一步</a>
			&nbsp;&nbsp;|&nbsp;&nbsp;<a href="${pasePath }user/page/login.html">登录</a></span>
		</div>
		<div class="forget-form form-container">
			<p class="form-title">${actionMessage }</p>
			<form class="update-password-form">
				<input type="hidden" class="account-input" name="account" value="${account }">
				<div class="form-item">
					<div class="item-label">输入验证码</div>
					<input class="short-input" type="text" name="smsCaptcha"
						class="item-input" autocomplete="off" data-label="验证码"> <a
						class="get-msg-captcha" href="javascript:void(0);">获取验证码</a>
				</div>
				<div class="form-item">
					<div class="item-label">密码</div>
					<input class="long-input password-input" type="password" name="password"
						class="item-input" autocomplete="off" data-label="密码">
				</div>
				<div class="form-item">
					<div class="item-label">确认密码</div>
					<input class="long-input repassword-input" type="password" name="repassword"
						class="item-input" autocomplete="off" data-label="确认密码">
				</div>
				<p class="error-tips">${errorMessage }</p>
				<a class="update-pass-btn" href="javascript:void(0);">更新密码</a>
			</form>
		</div>
	</div>
	<jsp:include page="../common/page_footer.jsp" />
	<script>
		var basePath = "${basePath}";
	</script>
	<script src="static\js\jquery.js"></script>
	<script src="static\plugins\BeAlert\BeAlert.js"></script>
	<script src="static\js\forget_password.js"></script>
</body>

</html>
