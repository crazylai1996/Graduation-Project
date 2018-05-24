//鼠标悬停用户名时，显示更多用户信息
$(".user-list").on("mouseenter",".user-name-p",function(){
  $(".more-info-down").hide();
  var moreInfoDiv = $(this).find(".more-info-down");
  moreInfoDiv.find(".more-info").html($(".more-info-templet").html());
  moreInfoDiv.show();
});
//鼠标移出用户名时，隐藏更多用户信息
$(".user-list").on("mouseleave",".user-name-p",function(){
  $(".user-list .more-info").html("");

  var moreInfoDiv = $(this).find(".more-info-down");

  moreInfoDiv.hide();
});
//鼠标移入移出事件，隐藏或显示产品基本信息
$(".product-list").on("mouseenter",".picture-container",function(){
  $(this).find(".product-count").show();
});
$(".product-list").on("mouseleave",".picture-container",function(){
  $(this).find(".product-count").hide();
});
/**
 * 关注用户按钮 
 */
$(".user-list").on("click",".follow-user-btn",function(){
	  var _this = $(this);
	  var followedUserId = $(this).data("userId");
	  
	  var postUrl = basePath + "user/followUser.do";
	  $.ajax({
		  url: postUrl,
		  data: {followedUserId:followedUserId},
		  type: "POST",
		  dataType: "json",
		  success: function(result){
			  console.log(result);
			  if(result.success){
				//确认按钮回调
				  var tips = "";
					if(result.data.action == 1){
						//关注成功
						_this.text("取消关注");
						_this.removeClass("follow-user-btn").addClass("unfollow-user-btn");
						tips = "成功关注";
					}else if(result.data.action == 2){
						//取消关注
						tips = "你已取消关注";
					}
				  alert("",
		  					tips,
		  					function(){
		  						
		  					},
		  					{type:'success',confirmButtonText: '好的'});
			  }else{
				  var title = "";
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
		  				tips = "该用户不存在";
		  			}else{
		  				tips = "未知错误";
		  			}
		  			alert(title,
							tips,
							callback,
							{type:'error',confirmButtonText: '好的'});
			  }
		  }});
});

/**
 * 取消关注用户
 */
$(".user-list").on("click",".unfollow-user-btn",function(){
	  var _this = $(this);
	  var followedUserId = $(this).data("userId");
	  
	  var postUrl = basePath + "user/unfollowUser.do";
	  $.ajax({
		  url: postUrl,
		  data: {followedUserId:followedUserId},
		  type: "POST",
		  dataType: "json",
		  success: function(result){
			  console.log(result);
			  if(result.success){
				//确认按钮回调
				  var tips = "";
					if(result.data.action == 2){
						//取消关注成功
						_this.text("关注TA");
						_this.removeClass("unfollow-user-btn").addClass("follow-user-btn");
						tips = "成功取消关注";
					}else if(result.data.action == 1){
						//成功关注
						tips = "关注成功";
					}
				  alert("",
		  					tips,
		  					function(){
		  						
		  					},
		  					{type:'success',confirmButtonText: '好的'});
			  }else{
				  var title = "";
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
		  				tips = "该用户不存在";
		  			}else{
		  				tips = "未知错误";
		  			}
		  			alert(title,
							tips,
							callback,
							{type:'error',confirmButtonText: '好的'});
			  }
		  }});
});
/**
 * 关注产品按钮
 */
$(".product-list").on("click",".follow-product-btn",function(){
	  var _this = $(this);
	  var productId = $(this).data("productId");
	  
	  var postUrl = basePath + "product/followProduct.do";
	  $.ajax({
		  url: postUrl,
		  data: {productId:productId},
		  type: "POST",
		  dataType: "json",
		  success: function(result){
			  console.log(result);
			  if(result.success){
				//确认按钮回调
				  var tips = "";
					if(result.data.action == 1){
						//关注成功
						var html = '<i class="layui-icon layui-icon-star-fill"></i> ';
						_this.removeClass("follow-product-btn").addClass("unfollow-product-btn");
						_this.html(html);
						tips = "成功关注";
					}else if(result.data.action == 2){
						//取消关注
						tips = "你已取消关注";
					}
				  alert("",
		  					tips,
		  					function(){
		  						
		  					},
		  					{type:'success',confirmButtonText: '好的'});
			  }else{
				  var title = "";
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
		  			}else if(result.code == 303){
		  				tips = "产品不存在或已下架";
		  			}else{
		  				tips = "未知错误";
		  			}
		  			alert(title,
							tips,
							callback,
							{type:'error',confirmButtonText: '好的'});
			  }
		  }});
});

/**
 * 取消关注产品按钮
 */
$(".product-list").on("click",".unfollow-product-btn",function(){
	  var _this = $(this);
	  var productId = $(this).data("productId");
	  var postUrl = basePath + "product/unfollowProduct.do";
	  $.ajax({
		  url: postUrl,
		  data: {productId:productId},
		  type: "POST",
		  dataType: "json",
		  success: function(result){
			  console.log(result);
			  if(result.success){
				//确认按钮回调
				  var tips = "";
					if(result.data.action == 2){
						//关注成功
						var html = '<i class="layui-icon layui-icon-star"></i> ';
						_this.html(html);
						_this.removeClass("unfollow-product-btn").addClass("follow-product-btn");
						tips = "成功取消关注";
					}else if(result.data.action == 1){
						//取消关注
						tips = "你已成功关注该产品";
					}
				  alert("",
		  					tips,
		  					function(){
		  						
		  					},
		  					{type:'success',confirmButtonText: '好的'});
			  }else{
				  var title = "";
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
		  			}else if(result.code == 303){
		  				tips = "产品不存在或已下架";
		  			}else{
		  				tips = "未知错误";
		  			}
		  			alert(title,
							tips,
							callback,
							{type:'error',confirmButtonText: '好的'});
			  }
		  }});
});

/**
 * 取消关注化妆品品类
 */
$(".tag-list").on("click",".unfollow-this-class",function(){
	var _this = $(this);
	var classId = _this.data("classId");
	var postUrl = basePath + "cosmeticClass/unfollowClass.do";
	$.ajax({
		url: postUrl,
		data: {classId,classId},
		type: "POST",
		dataType: "json",
		success: function(result){
			console.log(result);
			if(result.success){
				layer.msg("取消关注成功");
				_this.remove();
			}else{
				var title = "";
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
	  			}else{
	  				tips = "未知错误";
	  			}
	  			alert(title,
						tips,
						callback,
						{type:'error',confirmButtonText: '好的'});
			}
		}
	});
});
/**
 * 关注更多分类
 */
$(".tag-list").on("click",".add-more-tag",function(){
	var getUrl = basePath + "cosmeticClass/classSelect";
	layer.open({
	  type: 2, 
	  title: "选择要关注的标签",
	  content: [getUrl, 'yes'],
	  area: ["400px"],
	  btn: ['保存', '取消'],
	  yes: function(index, layero){
	    //按钮【按钮一】的回调
		  var iframeWin = window[layero.find('iframe')[0]['name']];
		  var selectedOptions = iframeWin.selectedOptions;
		  var classIds = iframeWin.selectedOptionValue;
		  if(selectedOptions.length == 0){
			  layer.msg("未选中任何产品标签，请先选择");
			  return ;
		  }
		  
		  var postUrl = basePath + "cosmeticClass/followMoreClasses.do";
		  $.ajax({
			  	url: postUrl,
				data: {classIds,classIds},
				type: "POST",
				dataType: "json",
				traditional:true,
				success: function(result){
					if(result.success){
						layer.msg("保存成功");
						//关闭弹窗
					    layer.close(index);
					    var html = "";
					    $.each(selectedOptions,function(){
					    	html = html + '<a class="unfollow-this-class" \
					    			data-class-id="'+this.value+'" \
					    			href="javascript:void(0);">'+this.name+'</a>';
					    });
					    html = html + '<a href="javascript:void(0);" class="add-more-tag">关注更多</a>';
					    $(".tag-list p").html(html);
					}else{
						var title = "";
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
			  			}else{
			  				tips = "未知错误";
			  			}
			  			alert(title,
								tips,
								callback,
								{type:'error',confirmButtonText: '好的'});
					}
				}
		  });
		  
		  
	  },
	  cancel: function(){ 
	    //右上角关闭回调
	    
	  }
	});
});
