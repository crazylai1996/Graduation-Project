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
      clearTimeout(mouseTimer);
      var index = $(this).index();
      mouseTimer = setTimeout(function() {
        currentImg = index;
        showImg(index);
      }, 1000);
    }, function() {
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
    clearTimeout(autoTimer);
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
  var $input = $('input.rating'), count = Object.keys($input).length;
  if (count > 0) {
      $input.rating();
  }
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
	 * 添加按钮
	 */
  $(".add-btn").click(function(){
	  var productId = $(".product-id").val();
	  var count = editor.count('text');
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
