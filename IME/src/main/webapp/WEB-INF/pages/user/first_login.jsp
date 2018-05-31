<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/base.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<base href="${basePath }">
<title>首次登录 - 爱美丽</title>
<link rel="stylesheet" href="static\plugins\layui\css\layui.css" />
<script type="text/javascript" src="static\plugins\layui\layui.all.js"></script>
<link rel="stylesheet" href="static\plugins\BeAlert\BeAlert.css" />
<link rel="stylesheet" href="static\css\first_login.css">
</head>

<body>
	<!-- 头部包含 -->
	<jsp:include page="../common/page_header.jsp" />
	<div class="main-wrapped">
		<div class="main-container">
			<!-- 当前位置开始 -->
			<div class="current-pos">
				<a href="${basePath }">首页</a><span class="split-line"></span>
				<sapn class="title">首次登录</span>
			</div>
			<!-- 当前位置结束 -->
			<!-- 主体内容开始 -->
			<div class="main-contents">
				<h2 class="tips">请填写您的肤质和年龄，以获得更准确的推荐结果</h2>
				<div class="input-container">
					<div class="input-label">适用肤质：</div>
					<div class="input-contents">
						<select class="sm-input skin-texture" name="skinTexture">
							<c:forEach items="${skinTextures }" var="skinTexture">
								<c:if test="${skinTexture.code != '1111' }">
		                    		<option value="${skinTexture.code }">${skinTexture.name }</option>
		                    	</c:if>
		                    </c:forEach>
						</select>
					</div>
					<div class="input-label">出生年月：</div>
					<div class="input-contents">
						<input class="born-year" type="text" readonly />
					</div>
				</div>
				<div class="btn-container">
<!-- 					<a href="javascript:void(0);" class="save-btn">保存</a> -->
					<button class="save-btn" type="button">保存</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 包含尾部 -->
	<jsp:include page="../common/page_footer.jsp" />

	<script>
		var basePath = "${basePath}";
	</script>
	<script src="static\js\jquery.js"></script>
	<script type="text/javascript" src="static\plugins\BeAlert\BeAlert.js"></script>
	<script type="text/javascript">
		var laydate = layui.laydate, layer = layui.layer;
		laydate.render({
			elem : '.born-year',
			type : 'year',
			format : 'yyyy',
			theme : '#CC99CC'
		});
		$(".save-btn").click(function(){
			var _this = $(this);
			var skinTexture = $(".skin-texture").val();
			var bornYear = $(".born-year").val();
			if(skinTexture == "" || bornYear == ""){
				layer.msg("表单不能留空");
				return ;
			}
			
			_this.attr("disabled",true);
			var postUrl = basePath + "user/firstLogin.do";
			$.ajax({
				url: postUrl,
				data: {skinTexture:skinTexture,bornYear:bornYear},
				type: "POST",
				dataType: "json",
				success:function(result){
					if(result.success){
						alert("保存成功",
			  					"",
			  					function(){
			  						//确认按钮回调
									location.reload();
			  					},
			  					{type:'success',confirmButtonText: '好的'});
					}else{
						var title = "保存失败";
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
			  				};
			  			}else{
			  				tips = "其他错误";
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
	</script>
</body>

</html>
