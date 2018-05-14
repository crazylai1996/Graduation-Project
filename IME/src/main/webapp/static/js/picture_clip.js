$(document).ready(function(){
	var maskObj = $(".upload-picture-mask");
	  var uploadDialog = $(".upload-dialog");
	  var dWidth = uploadDialog.innerWidth(),
	    dHeight = uploadDialog.innerHeight();
	  var sHeight = $(document).height(),
	    sWidth = $(document).width();
	  var oLeft = (0 - dWidth) / 2;
	  var oTop = (0 - dHeight) / 2;
	  uploadDialog.css({
	    left: $(window).width()/2 + oLeft,
	    top: $(window).height()/2 + oTop
	  });
	  maskObj.css({
	    width: sWidth + "px",
	    height: sHeight + "px"
	  });
	  var dialogTop,dialogLeft;

	  //移动头像裁剪弹框
	  var lastPoint = null;
	  var offsetLeft,offsetTop;

	  //鼠标按下
	  $(".upload-dialog-header").mousedown(function(event){
	    x=event.clientX;
	    y=event.clientY;
	    lastPoint = {
	      x:x,
	      y:y
	    };
	    //鼠标移动事件绑定到document，解决鼠标移动过快，事件不触发问题
	    $(document).mousemove(function(event){
	      offsetLeft = uploadDialog.position().left;
	      offsetTop = uploadDialog.position().top;

	      if(!lastPoint){
	        return;
	      }
	      //鼠标位置
	      var x,y,offsetX,offsetY;
	      x=event.clientX;
	      y=event.clientY;

	      offsetX = x - lastPoint.x;
	      offsetY = y - lastPoint.y;

	      var left,top ;
	      left = offsetLeft + offsetX;
	      top = offsetTop + offsetY;
	      if(left + uploadDialog.width() >= $(window).width()){
	        left = $(window).width() - uploadDialog.width();
	      }
	      if(top + uploadDialog.height() >= $(window).height()){
	        top = $(window).height() - uploadDialog.height();
	      }
	      left = left<=0?0:left;
	      top = top<=0?0:top;
	      uploadDialog.css({
	        left: left,
	        top: top
	      });
	      lastPoint = {
	        x:x,
	        y:y
	      };
	    });
	  });
	  $(".upload-dialog").mouseup(function(){
	    lastPoint = null;
	  });
	  $(document).mouseup(function(){
	    lastPoint = null;
	  });

	  //关闭按钮
	  $(".close-dialog-btn").click(function() {
		  $(".picture-clip-popup-container").remove();
	  });

	  var jcropApi;
	  var srcImg = $(".picture-show")[0];
	  var srcInput = $(".src-picture-input");
	  $(".picture-show").Jcrop({
	    allowSelect: true,
	    allowMove: true,
	    allowResize: true,
	    baseClass: 'jcrop',
	    bgColor: 'black',
	    bgOpacity: 0.6,
	    bgFade: true,
	    aspectRatio: 1,
	    borderOpacity: 0.4,
	    drawBorders: true,
	    dragEdges: true,
	    boxWidth: 260,
	    fadeTime: 400,
	    animationDelay: 20,
	    swingSpeed: 3,
	    onChange: getPosition
	  }, function() {
	    jcropApi = this;
	  });
	  srcInput.change(function() {
	    if (!this.files[0].name.match(/.jpg|.gif|.png|.bmp/i)) {
	      alert("你选择的文件类型不被支持！");
	      return;
	    }
	    var reader = new FileReader();
	    reader.readAsDataURL(this.files[0]);
	    reader.onload = function() {
	      srcImg.src = this.result;
	      jcropApi.setImage(this.result);
	      reader = null;
	    };
	  });

	  function getPosition(p) {
	    preShow(p.x, p.y, p.w, p.h);
	  }

	  function preShow(x, y, w, h) {
		if(w != 0 && h != 0){
		    $(".picture-pre-show").show();
		    $(".confirm-btn").addClass("confirm-btn-show");
		}

	    var img = new Image();
	    img.onload = function() {
        var canvas = $(".picture-pre-show")[0];
    	  var cxt = canvas.getContext("2d");
	      cxt.drawImage(img, x, y, w, h, 0, 0, 300, 300);
	    };
	    img.src = srcImg.src;
	  }
});
