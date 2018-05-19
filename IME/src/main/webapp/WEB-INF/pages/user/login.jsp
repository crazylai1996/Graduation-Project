<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/base.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<base href="${basePath }">
<title>登录 - 爱美丽</title>
<link rel="stylesheet" href="static\css\login.css" />
<link rel="stylesheet" href="static\plugins\layui\css\layui.css" />
<script type="text/javascript" src="static\plugins\layui\layui.all.js"></script>
</head>

<body>
	<div class="login-container">
		<div class="login-label">
			<div class="logo-title">
				<img src="static\img\logo.png" alt="爱美丽"> <span class="left"> |
					登录</span>
			</div>
			<a class="right" href="${basePath }user/page/register.html">注册</a>
		</div>
		<!-- 弹出框 -->
		<div class="popup-wrap">
			<!-- 表单 -->
			<div class="popup-contents">
				<!-- 导航tab -->
				<ul class="navi-tab">
					<li><a class="current-tab" href="javascript:void(0);">常规登录</a></li>
					<li><a href="javascript:void(0);">快捷登录</a></li>
				</ul>
				<div class="form-container">
					<!-- 账号登录表单 -->
					<input type="hidden" name="redirectUrl"
							value="${param.redirectUrl }">
					<form class="normal-login" method="POST">
						<div class="form-item">
							<div class="item-label">手机号/邮箱</div>
							<input class="long-input" type="text" name="account"
								class="item-input" autocomplete="off" data-label="手机号/邮箱">
						</div>
						<div class="form-item">
							<div class="item-label">密码</div>
							<input class="long-input" type="password" name="password"
								class="item-input" autocomplete="off" data-label="密码">
						</div>
						<div class="form-item">
							<div class="item-label">输入验证码</div>
							<input class="short-input" type="text" name="imageCaptcha"
								class="item-input" autocomplete="off" data-label="验证码">
							<img class="captcha-img" alt="看不清，换一张">
						</div>
						<!-- 其他操作 -->
						<div class="relative-op">
							<div class="op-left">
								<a class="select-login-style" href="javascript:void(0);"
									onclick="switchForm(2)">手机号快捷登录</a>
							</div>
							<div class="op-right">
								<input id="rememberMe" type="checkbox" name=""> <label
									for="rememberMe">记住我</label> <a href="javascript:void(0);">忘记密码</a>
							</div>
						</div>
						<p class="login-tips">错误信息提示</p>
						<a class="login-btn" href="javascript:void(0);">登录</a>
						<!-- <input class="login-btn" type="submit" value="登录"> -->
					</form>
					<!-- 手机号快速登录表单 -->
					<form class="phone-quick-login">
						<div class="form-item">
							<div class="item-label">手机号</div>
							<input class="long-input" type="text" name="phone"
								class="item-input" autocomplete="off" data-label="手机号">
						</div>
						<div class="form-item">
							<div class="item-label">输入验证码</div>
							<input class="short-input" type="text" name="smsCaptcha"
								class="item-input" autocomplete="off" data-label="验证码">
							<a class="get-msg-captcha" href="javascript:void(0);">获取短信验证码</a>
						</div>
						<!-- 其他操作 -->
						<div class="relative-op">
							<div class="op-left">
								<a class="select-login-style" href="javascript:void(0);"
									onclick="switchForm(0)">账号密码登录</a>
							</div>
							<div class="op-right">
								<input id="rememberMe-phone" type="checkbox" name=""> <label
									for="rememberMe-phone">记住我</label>
							</div>
						</div>
						<p class="quick-login-tips">错误信息提示</p>
						<a class="quick-login-btn" href="javascript:void(0);">登录</a>
						<!-- <input class="login-btn" type="submit" value="登录"> -->
					</form>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../common/page_footer.jsp" />
	<script>
		var basePath = "${basePath}";
	</script>
	<!-- js脚本 -->
	<script src="static\js\jquery.js"></script>
	<script src="static\js\login.js"></script>
</body>

</html>
