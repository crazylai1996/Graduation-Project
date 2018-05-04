//遮照层
var maskObj = $(".popup-mask");

maskObj.click(function() {
  hidePopup();
});
// $("body").css("overflow","hidden");

var popupObj = $(".popup-wrap");
var marginVal = "-" + popupObj.height() / 2 + "px" + " 0 0 " + "-" + popupObj.width() / 2 + "px";
popupObj.css("margin", marginVal);

/**
 * [隐藏弹窗]
 */
function hidePopup() {
  maskObj.hide();
  popupObj.hide();
  $("body").css("overflow", "visiable");
}
var closeBtn = $(".popup-close-btn");
closeBtn.click(function() {
  hidePopup();
});

/*
 *输入框聚集与失焦事件
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
 *表单切换
 */
function switchForm(tag) {
  //账号密码登录
  $(".form-container form").eq(tag).show().siblings("form").hide();
}
$(".navi-tab li").click(function() {
  var index = $(".navi-tab li").index(this);
  $(".navi-tab a").removeClass("current-tab")
  $(".navi-tab li").eq(index).find("a").addClass("current-tab");

  switchForm(index);
});

/*
 *判断表单是否为空
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
 *手机号校验
 */
function checkPhone(val) {
  var phoneReg = /^[1][3,4,5,7,8][0-9]{9}$/;
  if (!phoneReg.test(val)) {
    return false;
  }
  return true;
}
/*
 *邮箱号校验
 */
function checkEmail(val) {
  var emailReg = /^([a-zA-Z0-9._-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
  if (!emailReg.test(val)) {
    return false;
  }
  return true;
}
/*
 *输入密码校验
 */
function checkPassword(val) {
  if (val.length <= 6 || val.length >= 18) {
    return false;
  }
  return true;
}
/*
 *提示信息
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
 *账号密码登录
 */
(function() {
  var pass = false; //标识表单是否通过校验
  /**
   * [登录提示信息]
   * @param  {[type]} message [提示信息]
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
 *手机号验证码登录
 */
(function() {
  var pass = false; //标识表单是否通过校验
  /**
   * [登录提示信息]
   * @param  {[type]} message [提示信息]
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
 *手机号注册
 */
(function() {
  var pass = false; //标识表单是否通过校验
  /**
   * [登录提示信息]
   * @param  {[type]} message [提示信息]
   */
  function showRegisterTips(message) {
    showTips($(".register-tips"), message);
  }

  $(".register-btn").click(function() {
    var phone = $(".phone-register input[name='phone']").val();

    if (!checkEmpty($(".phone-register"))) {
      return;
    }
    if (!checkPhone(phone)) {
      showRegisterTips("请填写正确的手机号");
    } else {

    }
  });
})();
