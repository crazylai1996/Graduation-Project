$(document).ready(function(){
	// 遮照层
	var maskObj = $(".popup-mask");

	maskObj.click(function() {
// hidePopup();
		removePopup();
	});
	
	var popupObj = $(".popup-wrap");
	var marginVal = "-" + popupObj.height() / 2 + "px" + " 0 0 " + "-" + popupObj.width() / 2 + "px";
	popupObj.css("margin", marginVal);
	
	/**
	 * 隐藏弹窗
	 */
	function hidePopup() {
	  maskObj.hide();
	  popupObj.hide();
	  $("body").css("overflow", "auto");
	}
	/**
	 * 销毁弹窗
	 */
	function removePopup(){
		$(".popup-container").remove();
		$("html").css("overflow-y","auto");
	}
	var closeBtn = $(".popup-close-btn");
	closeBtn.click(function() {
// hidePopup();
		removePopup();
	});
	
	/**
	 * 初始化，显示对应表单
	 */
	$(function(){
		var target = $(".target-window").val();
		if(target == "signup"){
			$(".navi-tab li:eq(1)").click();
		}
	});
	
	/**
	 * 图片验证码显示
	 */
	$(function(){
		var catpchaUrl = basePath + "/security/getCaptchaImage.do";
		$(".captcha-img").attr("src",catpchaUrl + "?timestamp="+new Date().getTime())
	     .click(function(){
	         $(this).attr("src",catpchaUrl + "?timestamp="+new Date().getTime());
	     });
	});
});

// 短信验证码获取
$(".get-msg-captcha").click(function(){
	var _this = $(this);
	var text = _this.text();
	
	var phone = _this.parents("form").find("input[name='phone']").val();
	// 校验手机号
	//是否为空
	if(!phone){
		alert("提示",
				"手机号不能为空 ",
				{type:'warning',confirmButtonText: '好的'});
		return ;
	}
	//输入是否为手机号
	if(!checkPhone(phone)){
		alert("提示",
				"请输入正确的手机号 ",
				{type:'warning',confirmButtonText: '好的'});
		return ;
	}
	var getUrl = basePath + "/user/getSmsCaptcha.do";
	// 请求获取验证码
	$.ajax({
	  url: getUrl,
	  type: "GET",
	  data: {phone:phone},
	  success: function(result){
		  console.log(result);
		  //请求成功
		  if("00000" == result.data.respCode){
			  _this.addClass("to-get-btn");
				
			  var timeout = 5;
			  _this.text(timeout+"秒后重新获取");
			  var timer = setInterval(function(){
				  timeout--;
				  _this.text(timeout+"秒后重新获取");
				  if(timeout == 0){
					  clearInterval(timer);
					  _this.text(text);
					  _this.removeClass("to-get-btn");
				  }
			},1000);
		  }else{//请求失败
			  alert("错误",
						"获取验证码失败，请稍后重试 ",
						{type:'error',confirmButtonText: '好的'});
		  }
	  },
	  dataType: "json"
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
$(".form-container input[type='text'],input[type='password']").focus(function() {
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
 * 表单切换
 */
function switchForm(tag) {
  // 账号密码登录
  $(".form-container form").eq(tag).show().siblings("form").hide();
}
$(".navi-tab li").click(function() {
  var index = $(".navi-tab li").index(this);
  $(".navi-tab a").removeClass("current-tab")
  $(".navi-tab li").eq(index).find("a").addClass("current-tab");

  switchForm(index);
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
      showTips(formObj.find("p"), message);
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
  var phoneReg = /^[1][3,4,5,7,8][0-9]{9}$/;
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
 * 输入密码校验
 */
function checkPassword(val) {
  if (val.length <= 6 || val.length >= 18) {
    return false;
  }
  return true;
}
/*
 * 提示信息
 */
function showTips(target, message) {
  target.html(message).
  stop(true, true).animate({
    opacity: "1"
  }).
  delay(1500).animate({
    opacity: "0"
  });
}
/*
 * 账号密码登录
 */
(function() {
  var pass = false; // 标识表单是否通过校验
  /**
	 * [登录提示信息]
	 * 
	 * @param {[type]}
	 *            message [提示信息]
	 */
  function showLoginTips(message) {
    showTips($(".login-tips"), message);
  }
  $(".login-btn").click(function() {
    var account = $(".normal-login input[name='account']").val();
    var password = $(".normal-login input[name='password']").val();

    if (!checkEmpty($(".normal-login"))) {
      return;
    }
    if (!checkPhone(account) && !checkEmail(account)) {
      showLoginTips("请填写正确的手机号或邮箱号");
    } else {

    }
  });
})();
/*
 * 手机号验证码登录
 */
(function() {
  var pass = false; // 标识表单是否通过校验
  /**
	 * [登录提示信息]
	 * 
	 * @param {[type]}
	 *            message [提示信息]
	 */
  function showQuickTips(message) {
    showTips($(".quick-login-tips"), message);
  }

  $(".quick-login-btn").click(function() {
    var phone = $(".normal-login input[name='phone']").val();

    if (!checkEmpty($(".phone-quick-login"))) {
      return;
    }
    if (!checkPhone(phone)) {
      showQuickTips("请填写正确的手机号");
    } else {

    }
  });
})();
/*
 * 手机号注册
 */
(function() {
  var pass = false; // 标识表单是否通过校验
  /**
	 * [登录提示信息]
	 * 
	 * @param {[type]}
	 *            message [提示信息]
	 */
  function showRegisterTips(message) {
    showTips($(".register-tips"), message);
  }

  /**
   * 手机号注册按钮点击事件
   */
  $(".register-btn").click(function() {
    var phone = $(".phone-register input[name='phone']").val();

    if (!checkEmpty($(".phone-register"))) {
      return;
    }
    if (!checkPhone(phone)) {
      showRegisterTips("请填写正确的手机号");
      return;
    }
    
    //手机号注册地址
    var registerUrl = basePath + "/user/phoneRegister.do";
    $.ajax({
  	  url: registerUrl,
  	  type: "POST",
  	  data: $(".phone-register").serialize(),
  	  success: function(data){
  		  console.log(data);
  	  },
  	  dataType: "json"
  	});
  });
})();
