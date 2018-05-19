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
		});

// 短信验证码获取
$(".get-msg-captcha").click(function() {
	var _this = $(this);
	var text = _this.text();

	var phone = _this.parents("form").find("input[name='phone']").val();
	// 校验手机号
	// 是否为空
	if (!phone) {
		layer.msg("请输入手机号");
		return;
	}
	// 输入是否为手机号
	if (!checkPhone(phone)) {
		layer.msg("请输入正确的手机号");
		return;
	}
	var getUrl = basePath + "/user/getSmsCaptcha.do";
	// 请求获取验证码
	$.ajax({
		url : getUrl,
		type : "GET",
		data : {
			phone : phone
		},
		success : function(result) {
			console.log(result);
			if (result.code == 206) {
				alert("提示", "请输入正确的手机号 ", function() {
					// 确认按钮回调
				}, {
					type : 'warning',
					confirmButtonText : '好的'
				});
				return;
			}
			// 请求成功
			if ("00000" == result.data.respCode) {
				_this.addClass("to-get-btn");

				var timeout = 60;
				_this.text(timeout + "秒后重新获取");
				var timer = setInterval(function() {
					timeout--;
					_this.text(timeout + "秒后重新获取");
					if (timeout == 0) {
						clearInterval(timer);
						_this.text(text);
						_this.removeClass("to-get-btn");
					}
				}, 1000);
			} else {// 请求失败
				alert("错误", "获取验证码失败，请稍后重试 ", function() {
					// 确认按钮回调
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
	}).delay(1500).animate({
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
	}
});
$(".update-pass-btn").click(function() {
	// 表单是否为空
	if (!checkEmpty($(".update-password-form"))) {
		return;
	}
});
