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
