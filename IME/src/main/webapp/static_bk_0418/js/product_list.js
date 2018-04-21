/**
 * [产品更多属性]
 */
$(".property-more-btn").click(function(){
  if(!$(this).hasClass("pointer-top")){
    $(this).parent().siblings(".property-items").css("height","auto");
  }else{
    $(this).parent().siblings(".property-items").css("height","24px");
  }
  $(this).toggleClass("pointer-top");
});
/**
 * [加载评分插件]
 */
var $input = $('input.rating'), count = Object.keys($input).length;
if (count > 0) {
    $input.rating();
}
