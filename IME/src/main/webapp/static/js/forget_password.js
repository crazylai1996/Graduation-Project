var laydate = layui.laydate, layer = layui.layer;
$(document).ready(
		function() {
			/**
			 * 图片验证码显示
			 */
			$(function() {
				var catpchaUrl = basePath + "/security/getCaptchaImage.do";
				$(".captcha-img").attr("src",
						catpchaUrl + "?timestamp=" + new Date().getTime())
						.click(function() {
									$(this).attr("src",catpchaUrl 
											+ "?timestamp="
											+ new Date().getTime());});
			});
			var errorMessage = $(".error-tips").html();
			if(errorMessage != ""){
				$(".error-tips").css("opacity",1);
			}
			if($(".account-input").val() != ""){
				toggleGetMsgButton();
			}
			
		});
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

// 短信验证码获取
$(".get-msg-captcha").click(function() {
	var account = $(".account-input").val();
	
	// 是否为空
	if (!account) {
		layer.msg("手机号/邮箱号为空，请返回上一步重试");
		return;
	}
	var postUrl = basePath + "user/forgetPssword/reSendCaptcha.do";
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
				alert("错误", "获取验证码失败，将返回上一步重试 ", function() {
					// 确认按钮回调
					location.href = bashPath+"user/page/forgetPssword.html";
				}, {
					type : 'error',
					confirmButtonText : '好的'
				});
			}
			
		},
		dataType : "json"
	});
});
/*
 * 输入框聚集与失焦事件
 */
function moveIn(curInput) {
	$(curInput).siblings(".item-label").removeClass("current-label");
}

function moveOut(curInput) {
	$(curInput).siblings(".item-label").addClass("current-label");
}
$(".form-container input[type='text'],input[type='password']").focus(
		function() {
			moveOut(this);
		});
$(".form-container input[type='text'],input[type='password']").blur(function() {
	if ($(this).val() != "") {
		return;
	}
	moveIn(this);
});
$(".form-container .item-label").click(function() {
	$(this).siblings("input").focus();
});

/*
 * 判断表单是否为空
 */
function checkEmpty(formObj) {
	var inputs = formObj.find("input");
	var pass = true;

	inputs.each(function(n) {
		if ($(this).val() == "") {
			var message = "请填写" + $(this).data("label");
			showTips(formObj.find(".error-tips"), message);
			pass = false;
			return false;
		}
	});

	return pass
}

/*
 * 手机号校验
 */
function checkPhone(val) {
	var phoneReg = /^[1][3,4,5,6,7,8][0-9]{9}$/;
	if (!phoneReg.test(val)) {
		return false;
	}
	return true;
}
/*
 * 邮箱号校验
 */
function checkEmail(val) {
	var emailReg = /^([a-zA-Z0-9._-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
	if (!emailReg.test(val)) {
		return false;
	}
	return true;
}
/*
 * 提示信息
 */
function showTips(target, message) {
	target.html(message).stop(true, true).animate({
		opacity : "1"
	}).delay(3000).animate({
		opacity : "0"
	});
}

/**
 * 登录按钮点击
 */
$(".next-step-btn").click(function() {
	var account = $(".forget-password-form input[name='account']").val();

	// 表单是否为空
	if (!checkEmpty($(".forget-password-form"))) {
		return;
	}
	// 手机号或者邮箱号是否输入正确
	if (!checkPhone(account) && !checkEmail(account)) {
		showTips($(".forget-password-form .error-tips"), "请填写正确的手机号或邮箱号");
		return ;
	}
	$(".forget-password-form").submit();
});
$(".update-pass-btn").click(function() {
	// 表单是否为空
	if (!checkEmpty($(".update-password-form"))) {
		return;
	}
	var password = $(".password-input").val();
	var rePassword = $(".repassword-input").val();
	if(password != rePassword){
		layer.msg("再次输入密码不一致，请重新输入");
		return ;
	}
	var postUrl = basePath + "user/forgetPssword/resetPassword.do";
	$.ajax({
	  	  url: postUrl,
	  	  type: "POST",
	  	  data: $(".update-password-form").serialize(),
	  	  success: function(result){
	  		  if(result.success){
	  			alert("找回密码成功",
	  					"点击确认将跳转到登录页面 ",
	  					function(){
	  						//确认按钮回调
	  						location.href = basePath + "user/page/login.html";
	  					},
	  					{type:'success',confirmButtonText: '好的'});
	  		  }else{
	  			var title = "找回密码失败";
	  			var tips = "";
	  			var callback = function(){
	  				
	  			};
	  			
	  			if(result.code == 101){
	  				tips = "验证码已失效，请重新获取";
	  				$(".update-password-form input[name='smsCaptcha']").val("").blur();
	  			}else if(result.code == 102){
	  				tips = "验证码错误，请重新输入";
	  				$(".update-password-form input[name='smsCaptcha']").val("").blur();
	  			}else if(result.code == 103){
	  				tips = "验证码未获取，请先获取";
	  				$(".update-password-form input[name='smsCaptcha']").val("").blur();
	  			}else{
	  				tips = "非法请求，将返回上一步";
	  				callback = function(){
	  					location.href = basePath + "user/page/forgetPssword.html";
	  				};
	  			}
	  			alert(title,
						tips,
						callback,
						{type:'error',confirmButtonText: '好的'});
	  		  }
	  	  },
	  	  dataType: "json"
	  	});
});
