<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/base.jsp"%>
<div class="email-input-container">
	<div class="input-item">
		请输入你要绑定的新手机号
	</div>
	<div class="input-item">
		<div class="input-label">输入新手机号：</div>
		<input class="input-val mid-size-input" type="text" name="account" 
			 autocomplete="off"> 
		<a class="get-msg-captcha" href="javascript:void(0);">获取验证码</a>
	</div>
	<div class="input-item">
		<div class="input-label">输入验证码：</div>
		<input class="input-val identity-auth-captcha-input" type="text" name="smsCaptcha" 
			 autocomplete="off"> 
	</div>
</div>
<script src="${basePath }static/js/jquery.js"></script>
<script>
$(document).ready(function(){
	function toggleGetMsgButton(){
		$(".get-msg-captcha").addClass("to-get-btn");
		var timeout = 60;
		var text = $(".get-msg-captcha").text();
		$(".get-msg-captcha").text(timeout + "秒后重新获取");
		var timer = setInterval(function() {
			timeout--;
			$(".get-msg-captcha").text(timeout + "秒后重新获取");
			if (timeout == 0) {
				clearInterval(timer);
				$(".get-msg-captcha").text(text);
				$(".get-msg-captcha").removeClass("to-get-btn");
			}
		}, 1000);
	}

	// 验证码获取
	$(".get-msg-captcha").click(function() {
		var account = $(".email-input-container input[name='account']").val();
		if(account == ""){
			layer.msg("请先填写手机号");
			return ;
		}
		var phoneReg = /^[1][3,4,5,6,7,8][0-9]{9}$/;
		if (!phoneReg.test(account)) {
			layer.msg("请填写正确的手机号");
		  	return ;
		}
		
		var postUrl = basePath + "user/accountSetting/sendBandNewCaptcha.do";
		// 请求获取验证码
		$.ajax({
			url : postUrl,
			type : "POST",
			data : {
				account : account
			},
			success : function(result) {
				console.log(result);
				// 请求成功
				if(result.success){
					toggleGetMsgButton();
					layer.msg("发送成功，请查收");
				}else{
					if(result.code == 207){
						alert("获取验证码失败", "登录已失效，请重新登录 ", function() {
							// 确认按钮回调
							window.open(basePath + "user/page/login.html",'_blank'); 
						}, {
							type : 'error',
							confirmButtonText : '好的'
						});
					}else{
						alert("获取验证码失败", "获取验证码失败，请重新重试 ", function() {
							// 确认按钮回调
						}, {
							type : 'error',
							confirmButtonText : '好的'
						});
					}
				}
				
			},
			dataType : "json"
		});
	});
});
</script>