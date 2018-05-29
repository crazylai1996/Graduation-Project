var laydate = layui.laydate, layer = layui.layer;
$(document).ready(function() {
  var currentImg = 0;
  var mouseTimer;
  var autoTimer;

  var maxWidth = 300;
  var maxHeight = 350;
  var imgScale = maxWidth / maxHeight;

  var imgDivs = $(".image-display").find("div");
  var imgsDisplay = $(".image-display").find("img");
  var imgCount = imgDivs.length;

  /**
	 * [设置图片高度，使其适应外层div]
	 */
  function initImgDisplay() {
    for (var i = 0; i < imgsDisplay.length; i++) {
      var imgObj = imgsDisplay.eq(i);
      if (imgObj.width() >= maxWidth || imgObj.height() >= maxHeight) {
        if ((imgObj.width() / imgObj.height()) >= imgScale) {
          imgObj.css("width", maxWidth + "px");
        } else {
          imgObj.css("height", maxHeight + "px");
        }
      }

    }
    $(".image-display").find(".current-img").show().siblings("div").hide();
  }

  var maxLiWidth = 45;
  var maxLiHeight = 45;
  var liImgScale = maxLiWidth / maxLiHeight;
  var maxImgRowIndex = 5;

  var imgSelectors = $(".thumbnail-list").find("a");
  var liImgs = $(".thumbnail-list").find("img");
  var imgListPosition = $(".thumbnail-container");
  var maxPage = Math.ceil(liImgs.length / maxImgRowIndex);
  var currentPage = 0;
  var currentPosition = 0;
  var timeOut = true;
  var oneDistance = 415;
  var moveTime = 500;
  var moveInterval = 20;
  var speed = oneDistance / (moveTime / moveInterval);
  var pause = false;

  /**
	 * [设置缩略图图片高度适应外层div]
	 */
  function initLiImgs() {
    for (var i = 0; i < liImgs.length; i++) {
      var liImgObj = liImgs.eq(i);
      if (liImgObj.width() >= maxLiWidth || liImgObj.height() >= maxLiHeight) {
        if ((liImgObj.width() / liImgObj.height()) >= liImgScale) {
          liImgObj.css("width", maxLiWidth + "px");
        } else {
          liImgObj.css("height", maxLiHeight + "px");
        }
      }
    }
    imgSelectors.hover(function() {
	  clearTimeout(autoTimer);
      clearTimeout(mouseTimer);
      pause = true;
      var index = $(this).index();
      mouseTimer = setTimeout(function() {
        currentImg = index;
        showImg(index);
      }, 1000);
    }, function() {
      pause = false;
      autoPlay();
      clearTimeout(mouseTimer);
    });
  }

  /**
	 * [缩略图跳到上一页]
	 */
  function switchToLastPage(targetPosition) {
    if (timeOut) {
      return;
    }
    if (currentPosition < targetPosition) {
      currentPosition += speed;
    } else {
      currentPosition = targetPosition;
      timeOut = true;
    }
    imgListPosition.css("left", currentPosition + "px");
    setTimeout(function() {
      switchToLastPage(targetPosition);
    }, moveInterval);
  }

  /**
	 * [缩略图跳到下一页]
	 */
  function switchToNextPage(targetPosition) {
    if (timeOut) {
      return;
    }
    if (currentPosition > targetPosition) {
      currentPosition -= speed;
    } else {
      currentPosition = targetPosition;
      timeOut = true;
    }
    imgListPosition.css("left", currentPosition + "px");
    setTimeout(function() {
      switchToNextPage(targetPosition);
    }, moveInterval);
  }

  /**
	 * [添加按钮点击事件]
	 */
  function initBtns() {
    $(".previous-btn").click(function() {
      if (currentPage == 0 || !timeOut) {
        return;
      }
      timeOut = false;
      switchToLastPage(currentPosition + oneDistance);
      currentPage--;
    });
    $(".next-btn").click(function() {
      if (currentPage == maxPage || !timeOut) {
        return;
      }
      timeOut = false;
      switchToNextPage(currentPosition - oneDistance);
      currentPage++;
    });
  }

  /**
	 * [显示第几张图片]
	 *
	 * @param {[type]}
	 *            index [第几张]
	 */
  function showImg(index) {
    imgDivs.removeClass();
    imgDivs.eq(index).addClass("current-img");
    imgSelectors.removeClass();
    imgSelectors.eq(index).addClass("current-li");
    imgDivs.eq(index).fadeIn(700).siblings("div").fadeOut(700);
    autoPlay();
  }

  /**
	 * [自动播放]
	 */
  function autoPlay() {
	if(pause){
		return;
	}
    autoTimer = setTimeout(function() {
      currentImg++;
      if (currentImg >= imgCount) {
        currentImg = 0;
      }
      showImg(currentImg);
    }, 5000);
  }

  /**
	 * [启动产品图片轮播]
	 */
  $(function(){
    initImgDisplay();
    initLiImgs();
    initBtns();
    autoPlay();
  });

  /**
	 * [加载评分插件]
	 */
  function loadRating(){
	  var $input = $('input.rating'), count = Object.keys($input).length;
	  if (count > 0) {
	      $input.rating();
	  }
  }
  loadRating();
  $('input.rating').on('rating.change', function(event, value, caption) {
	    $(".product-rating").val(value);
	});
  /**
	 * [显示图形统计]
	 */
  function showPower(power) {
    var powerClassName = $(power).attr("class")+"-wrapped";
    var powerWidth = parseInt($("."+powerClassName).css("max-width"));
    $(power).width(powerWidth * parseFloat($(power).data("power")));
  }
  // 评分占比评论心得分析
  showPower($(".rating-power,.item-power"));

  /**
	 * 购买方式选择
	 */
  $(".buy-way-title").click(function(e){
    $(this).siblings(".buy-way-sel").toggle();
    e.stopPropagation();
  });
  $(".buy-way-sel li").click(function(){
	$(".buy-way-input").val($(this).data("code"));
    $(".buy-way-title").html($(this).text());
  });

  $(document).click(function(){
    $(".more-sel").hide();
  });

  /**
   * 加载富文本编辑器
   */
  KindEditor.ready(function(K) {
	  var uploadPictureUrl = basePath + "/comment/pictureUpload.do";
      window.editor = K.create('#comment-input', {
          themeType: "simple",
          uploadJson: uploadPictureUrl,
          resizeType: 0,
          pasteType: 2,
          syncType: "",
          filterMode: true,
          allowPreviewEmoticons: false,
          fullscreenShortcut: true,
          items: [
            'undo', 'redo', 'plainpaste', 'wordpaste', 'clearhtml', 'quickformat',
            'selectall', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor',
            'bold', 'italic', 'underline', 'hr', 'removeformat', '|', 'justifyleft', 'justifycenter',
            'justifyright', 'insertorderedlist', 'insertunorderedlist', '|', 'link', 'image',
            'unlink', 'emoticons', '|','fullscreen'
          ],
          afterCreate: function() {
            this.sync();
          },
          afterBlur: function() {
            this.sync();
          },
          afterChange: function() {
            //富文本输入区域的改变事件，一般用来编写统计字数等判断
             $(".word-count").html(this.count('text'));
          },
          afterUpload: function(url) {
            //上传图片后的代码
        	this.sync();
          },
          allowFileManager: false,
          allowFlashUpload: false,
          allowMediaUpload: false,
          allowFileUpload: false
        });
  });

  /**
   * 关注产品按钮
   */
  $(".follow-ops").on("click",".follow-btn",function(){
	  var productId = $(".product-id").val();
	  
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
				  var count = parseInt($(".follow-count").text());
					if(result.data.action == 1){
						count = count+1;
						//关注成功
						var html = '<a href="javascript:void(0);" class="love-btn unfollow-btn"> \
							<font><i class="layui-icon layui-icon-star-fill"></i> 已关注&nbsp;&nbsp;</font>|&nbsp;&nbsp;<font class="follow-count">'+count+'</font>人\
							</a>';
						$(".follow-ops").html(html);
						tips = "成功关注";
					}else if(result.data.action == 2){
						count = count-1;
						$(".follow-count").text(count);
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
  $(".follow-ops").on("click",".unfollow-btn",function(){
	  var productId = $(".product-id").val();
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
				  var count = parseInt($(".follow-count").text());
					if(result.data.action == 2){
						//取消关注成功
						count = count - 1;
						var html = '<a href="javascript:void(0);" class="love-btn follow-btn"> \
							<font><i class="layui-icon layui-icon-star"></i> 关注&nbsp;&nbsp;</font>|&nbsp;&nbsp;<font class="follow-count">'+count+'</font>人\
							</a>';
						$(".follow-ops").html(html);
						tips = "成功取消关注";
					}else if(result.data.action == 1){
					count = count + 1;
					$(".follow-count").text(count);
						//成功关注
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
   * 关注用户按钮 
   */
  $(".comment-list").on("click",".follow-her-btn",function(){
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
						_this.removeClass("follow-her-btn").addClass("unfollow-her-btn");
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
  $(".comment-list").on("click",".unfollow-her-btn",function(){
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
						_this.removeClass("unfollow-her-btn").addClass("follow-her-btn");
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
   * 加载更多
   */
  $(".load-more-btn").click(function(){
	  var productId = $(".product-id").val();
	  var pageNum = $(".comment-list").data("pageNum") + 1;
	  var pages = $(".comment-list").data("pages");
	  $(".comment-list").data("pageNum",pageNum);
	  var getUrl = basePath + "comment/loadMoreComments.do" + "?pageNum="+pageNum+"&productId="+productId;
	  if(pageNum > pages){
		  layer.msg("没有更多啦")
		  return ;
	  }
	  var index = layer.load(0, {shade: false});
	  $.get(getUrl,function(result){
		  $(".comment-list").append(result);
		  loadRating();
		  layer.close(index);
		  if(pageNum >= pages){
			  $(".load-more-wrapped").html('<span class="no-more-load">我是有底线的</span>');
		  }
	  });
  });
 
  
  /**
	 * 添加按钮
	 */
  $(".add-btn").click(function(){
	  var productId = $(".product-id").val();
	  var count = editor.count('text');
	  var articleTitle = $(".comment-title-input").val();
	  var buyWay = $(".buy-way-input").val();
	  var contentHtml = editor.html();
	  var contentText = editor.text();
	  var productRating = $(".product-rating").val();
	  //字数判断 
	  if(count <= 0){
		  layer.msg("请填写心得内容");
		  return ;
	  }
	  if(count <20 || count > 800){
		  layer.msg("请输入20-500个字");
		  return ;
	  }
	  if(articleTitle == ""){
		  layer.msg("请填写心得标题");
		  return ;
	  }
	  if(buyWay == ""){
		  layer.msg("请选择购买方式");
		  return ;
	  }
	  if(productRating == ""){
		  layer.msg("请选择你的性价评分");
		  return ;
	  }
	  var param = new Object();
	  param.productId = productId;
	  param.articleTitle = articleTitle;
	  param.worthMark = productRating;
	  param.buyWay = buyWay;
	  param.contentText = contentText;
	  param.contentHtml = contentHtml;
	  var postUrl = basePath + "comment/new.do";
	  $.ajax({
		  url: postUrl,
		  data: param,
		  type: "POST",
		  dataType: "json",
		  success: function(result){
			  console.log(JSON.stringify(result));
			  if(result.success){
					alert("发表成功",
		  					"",
		  					function(){
		  						//确认按钮回调
								location.reload();
		  					},
		  					{type:'success',confirmButtonText: '好的'});
				}else{
					var title = "发表失败";
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
		  }
	  });
  });

});
