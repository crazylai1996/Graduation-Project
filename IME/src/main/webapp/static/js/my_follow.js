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
