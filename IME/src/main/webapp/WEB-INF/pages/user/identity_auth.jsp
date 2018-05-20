<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/base.jsp"%>
<div class="identity-auth-container">
	<div class="input-item">
		<span class="input-label">验证方式：</span> 
		<select class="input-val auth-way-selector"
			name="account">
			<c:forEach items="${authWays }" var="auth">
				<option value="${auth.code}">${auth.name }</option>
			</c:forEach>
		</select>
		<span class="input-tips"></span>
	</div>
	<div class="input-item">
		<div class="input-label">输入验证码：</div>
		<input class="input-val" type="text" name="imageCaptcha" 
			 autocomplete="off"> 
		<img class="captcha-img" alt="看不清，换一张">
	</div>
</div>
<script src="${basePath }static/js/jquery.js"></script>
<script>
$(document).ready(function(){
	/**
	 * 图片验证码显示
	 */
	$(function(){
		var catpchaUrl = "${basePath}security/getCaptchaImage.do";
		$(".captcha-img").attr("src",catpchaUrl + "?timestamp="+new Date().getTime())
	     .click(function(){
	         $(this).attr("src",catpchaUrl + "?timestamp="+new Date().getTime());
	     });
	});
	function getAccountText(account){
		var accountText = "";
		var emailReg = /^([a-zA-Z0-9._-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
		  if (emailReg.test(account)) {
		    accountText = account.substring(0,1)+"***@"+account.split("@")[1]
		  }
		  var phoneReg = /^[1][3,4,5,6,7,8][0-9]{9}$/;
		  if (phoneReg.test(account)) {
			  accountText = account.substring(0,3) +"***"+ account.substring(7);
		  }
		  return accountText;
	}
	
	var account = $(".auth-way-selector option:selected").val();
	
	$(".input-tips").html(getAccountText(account));
	$(".auth-way-selector").change(function(){
		$(".input-tips").html(getAccountText($(this).children('option:selected').val()));
	});
	
});
</script>