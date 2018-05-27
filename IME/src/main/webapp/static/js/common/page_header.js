/*
 *导航栏移入移出事件,展开更多
 */
$(".user-navi>li,.more-beauty").hover(function(){
  $(this).find(".more").show();
},function(){
  $(this).find(".more").hide();
});
/*
 *选择搜索类型
 */
$(".search-type").click(function(){
  $(this).siblings("ul").toggle();
});
$(".s-type-slector a").click(function(){
  $(".search-type").html($(this).html());
  $(".s-type-slector").hide();
});
/*
 * 跳转到登录页面
 */
$(".gologin-btn").click(function(){
	var redirectUrl = window.location.href;
	$.get(basePath+"user/login",{target:"login",redirectUrl:redirectUrl},function(data,status){
		$(document.body).append("<div class='login-popup-container'>"+data+"</div>");
		$("html").css("overflow-y","hidden");
	});
});
/**
 * 跳转到注册页面
 */
$(".gosignup-btn").click(function(){
	var redirectUrl = window.location.href;
	$.get(basePath+"user/login",{target:"signup",redirectUrl:redirectUrl},function(data,status){
		$(document.body).append("<div class='login-popup-container'>"+data+"</div>");
		$("html").css("overflow-y","hidden");
	});
});
/**
 * 搜索
 */
$(".search-btn").click(function(){
	var keyword = $(".search-keyword").val();
	if($.trim(keyword) == ""){
		layer.msg("请先输入要搜索的关键字");
		return ;
	}
	if($(".search-type").text() == "商品"){
		window.open(basePath + "product/list.html"+"?keyword="+keyword,"_blank");   
	}
	if($(".search-type").text() == "点评"){
		location.href=basePath + "comment/search.html"+"?keyword="+keyword;
	}
});

