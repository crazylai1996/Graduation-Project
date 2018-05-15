//上传按钮
$(".confirm-btn").click(function(){
	var canvas = $(".picture-pre-show")[0];
	//保存裁剪后的图片
	cover=canvas.toDataURL();
	$(".cover-pre-show").html("<img src='" + cover + "'/>");
	$(".picture-clip-popup-container").remove();
});
