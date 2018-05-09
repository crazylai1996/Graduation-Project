<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="common/base.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<base href="${basePath }">
<title>头像更新</title>
<link rel="stylesheet" href="static\plugins\BeAlert\BeAlert.css" />
</head>

<body>
	<!-- 头像选择框 -->
	<div class="portrait-clip-container">
		<div class="upload-portrait-mask"></div>
		<div class="upload-dialog">
			<span class="upload-dialog-header"> <span class="title-label"
				onselectstart="return   false">选择头像</span> <a
				class="close-dialog-btn" href="javascript:void(0);"></a>
			</span>
			<hr />
			<input type="file" class="src-portrait-input" />
			<div class="portrait-clip-box">
				<div class="clip-container">
					<img class="portrait-show" src=" " />
				</div>
				<div class="target-image-container">
					<canvas class="portrait-pre-show" width="300px" height="300px"></canvas>
					<a class="upload-btn" href="javascript:void(0);">上传</a>
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
	<script src="static\js\update_portrait.js"></script>
</body>

</html>
