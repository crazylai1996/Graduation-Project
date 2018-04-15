$(".property-more-btn").click(function(){
  if(!$(this).hasClass("pointer-top")){
    $(this).parent().siblings(".property-items").css("height","auto");
  }else{
    $(this).parent().siblings(".property-items").css("height","24px");
  }
  $(this).toggleClass("pointer-top");
});
