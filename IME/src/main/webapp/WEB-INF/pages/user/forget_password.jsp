<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/base.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<base href="${basePath }">
<title>忘记密码 - 爱美丽</title>
<link rel="stylesheet" href="static\css\forget_password.css">
<link rel="stylesheet" href="static\plugins\layui\css\layui.css" />
<script type="text/javascript" src="static\plugins\layui\layui.all.js"></script>
</head>

<body>

	<div class="forget-container">
		<div class="forget-label">
			<div class="logo-title">
				<img src="static\img\logo.png" alt=""> <span class="left">
					| 忘记密码</span>
			</div>
			<a class="right" href="javascript:void(0);">登录</a>
		</div>
		<div class="forget-form form-container">
			<p class="form-title">请输入可以接收验证码的手机号或邮箱</p>
			<form class="forget-password-form">
				<div class="form-item">
					<div class="item-label">手机号/邮箱</div>
					<input class="long-input" type="text" name="account"
						class="item-input" autocomplete="off" data-label="手机号/邮箱">
				</div>
				<div class="form-item">
					<div class="item-label">输入验证码</div>
					<input class="short-input" type="text" name="imageCaptcha"
						class="item-input" autocomplete="off" data-label="验证码"> <img
						class="captcha-img" alt="看不清，换一张">
				</div>
				<p class="error-tips">错误信息提示</p>
				<a class="next-step-btn" href="javascript:void(0);">下一步</a>
			</form>
		</div>
	</div>
	<jsp:include page="../common/page_footer.jsp" />
	<script>
		var basePath = "${basePath}";
	</script>
	<script src="static\js\jquery.js"></script>
	<script src="static\js\forget_password.js"></script>
</body>

</html>
