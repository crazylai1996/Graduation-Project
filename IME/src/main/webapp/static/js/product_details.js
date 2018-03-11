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
   * @param  {[type]} index [第几张]
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
    }, 4000);
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
  /**
   * [显示图形统计]
   */
  function showPower(power) {
    var powerClassName = $(power).attr("class")+"-wrapped";
    var powerWidth = parseInt($("."+powerClassName).css("max-width"));
    $(power).width(powerWidth * parseFloat($(power).data("power")));
  }
  showPower($(".rating-power"));
});
