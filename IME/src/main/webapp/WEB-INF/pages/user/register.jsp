<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/base.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<base href="${basePath }">
<title>注册 - 爱美丽</title>
<link rel="stylesheet" href="static\css\register.css" />
<link rel="stylesheet" href="static\plugins\layui\css\layui.css" />
<script type="text/javascript" src="static\plugins\layui\layui.all.js"></script>
<link rel="stylesheet" href="static\plugins\BeAlert\BeAlert.css"/>
</head>

<body>
	<div class="register-container">
		<div class="register-label">
			<div class="logo-title">
				<a href="${basePath }index.html"><img src="static\img\logo.png" alt="爱美丽"></a><span class="left"> |
					注册</span>
			</div>
			<a class="right" href="${basePath }user/page/login.html">登录</a>
		</div>
		<!-- 弹出框 -->
		<div class="popup-wrap">
			<!-- 表单 -->
			<div class="popup-contents">
				<!-- 导航tab -->
				<ul class="navi-tab">
					<li><a class="current-tab" href="javascript:void(0);">快速注册</a></li>
				</ul>
				<div class="form-container">
					<!-- 手机号快速注册表单 -->
					<form class="phone-register">
						<div class="form-item">
							<div class="item-label">手机号</div>
							<input class="long-input" type="text" name="phone"
								class="item-input" autocomplete="off" data-label="手机号">
						</div>
						<div class="form-item">
							<div class="item-label">输入验证码</div>
							<input class="short-input" type="text" name="captcha"
								class="item-input" autocomplete="off" data-label="验证码">
							<a class="get-msg-captcha" href="javascript:void(0);">获取短信验证码</a>
						</div>
						<div class="form-item">
							<div class="item-label">密码</div>
							<input class="long-input" type="password" name="password"
								class="item-input" autocomplete="off" data-label="密码">
						</div>
						<!-- 其他操作 -->
						<div class="relative-op">
							<div class="op-left">
								<input id="agreement" type="checkbox" name=""> <label
									for="agreement">同意爱美丽</label> <a href="#">&lt;&lt;用户协议&gt;&gt;</a>
							</div>
						</div>
						<p class="register-tips">错误信息提示</p>
<!-- 						<a class="register-btn" href="javascript:void(0);">注册</a> -->
						<button type="button" class="register-btn">注册</button>
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
	<script src="static\plugins\BeAlert\BeAlert.js"></script>
	<script src="static\js\register.js"></script>
</body>

</html>
