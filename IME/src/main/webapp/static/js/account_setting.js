var laydate = layui.laydate, layer = layui.layer;
/**
 * 弹出修改密码页面
 */
$(".modify-password-btn").click(function() {
	var getUrl = basePath + "user/accountSetting/modifyPassword";
	$.get(getUrl, function(result) {
		layer.open({
			type : 1,
			title: '密码更改',
			content : result,
			area : ['420px', '220px'],
			btn : [ '更改', '取消' ],
			yes : function() {
				// 确定按钮回调
				var newPassword = $(".new-password-input").val();
				var reNewPassword = $(".re-new-password-input").val();
				var pass = true;
//				$(".modify-password-form input").each(function(n) {
//					if ($(this).val() == "") {
//						pass = false;
//						return false;
//					}
//				});
				if($.trim(newPassword) == "" || $.trim(reNewPassword) == ""){
					pass = false;
				}
				if(!pass){
					layer.msg("表单信息不能留空");
					return ;
				}
				if(newPassword != reNewPassword){
					layer.msg("两次密码输入不一致，请重新输入");
					return ;
				}
				var postUrl = basePath + "user/accountSetting/modifyPassword.do";
				$.ajax({
				  	  url: postUrl,
				  	  type: "POST",
				  	  data: $(".modify-password-form").serialize(),
				  	  success: function(result){
				  		  if(result.success){
				  			alert("密码修改成功",
				  					"请重新登录 ",
				  					function(){
				  						//确认按钮回调
				  						location.href = basePath + "user/page/login.html";
				  					},
				  					{type:'success',confirmButtonText: '好的'});
				  		  }else{
				  			var title = "密码修改失败";
				  			var tips = "";
				  			var callback = function(){
				  				
				  			};
				  			if(result.code == 207){
				  				tips = "登录已失效，请重新登录 ";
				  				callback = function(){
				  					window.open(basePath + "user/page/login.html",'_blank'); 
				  				};
				  			}else if(result.code == 208){
				  				tips = "非法请求";
				  			}else if(result.code == 205){
				  				tips = "用户不存在";
				  			}else if(result.code == 204){
				  				tips = "当前密码输入有误，请重新输入";
				  			}
				  			else{
				  				tips = "未知错误，请稍后重试";
				  			}
				  			alert(title,
									tips,
									callback,
									{type:'error',confirmButtonText: '好的'});
				  		  }
				  	  },
				  	  dataType: "json"
				  	});
			}
		});
	});

});
/**
 * 手机号修改
 */
$(".modify-phone-btn").click(function(){
	var getUrl = basePath + "user/accountSetting/identityAuth"+"?type=1";
	//选择身份验证方式页面
	$.get(getUrl, function(result) {
		var authWayIndex = layer.open({
			type : 1,
			title: '身份验证',
			content : result,
			area : ['420px', '220px'],
			btn : [ '下一步', '取消' ],
			yes : function() {
				// 确定按钮回调
				var account = $(".identity-auth-container select[name='account']").val();
				var imageCaptcha = $(".identity-auth-container input[name='imageCaptcha']").val();
				if(imageCaptcha == ""){
					layer.msg("请填写验证码");
					return ;
				}
				
				//发送身份验证码
				var postUrl = basePath + "user/accountSetting/sendAuthCpatha.do";
				$.ajax({
					  	  url: postUrl,
					  	  type: "POST",
					  	  data: {account:account,imageCaptcha:imageCaptcha},
					  	  success: function(result){
					  		  console.log(result);
					  		if(result.success){
					  			layer.close(authWayIndex);
					  			//打开验证码输入框
					  			var getUrl = basePath + "user/accountSetting/captchaInput";
					  			$.get(getUrl,{account:account}, function(result) {
					  				var captchaInputIndex = layer.open({
					  					type : 1,
					  					title: '身份验证',
					  					content : result,
					  					area : ['420px', '220px'],
					  					btn : [ '确定', '取消' ],
					  					yes : function() {
					  						//验证身份验证码
					  						var postUrl = basePath + "user/accountSetting/authCaptchaVerify.do";
								  			var account = $(".captcha-input-container input[name='account']").val();
								  			var smsCaptcha = $(".captcha-input-container input[name='smsCaptcha']").val();
					  						$.ajax({
					  					  	  url: postUrl,
					  					  	  type: "POST",
					  					  	  data: {account:account,smsCaptcha:smsCaptcha},
					  					  	  success: function(result){
					  					  		  console.log(result);
					  					  		  if(result.success){
					  					  			//验证码输入正确，打开修改邮箱页面
					  					  			layer.close(captchaInputIndex);
					  					  			var getUrl = basePath + "user/accountSetting/modifyPhone";
						  					  		$.get(getUrl,function(result) {
										  				var modifyEmailIndex = layer.open({
											  					type : 1,
											  					title: '输入新邮箱',
											  					content : result,
											  					area : ['430px', '240px'],
											  					btn : [ '确定', '取消' ],
											  					yes : function() {
											  						var phone = $(".email-input-container input[name='account']").val();
											  						var smsCaptcha = $(".email-input-container input[name='smsCaptcha']").val();
											  						if(phone == ""){
											  							layer.msg("邮箱号不能为空");
											  							return ;
											  						}
											  						if(smsCaptcha == ""){
											  							layer.msg("验证码不能为空");
											  							return ;
											  						}
											  						
											  						var phoneReg = /^[1][3,4,5,6,7,8][0-9]{9}$/;
											  						if (!phoneReg.test(phone)) {
											  							layer.msg("请填写正确的手机号");
											  						  	return ;
											  						}
											  						var postUrl = basePath + "user/accountSetting/modifyPhone.do";
											  						$.ajax({
												  					  	  url: postUrl,
												  					  	  type: "POST",
												  					  	  data: {account:phone,smsCaptcha:smsCaptcha},
												  					  	  success: function(result){
												  					  		  console.log(result);
												  					  		  if(result.success){
												  					  			alert("",
																						"绑定新手机号成功",
																						function(){
												  					  						//确定按钮回调
												  					  						layer.close(modifyEmailIndex);
												  					  					},
																						{type:'success',confirmButtonText: '好的'});
												  					  		  }else{
												  					  			var title = "修改手机号失败";
																	  			var tips = "";
																	  			var callback = function(){
																	  				
																	  			};
																	  			if(result.code == 207){
																	  				tips = "登录已失效，请重新登录 ";
																	  				callback = function(){
																	  					window.open(basePath + "user/page/login.html",'_blank'); 
																	  				};
																	  			}else if(result.code == 103){
																	  				tips = "验证码未获取，请先获取";
																	  			}else if(result.code == 102){
																	  				tips = "验证码不匹配，请重新输入";
																	  			}else if(result.code == 101){
																	  				tips = "验证码已失效，请返回重新获取";
																	  			}else if(result.code == 208){
																	  				tips = "非法请求";
																	  			}else if(result.code == 214){
																	  				tips = "手机号已被其他账号绑定，请重新输入";
																	  			}else{
																	  				tips = "未知错误，请稍后重试";
																	  			}
																	  			alert(title,
																						tips,
																						callback,
																						{type:'error',confirmButtonText: '好的'});
												  					  		  }
												  					  	  },
												  					  	  dataType: "json"
											  						});
											  					}
											  				});
							  					  		});
					  					  		  }else{
					  					  			var title = "验证失败";
										  			var tips = "";
										  			var callback = function(){
										  				
										  			};
										  			if(result.code == 207){
										  				tips = "登录已失效，请重新登录 ";
										  				callback = function(){
										  					window.open(basePath + "user/page/login.html",'_blank'); 
										  				};
										  			}else if(result.code == 102){
										  				tips = "验证码不匹配，请重新输入";
										  			}else if(result.code == 101){
										  				tips = "验证码已失效，请返回重新获取";
										  			}else if(result.code == 208){
										  				tips = "非法请求";
										  			}else{
										  				tips = "未知错误，请稍后重试";
										  			}
										  			alert(title,
															tips,
															callback,
															{type:'error',confirmButtonText: '好的'});
					  					  		  }
					  					  	  },
					  					  	  dataType: "json"
					  						});
					  					}});
					  				});
					  		}else{
					  			var title = "获取验证码失败";
					  			var tips = "";
					  			var callback = function(){
					  				
					  			};
					  			if(result.code == 207){
					  				tips = "登录已失效，请重新登录 ";
					  				callback = function(){
					  					window.open(basePath + "user/page/login.html",'_blank'); 
					  				};
					  			}else if(result.code == 104){
					  				tips = "验证码不匹配，请重新输入";
					  			}else if(result.code == 208){
					  				tips = "非法请求";
					  			}else{
					  				tips = "未知错误，请稍后重试";
					  			}
					  			alert(title,
										tips,
										callback,
										{type:'error',confirmButtonText: '好的'});
					  		  }
					  		},
				  		  dataType: "json"
				  	  });
				
			}
		});
	});
});
/**
 * 邮箱修改
 */
$(".modify-email-btn").click(function(){
	var getUrl = basePath + "user/accountSetting/identityAuth"+"?type=2";
	//选择身份验证方式页面
	$.get(getUrl, function(result) {
		var authWayIndex = layer.open({
			type : 1,
			title: '身份验证',
			content : result,
			area : ['420px', '220px'],
			btn : [ '下一步', '取消' ],
			yes : function() {
				// 确定按钮回调
				var account = $(".identity-auth-container select[name='account']").val();
				var imageCaptcha = $(".identity-auth-container input[name='imageCaptcha']").val();
				if(imageCaptcha == ""){
					layer.msg("请填写验证码");
					return ;
				}
				
				//发送身份验证码
				var postUrl = basePath + "user/accountSetting/sendAuthCpatha.do";
				$.ajax({
					  	  url: postUrl,
					  	  type: "POST",
					  	  data: {account:account,imageCaptcha:imageCaptcha},
					  	  success: function(result){
					  		  console.log(result);
					  		if(result.success){
					  			layer.close(authWayIndex);
					  			//打开验证码输入框
					  			var getUrl = basePath + "user/accountSetting/captchaInput";
					  			$.get(getUrl,{account:account}, function(result) {
					  				var captchaInputIndex = layer.open({
					  					type : 1,
					  					title: '身份验证',
					  					content : result,
					  					area : ['420px', '220px'],
					  					btn : [ '确定', '取消' ],
					  					yes : function() {
					  						//验证身份验证码
					  						var postUrl = basePath + "user/accountSetting/authCaptchaVerify.do";
								  			var account = $(".captcha-input-container input[name='account']").val();
								  			var smsCaptcha = $(".captcha-input-container input[name='smsCaptcha']").val();
					  						$.ajax({
					  					  	  url: postUrl,
					  					  	  type: "POST",
					  					  	  data: {account:account,smsCaptcha:smsCaptcha},
					  					  	  success: function(result){
					  					  		  console.log(result);
					  					  		  if(result.success){
					  					  			//验证码输入正确，打开修改邮箱页面
					  					  			layer.close(captchaInputIndex);
					  					  			var getUrl = basePath + "user/accountSetting/modifyEmail";
						  					  		$.get(getUrl,function(result) {
										  				var modifyEmailIndex = layer.open({
											  					type : 1,
											  					title: '输入新邮箱',
											  					content : result,
											  					area : ['430px', '240px'],
											  					btn : [ '确定', '取消' ],
											  					yes : function() {
											  						var email = $(".email-input-container input[name='account']").val();
											  						var smsCaptcha = $(".email-input-container input[name='smsCaptcha']").val();
											  						if(email == ""){
											  							layer.msg("邮箱号不能为空");
											  							return ;
											  						}
											  						if(smsCaptcha == ""){
											  							layer.msg("验证码不能为空");
											  							return ;
											  						}
											  						
											  						var emailReg = /^([a-zA-Z0-9._-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
												  					if (!emailReg.test(email)) {
												  						 layer.msg("请填写正确的邮箱号")
												  					    return ;
												  					  }
											  						var postUrl = basePath + "user/accountSetting/modifyEmail.do";
											  						$.ajax({
												  					  	  url: postUrl,
												  					  	  type: "POST",
												  					  	  data: {account:email,smsCaptcha:smsCaptcha},
												  					  	  success: function(result){
												  					  		  console.log(result);
												  					  		  if(result.success){
												  					  			alert("",
																						"绑定新邮箱成功",
																						function(){
												  					  						//确定按钮回调
												  					  						layer.close(modifyEmailIndex);
												  					  					},
																						{type:'success',confirmButtonText: '好的'});
												  					  		  }else{
												  					  			var title = "修改邮箱失败";
																	  			var tips = "";
																	  			var callback = function(){
																	  				
																	  			};
																	  			if(result.code == 207){
																	  				tips = "登录已失效，请重新登录 ";
																	  				callback = function(){
																	  					window.open(basePath + "user/page/login.html",'_blank'); 
																	  				};
																	  			}else if(result.code == 103){
																	  				tips = "验证码未获取，请先获取";
																	  			}else if(result.code == 102){
																	  				tips = "验证码不匹配，请重新输入";
																	  			}else if(result.code == 101){
																	  				tips = "验证码已失效，请返回重新获取";
																	  			}else if(result.code == 208){
																	  				tips = "非法请求";
																	  			}else if(result.code == 214){
																	  				tips = "邮箱号已被其他账号绑定，请重新输入";
																	  			}else{
																	  				tips = "未知错误，请稍后重试";
																	  			}
																	  			alert(title,
																						tips,
																						callback,
																						{type:'error',confirmButtonText: '好的'});
												  					  		  }
												  					  	  },
												  					  	  dataType: "json"
											  						});
											  					}
											  				});
							  					  		});
					  					  		  }else{
					  					  			var title = "验证失败";
										  			var tips = "";
										  			var callback = function(){
										  				
										  			};
										  			if(result.code == 207){
										  				tips = "登录已失效，请重新登录 ";
										  				callback = function(){
										  					window.open(basePath + "user/page/login.html",'_blank'); 
										  				};
										  			}else if(result.code == 102){
										  				tips = "验证码不匹配，请重新输入";
										  			}else if(result.code == 101){
										  				tips = "验证码已失效，请返回重新获取";
										  			}else if(result.code == 208){
										  				tips = "非法请求";
										  			}else{
										  				tips = "未知错误，请稍后重试";
										  			}
										  			alert(title,
															tips,
															callback,
															{type:'error',confirmButtonText: '好的'});
					  					  		  }
					  					  	  },
					  					  	  dataType: "json"
					  						});
					  					}});
					  				});
					  		}else{
					  			var title = "获取验证码失败";
					  			var tips = "";
					  			var callback = function(){
					  				
					  			};
					  			if(result.code == 207){
					  				tips = "登录已失效，请重新登录 ";
					  				callback = function(){
					  					window.open(basePath + "user/page/login.html",'_blank'); 
					  				};
					  			}else if(result.code == 104){
					  				tips = "验证码不匹配，请重新输入";
					  			}else if(result.code == 208){
					  				tips = "非法请求";
					  			}else{
					  				tips = "未知错误，请稍后重试";
					  			}
					  			alert(title,
										tips,
										callback,
										{type:'error',confirmButtonText: '好的'});
					  		  }
					  		},
				  		  dataType: "json"
				  	  });
				
			}
		});
	});
});
