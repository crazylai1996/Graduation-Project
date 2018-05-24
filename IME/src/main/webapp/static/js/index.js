/*
 *子分类展开
 */
$(".category-index>li>a").hover(function(){
  $(this).siblings(".sub-category").show();
},function(){
  $(this).siblings(".sub-category").hide();
});
$(".sub-category").hover(function(){
  $(this).siblings("a").toggleClass("current-category");
  $(this).show();
},function(){
  $(this).siblings("a").toggleClass("current-category");
  $(this).hide();
});
/*
 *最新动态导航栏点击事件，榜单时间段切换
 */
$(".mc-head-time li").click(function(){
  $(this).addClass("current").siblings("li").removeClass("current");
});
/*
 *榜单条目hover事件
 */
$(".mc-body>li").hover(function(){
  $(this).addClass("current").siblings("li").removeClass("current");
});
//加载更多最新
function loadMoreNewest(){
	var pageNum = $(".nc-list").data("pageNum") + 1;
	var pages = $(".nc-list").data("pages");
	var getUrl = basePath + "comment/newestComments/loadMore.do" + "?pageNum="+pageNum;
	$(".nc-list").data("pageNum",pageNum);
	var index = layer.load(0, {shade: false});
	$.get(getUrl,function(result){
		if(pageNum == 1){
			$(".nc-list").html(result);
		}else{
			$(".nc-list").append(result);
		}
		layer.close(index);
		pages = $(".nc-list").data("pages");
		console.log(pageNum >= pages);
		if(pageNum >= pages){
			$(".no-more-load").show();
			$(".load-more-btn").hide();
		}else{
			$(".no-more-load").hide();
			$(".load-more-btn").show();
		}
	});
}
$(".load-more-newest").click(function(){
	loadMoreNewest();
})
//加载更多关注
function loadMoreFollowed(){
	var pageNum = $(".nc-list").data("pageNum") + 1;
	var pages = $(".nc-list").data("pages");
	var getUrl = basePath + "comment/myFollowed/loadMore.do" + "?pageNum="+pageNum;
	$(".nc-list").data("pageNum",pageNum);
	var index = layer.load(0, {shade: false});
	$.get(getUrl,function(result){
		if(pageNum == 1){
			$(".nc-list").html(result);
		}else{
			$(".nc-list").append(result);
		}
		layer.close(index);
		if($(".nc-list li").length == 0){
			$(".nc-list").html('<div class="no-more-contents">\
    				暂时没有新的内容\
    			</div>');
			$(".no-more-load").hide();
			$(".load-more-btn").hide();
		}else{
			pages = $(".nc-list").data("pages");
			if(pageNum >= pages){
				$(".no-more-load").show();
				$(".load-more-btn").hide();
			}else{
				$(".no-more-load").hide();
				$(".load-more-btn").show();
			}
		}
		
	});
}

$(".load-more-followed").click(function(){
	loadMoreFollowed();
});
$(".newest-tab").click(function(){
	$(".nc-list").data("pageNum",0);
	$(".load-more-btn").removeClass("load-more-followed").addClass("load-more-newest");
	loadMoreNewest();
	$(this).addClass("current").siblings("li").removeClass("current");
});
$(".followed-tab").click(function(){
	$(".nc-list").data("pageNum",0);
	$(".load-more-btn").removeClass("load-more-newest").addClass("load-more-followed");
	loadMoreFollowed();
	$(this).addClass("current").siblings("li").removeClass("current");
});