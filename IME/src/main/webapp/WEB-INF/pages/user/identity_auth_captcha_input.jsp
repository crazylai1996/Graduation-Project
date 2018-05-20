<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/base.jsp"%>
<div class="captcha-input-container">
	<div class="input-item">
		请输入你所收到的验证码
	</div>
	<div class="input-item">
		<input name="account" value="${account }" type="hidden">
		<div class="input-label">输入验证码：</div>
		<input class="input-val identity-auth-captcha-input" type="text" name="smsCaptcha" 
			 autocomplete="off"> 
	</div>
</div>

