var laydate = layui.laydate, layer = layui.layer;
/**
 * [产品更多属性]
 */
$(".property-more-btn").click(function(){
  if(!$(this).hasClass("pointer-top")){
    $(this).parent().siblings(".filter-items").css("height","auto");
  }else{
    $(this).parent().siblings(".filter-items").css("height","24px");
  }
  $(this).toggleClass("pointer-top");
});
if(property != ""){
	$(".property-items").parent().find(".property-more-btn").click();
	$(".property-items a:contains("+property+")").addClass("selected");
}
if(effect != ""){
	$(".effect-items").parent().find(".property-more-btn").click();
	$(".effect-items a:contains("+effect+")").addClass("selected");
}
if(brand != ""){
	$(".brand-items").parent().find(".property-more-btn").click();
	$(".brand-items a:contains("+brand+")").addClass("selected");
}
/**
 * [加载评分插件]
 */
function reloadStarRating(){
	var $input = $('input.rating'), count = Object.keys($input).length;
	if (count > 0) {
	    $input.rating();
	}
}
reloadStarRating();
/**
 * 构造请求参数
 * @returns
 */
function getParams(){
	property = $(".property-items .selected").text();
	effect = $(".effect-items .selected").text();
	brand = $(".brand-items .selected").text();
	
	var params = new Object();
	if($.trim(keyword) != ""){
		params.keyword = keyword;
	}
	if($.trim(classify) != ""){
		params.classify = classify;
	}
	if($.trim(property) != ""){
		params.property = property;
	}
	if($.trim(effect) != ""){
		params.effect = effect;
	}
	if($.trim(brand) != ""){
		params.brand = brand;
	}
	
	return params;
}
/**
 * 搜索条件筛选
 */
$(".filter-list a").click(function(){
	if($(this).hasClass("selected")){
		return ;
	}
	var index = layer.load();
	$(this).siblings("a").removeClass("selected");
	$(this).addClass("selected");
	
	var params = getParams();
	
	//页码重置为1，搜索
	var pageNum = 1;
	$(".product-list").data("pageNum",pageNum);
	params.pageNum = pageNum;
	
	var postUrl = basePath + "product/loadMoreProducts.do";
	$.ajax({
		  url: postUrl,
		  data: params,
		  type: "POST",
		  success: function(result){
			  if($.trim(result) != ""){
				  $(".product-list").html(result);
				  reloadStarRating();
			  }else{
				  $(".product-list").html('\
						  <div class="no-more-contents">\
	        				找不到相关商品\
		        		  </div>');
			  }
			  var len = $(".product-list li").length;
			  if(len >= 5){
				  $(".load-more-wrapped").html('<a class="load-more-btn" href="javascript:void(0);">加载更多</a>');
			  }else{
				  $(".load-more-wrapped").html('<span class="no-more-load">我是有底线的</span>');
			  }
			  layer.close(index);
		  }
	});
});
/**
 * 加载更多搜索结果 
 */
$(".load-more-wrapped").on("click",".load-more-btn",function(){
	var index = layer.load();
	var pageNum = $(".product-list").data("pageNum") + 1;
	var params = getParams();
	params.pageNum = pageNum;
	
	var postUrl = basePath + "product/loadMoreProducts.do";
	$.ajax({
		  url: postUrl,
		  data: params,
		  type: "POST",
		  success: function(result){
			  if($.trim(result) != ""){
				  $(".product-list").append(result);
				  $(".product-list").data("pageNum",pageNum);
				  reloadStarRating();
			  }else{
				  layer.msg("没有更多啦");
				  $(".load-more-wrapped").html('<span class="no-more-load">我是有底线的</span>');
			  }
			  layer.close(index);   
		  }
	});
});
