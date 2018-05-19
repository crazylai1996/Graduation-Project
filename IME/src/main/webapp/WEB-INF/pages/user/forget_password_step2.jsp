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
			<p class="form-title">向你绑定的手机 134****1284 发送短信验证码</p>
			<form class="update-password-form">
				<div class="form-item">
					<div class="item-label">输入验证码</div>
					<input class="short-input" type="text" name="captcha"
						class="item-input" autocomplete="off" data-label="验证码"> <a
						class="get-msg-captcha" href="javascript:void(0);">获取短信验证码</a>
				</div>
				<div class="form-item">
					<div class="item-label">密码</div>
					<input class="long-input" type="password" name="password"
						class="item-input" autocomplete="off" data-label="密码">
				</div>
				<div class="form-item">
					<div class="item-label">确认密码</div>
					<input class="long-input" type="password" name="repassword"
						class="item-input" autocomplete="off" data-label="确认密码">
				</div>
				<p class="error-tips">错误信息提示</p>
				<a class="update-pass-btn" href="javascript:void(0);">更新密码</a>
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
