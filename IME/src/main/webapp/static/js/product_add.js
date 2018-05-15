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
 * 添加按钮监听
 */
$(".add-confirm").click(function(){
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
				alert("更新成功",
	  					"",
	  					function(){
	  						//确认按钮回调
	  					},
	  					{type:'success',confirmButtonText: '好的'});
			}else{
				var title = "添加失败";
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
