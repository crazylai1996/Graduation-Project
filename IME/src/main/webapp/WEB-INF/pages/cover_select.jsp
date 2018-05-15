<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="common/base.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<base href="${basePath }">
<title>化妆品封面选择</title>
<link rel="stylesheet" href="static\plugins\BeAlert\BeAlert.css" />
<link rel="stylesheet" href="static\plugins\Jcrop\jquery.Jcrop.min.css">
</head>

<body>
	<!-- 图片选择框 -->
	<div class="image-clip-container">
		<div class="upload-picture-mask"></div>
		<div class="upload-dialog">
			<span class="upload-dialog-header"> <span class="title-label"
				onselectstart="return   false">图片裁剪</span> <a
				class="close-dialog-btn" href="javascript:void(0);"></a>
			</span>
			<hr />
			<a href="javascript:void(0);" class="img-input-wrapped">
				封面图片<input type="file" class="src-picture-input" />
			</a>
			<div class="picture-clip-box">
				<div class="clip-container">
					<img class="picture-show" src=" " />
				</div>
				<div class="target-image-container">
					<canvas class="picture-pre-show" width="300px" height="300px"></canvas>
					<a class="confirm-btn" href="javascript:void(0);">确认</a>
				</div>
			</div>
		</div>
	</div>

	<!-- js脚本 -->
	<script>
		var basePath = "${basePath}";
	</script>
	<script src="static\js\jquery.js"></script>
	<!-- 弹窗插件 -->
	<script src="static\plugins\BeAlert\BeAlert.js"></script>
	<script src="static\plugins\Jcrop\jquery.Jcrop.min.js"></script>
	<script src="static\js\picture_clip.js"></script>
	<script src="static\js\cover_select.js"></script>
</body>

</html>
