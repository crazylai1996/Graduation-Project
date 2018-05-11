//产品上市时间日期选择
var laydate = layui.laydate,
    layer = layui.layer;
laydate.render({
  elem: '.product-ttm',
  type: 'month',
  format: 'yyyy年MM月',
  theme: '#CC99CC'
});
//加载图片上传
$(function(){
	var picShowingDiv=$(".product-pre-show");

  var showSelectedPic=function(){
		picShowingDiv.html("");
		if(this.files.length>5){
			layer.msg("选择图片不能超过5张");
      var oldInput = $(".product-img-input");
      var newInput = $(".product-img-input").clone();
      newInput.val("");
      oldInput.after(newInput);
			oldInput.remove();
			return ;
		}
		for(var i=0;i<this.files.length;i++){
      //判断选择文件类型
			if(!this.files[i].name.match(/.jpg|.gif|.png|.bmp/i)){
				layer.msg("你选择的文件类型不被支持");
        var oldInput = $(".product-img-input");
        var newInput = $(".product-img-input").clone();
        newInput.val("");
        oldInput.after(newInput);
  			oldInput.remove();
				return ;
			}
			var reader=new FileReader();
			reader.readAsDataURL(this.files[i]);
			reader.onload=function(){
				var imgHtml="<img src='"+this.result+"'/>";
				picShowingDiv.append(imgHtml);
			};
		}
	};
  //是否支持FileReader
	if(typeof FileReader==="undefined"){
		$(".product-img-input").attr("disabled","disabled");
    alert("警告",
          "你的浏览器不支持上传文件",
          function(){
            //callback
          },
          {type: "warning", confirmButtonText: "我知道了"});
	}
	else{
		$(".product-form").on("change",".product-img-input",showSelectedPic);
	}
});
