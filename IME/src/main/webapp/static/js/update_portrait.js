//上传按钮
$(".confirm-btn").click(function(){
	var canvas = $(".picture-pre-show")[0];
	var src=canvas.toDataURL();
	src=src.split(",")[1];
	src=window.atob(src);

	var ua=new Uint8Array(src.length);
	for(var i=0;i<src.length;i++){
		ua[i]=src.charCodeAt(i);
	}
	//canvas.toDataURL 返回的默认格式就是 image/png
	var postUrl = basePath + "user/updatePortrait.do";
	var blob=new Blob([ua],{type:"image/png"});
	var fd=new FormData();
	fd.append("portrait",blob);//fd.append("key",blob,"文件名");
	$.ajax({
		url: postUrl,
		type: "POST",
		data: fd,
		dataType: "json",
		processData: false,
		contentType: false,
		success:function(result){
			if(result.success){
				alert("更新头像成功",
							"",
							function(){
								//确认按钮回调
							$(".close-dialog-btn").click();
							$(".portrait-img")[0].src = result.data;
							},
							{type:'success',confirmButtonText: '好的'});
			}else{
				var title = "更新失败";
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
					}else if(result.code == 209){
						tips = "保存头像出错，请稍后重试";
						callback = function(){
							$(".close-dialog-btn").click();
						}
					}else{
						tips = "未知错误，请稍后重试";
						callback = function(){
							$(".close-dialog-btn").click();
						}
					}
					alert(title,
						tips,
						callback,
						{type:'error',confirmButtonText: '好的'});
			}

		}
	});
});
