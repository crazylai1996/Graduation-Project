$(".portrait-wrapped").hover(function() {
  $(this).find(".change-protrait").show();
}, function() {
  $(this).find(".change-protrait").hide();
});
//点击更换头像
$(".change-protrait a").click(function() {
	$.get(basePath+"user/updatePortrait",function(data,status){
		$(document.body).append("<div class='picture-clip-popup-container'>"+data+"</div>");
	});
});
//省份城市联动
$(".province-selector").change(function(){
	var provinceId = $(this).children('option:selected').val();
	$.ajax({
		url: basePath + "area/getCities.do",
		type: "GET",
		data: {provinceId:provinceId},
		dataType: 'json',
		success: function(result){
			if(result.success){
				$(".city-selector").empty();
				$.each(result.data,function(){
					$(".city-selector").append("<option value="+
							this.areaId+">"+
							this.areaName+
							"</option>");
				});
			}
		}
	});
});
//用户信息更新操作
$(".update-confirm").click(function(){
	var _this = $(this);
	_this.attr("disabled",true);
	$.ajax({
		url: basePath + "user/updateUserInfo.do",
		type: "POST",
		data: $(".userinfo-form").serialize(),
		dataType: 'json',
		success: function(result){
			if(result.success){
				alert("更新成功",
	  					"",
	  					function(){
	  						//确认按钮回调
	  					},
	  					{type:'success',confirmButtonText: '好的'});
			}else{
				var title = "更新失败";
	  			var tips = "";
	  			var callback = function(){

	  			};
	  			if(result.code == 207){
	  				tips = "登录失效，请重新登录";
	  				callback = function(){
	  					var redirectUrl = window.location.href;
	  					$.get(basePath+"user/login",{target:"login",redirectUrl:redirectUrl},function(data,status){
	  						$(document.body).append("<div class='login-popup-container'>"+data+"</div>");
	  						$("html").css("overflow-y","hidden");
	  					});
	  				}
	  			}else if(result.code == 208){
	  				tips = "非法请求";
	  			}else if(result.code == 205){
	  				tips = "用户不存在";
	  			}else{
	  				tips = "未知错误";
	  			}
	  			alert(title,
						tips,
						callback,
						{type:'error',confirmButtonText: '好的'});
	  			_this.attr("disabled",false);
			}
		}
	});
});
