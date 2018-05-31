var cover = "";
var productImageArr = [];
// 产品上市时间日期选择
var laydate = layui.laydate, layer = layui.layer;
laydate.render({
	elem : '.product-ttm',
	type : 'month',
	format : 'yyyy年MM月',
	theme : '#CC99CC'
});
// 加载图片上传
$(function() {
	var picShowingDiv = $(".product-pre-show");

	var showSelectedPic = function() {
		picShowingDiv.html("");
		if (this.files.length > 5) {
			layer.msg("选择图片不能超过5张");
			var oldInput = $(".product-img-input");
			var newInput = $(".product-img-input").clone();
			newInput.val("");
			oldInput.after(newInput);
			oldInput.remove();
			return;
		}
		for (var i = 0; i < this.files.length; i++) {
			// 判断选择文件类型
			if (!this.files[i].name.match(/.jpg|.gif|.png|.bmp/i)) {
				layer.msg("你选择的文件类型不被支持");
				var oldInput = $(".product-img-input");
				var newInput = $(".product-img-input").clone();
				newInput.val("");
				oldInput.after(newInput);
				oldInput.remove();
				//清空已选择的图片
				productImageArr = [];
				return;
			}
			var reader = new FileReader();
			reader.readAsDataURL(this.files[i]);
			reader.onload = function() {
				productImageArr.push(this.result);
				var imgHtml = "<img src='" + this.result + "'/>";
				picShowingDiv.append(imgHtml);
			};
		}
	};
	// 是否支持FileReader
	if (typeof FileReader === "undefined") {
		$(".product-img-input").attr("disabled", "disabled");
		alert("警告", "你的浏览器不支持上传文件", function() {
			// callback
		}, {
			type : "warning",
			confirmButtonText : "我知道了"
		});
	} else {
		$(".product-form").on("change", ".product-img-input", showSelectedPic);
	}
});
/**
 * 化妆品分类联动
 * @returns
 */
$(".classify-1st").change(function(){
	var parentClassId = $(this).children('option:selected').val();
	$.ajax({
		url: basePath + "cosmeticClass/getChildCosmeticClasses.do",
		type: "GET",
		data: {parentClassId:parentClassId},
		dataType: 'json',
		success: function(result){
			if(result.success){
				$(".classify-2nd").empty();
				$.each(result.data,function(){
					$(".classify-2nd").append("<option value="+
							this.classId+">"+
							this.className+
							"</option>");
				});
			}
		}
	});
});

/**
 * 封面选择弹框
 * 
 * @returns
 */
$(".select-cover").click(function() {
	var getUrl = basePath + "product/coverSelect";
	$.get(getUrl,function(data,status){
		$(document.body).append("<div class='picture-clip-popup-container'>"+data+"</div>");
	});
});
/**
 * 产品品牌选择
 */
$(".product-brand-input").click(function(){
	var _this = $(this);
	var getUrl = basePath + "productBrand/getAll";
	layer.open({
	  type: 2, 
	  title: "选择产品属性",
	  content: [getUrl, 'yes'],
	  area: ["400px"],
	  btn: ['确定', '取消'],
	  yes: function(index, layero){
	    //按钮【按钮一】的回调
		  var iframeWin = window[layero.find('iframe')[0]['name']];
		  var selectedOption = iframeWin.selectedOption;
		  if(selectedOption == null){
			  layer.msg("未选中任何产品品牌，请选择");
			  return ;
		  }
		  _this.val(selectedOption.name);
		  $(".product-brand-id-input").val(selectedOption.value);
		  //关闭弹窗
		  layer.close(index);
	  },
	  cancel: function(){ 
	    //右上角关闭回调
	    
	  }
	});
});
/**
 * 产品属性选择
 */
$(".product-property-input").click(function(){
	var _this = $(this);
	var getUrl = basePath + "productProperty/getAll";
	layer.open({
	  type: 2, 
	  title: "选择产品属性",
	  content: [getUrl, 'yes'],
	  area: ["400px"],
	  btn: ['确定', '取消'],
	  yes: function(index, layero){
	    //按钮【按钮一】的回调
		  var iframeWin = window[layero.find('iframe')[0]['name']];
		  var selectedOption = iframeWin.selectedOption;
		  if(selectedOption == null){
			  layer.msg("未选中任何产品属性，请选择");
			  return ;
		  }
		  _this.val(selectedOption.name);
		  $(".product-property-id-input").val(selectedOption.value);
		  //关闭弹窗
		  layer.close(index);
	  },
	  cancel: function(){ 
	    //右上角关闭回调
	    
	  }
	});
});
/**
 * 产品功效选择
 */
$(".product-effect-input").click(function(){
	var _this = $(this);
	var getUrl = basePath + "productEffect/getAll";
	layer.open({
	  type: 2, 
	  title: "选择产品功效",
	  content: [getUrl, 'yes'],
	  area: ["400px"],
	  btn: ['确定', '取消'],
	  yes: function(index, layero){
	    //按钮【按钮一】的回调
		  var iframeWin = window[layero.find('iframe')[0]['name']];
		  var selectedOption = iframeWin.selectedOption;
		  if(selectedOption == null){
			  layer.msg("未选中任何产品属性，请选择");
			  return ;
		  }
		  _this.val(selectedOption.name);
		  $(".product-effect-id-input").val(selectedOption.value);
		  //关闭弹窗
		  layer.close(index);
	  },
	  cancel: function(){ 
	    //右上角关闭回调
	    
	  }
	});
});
/**
 * 添加按钮监听
 */
$(".add-confirm").click(function(){
	var _this = $(this);
	var inputs = $(".product-form input[required='required']");
	var flag = true;
	$.each(inputs,function(){
		if(isBlank(this.value)){
			layer.msg("必填项不能留空");
			flag = false;
			return flag;
		}
	});
	if(!flag){
		return ;
	}
	var fd=new FormData($(".product-form")[0]);
	//产品规格
	fd.append("spec",$(".spec-val").val()+$(".spec-unit").val());
	if(isBlank(cover)){
		layer.msg("商品封面未选择");
		return ;
	}
	if(productImageArr.length == 0){
		layer.msg("产品图片未选择");
		return;
	}
	fd.append("coverFile",toBlob(cover));//fd.append("key",blob,"文件名");
	$.each(productImageArr,function(){
		fd.append("productFiles",toBlob(this));
	});
	
	_this.attr("disabled",true);
	var postUrl = basePath + "product/new.do";
	$.ajax({
		url: postUrl,
		type: "POST",
		data: fd,
		dataType: "json",
		processData: false,
		contentType: false,
		success:function(result){
			if(result.success){
				alert("添加成功",
	  					"",
	  					function(){
	  						//确认按钮回调
							location.reload();
	  					},
	  					{type:'success',confirmButtonText: '好的'});
			}else{
				var title = "添加失败";
	  			var tips = "";
	  			var callback = function(){

	  			};
	  			if(result.code == 207){
	  				tips = "未登录或登录失效，请重新登录";
	  				callback = function(){
	  					window.open(basePath + "user/page/login.html","_blank");
	  				}
	  			}else if(result.code == 301){
	  				tips = "所需产品图片未提供，请先提供";
	  			}else if(result.code == 302){
	  				tips = "产品图片保存失败，请稍后重试";
	  			}else{
	  				tips = "未知错误";
	  			}
	  			alert(title,
						tips,
						callback,
						{type:'error',confirmButtonText: '好的'});
	  			_this.attr("disabled",false);
			}
		}
	});
});

/**
 * Base64转Blob对象
 **/
function toBlob(base64){
	base64=base64.split(",")[1];
	//base-64编码过的字符串进行解码 
	src=window.atob(base64);

	var ua=new Uint8Array(src.length);
	for(var i=0;i<src.length;i++){
		ua[i]=src.charCodeAt(i);
	}
	//canvas.toDataURL 返回的默认格式就是 image/png
	return new Blob([ua],{type:"image/png"});
}
/**
 * 判断字符串是否为空
 */
function isBlank(data){
	return (data == "" || data == undefined || data == null);
}
