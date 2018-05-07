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
				function(){
					//确认按钮回调
				},
				{type:'warning',confirmButtonText: '好的'});
		return ;
	}
	//输入是否为手机号
	if(!checkPhone(phone)){
		alert("提示",
				"请输入正确的手机号 ",
				function(){
					//确认按钮回调
				},
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
				
			  var timeout = 60;
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
						function(){
				  			//确认按钮回调
						},
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
  //账号密码登录按钮
  $(".login-btn").click(function() {
    var account = $(".normal-login input[name='account']").val();
    var password = $(".normal-login input[name='password']").val();

    //校验表单是否为空
    if (!checkEmpty($(".normal-login"))) {
      return;
    }
    //手机号或者邮箱号是否输入正确
    if (!checkPhone(account) && !checkEmail(account)) {
      showLoginTips("请填写正确的手机号或邮箱号");
    } else {
    	var loginUrl = basePath + "/user/accountLogin.do";
    	$.ajax({
    	  	  url: loginUrl,
    	  	  type: "POST",
    	  	  data: $(".normal-login").serialize(),
    	  	  dataType: "json",
    	  	  success: function(result){
    	  		  //登录成功
    	  		  if(result.code == 0){
    	  			  location.reload();
    	  		  }else{//登录失败
    	  			  var title = "登录失败";
    	  			  var tips = "";
    	  			  if(result.code == 104){
    	  				  tips = "图片验证码输入不正确，请重新输入";
    	  			  }else if(result.code == 203){
    	  				  tips = "请输入正确的邮箱号或者手机号";
    	  			  }else if(result.code == 204){
    	  				  tips = "账号或密码输入错误，请重新输入";
    	  			  }else if(result.code == 205){
    	  				  tips = "用户不存在，请检查账号是否输入无误";
    	  			  }else{
    	  				  tips = "未知错误";
    	  			  }
    	  			  //清空验证码
			  		  $(".normal-login input[name='imageCaptcha']").val("").blur();
    	  			  alert(title,
    	  					  tips,
    	  					  function(){
		    	  				  // 确认按钮回调
    	  				  		  //刷新验证码
    	  				  		  $(".captcha-img").click();
		    	  			  },
		    	  			  {type:'error',confirmButtonText: '好的'});
    	  		  }
    	  	  }});
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

  /**
   * 登录按钮点击
   */
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
    //表单是否为空
    if (!checkEmpty($(".phone-register"))) {
      return;
    }
    //手机号是否输入正确
    if (!checkPhone(phone)) {
      showRegisterTips("请填写正确的手机号");
      return;
    }
    
    //手机号注册地址
    var registerUrl = basePath + "/user/phoneRegister.do";
    //注册请求
    $.ajax({
  	  url: registerUrl,
  	  type: "POST",
  	  data: $(".phone-register").serialize(),
  	  success: function(result){
  		  if(result.success){
  			alert("注册成功",
  					"点击确认可切换到登录框 ",
  					function(){
  						//确认按钮回调
  						$(".phone-register input").val("").blur();
  						$(".navi-tab li:eq(0)").click();
  					},
  					{type:'success',confirmButtonText: '好的'});
  		  }else{
  			var title = "注册失败";
  			var tips = "";
  			if(result.code == 101){
  				tips = "短信验证码已失效，请重新获取";
  				$(".phone-register input[name='smsCaptcha']").val("").blur();
  			}else if(result.code == 102){
  				tips = "短信验证码错误，请重新输入";
  				$(".phone-register input[name='smsCaptcha']").val("").blur();
  			}else if(result.code == 103){
  				tips = "未获取短信验证码，请先获取";
  				$(".phone-register input[name='smsCaptcha']").val("").blur();
  			}else if(result.code == 202){
  				tips = "该手机号已被注册";
  				$(".phone-register input[name='phone']").val("").blur();
  			}else{
  				tips = "未知错误";
  			}
  			alert(title,
					tips,
					function(){
  						//确认按钮回调
					},
					{type:'error',confirmButtonText: '好的'});
  		  }
  	  },
  	  dataType: "json"
  	});
  });
})();
