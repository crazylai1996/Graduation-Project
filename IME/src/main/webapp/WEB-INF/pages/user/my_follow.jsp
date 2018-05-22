<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/base.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<base href="${basePath }">
<title>我的关注 - 爱美丽</title>
<link rel="stylesheet" href="static\css\bootstrap.min.css">
<link rel="stylesheet" href="static\plugins\BeAlert\BeAlert.css" />
<link rel="stylesheet" href="static\css\my_follow.css">
</head>
<body>
	<!-- 头部包含 -->
	<jsp:include page="../common/page_header.jsp" />

	<div class="main-wrapped">
		<div class="main-container">
			<!-- 当前位置开始 -->
			<div class="current-pos">
				<a href="${basePath }">首页</a><span class="split-line"></span> <a
					href="javascript:void(0);">用户中心</a><span class="split-line"></span>
				<sapn class="title">账号设置</span>
			</div>
			<!-- 当前位置结束 -->
			<!-- 主体内容开始 -->
			<div class="main-contents">
				<div class="main-left">
					<jsp:include page="user_navi.jsp" />
				</div>
				<div class="main-right">
					<div class="follow-container">
						<h1 class="title">我的关注</h1>
						<hr class="line">
						<div class="follow-details">
							<!-- 关注的标签 开始 -->
							<div class="followed-tag">
								<div class="follow-title">关注的标签</div>
								<hr class="title-line">
								<!-- 关注的标签 列表 -->
								<div class="tag-list">
									<p>
										<c:forEach items="${followedClasses }" var="followedClass">
											<a class="unfollow-this-class" data-class-id="${followedClass.classId }" href="javascript:void(0);">${followedClass.className }</a>
										</c:forEach>
										<!-- <a href="javascript:void(0);">眼唇护理</a><a
											href="javascript:void(0);">彩妆</a><a
											href="javascript:void(0);">香氛</a> <a
											href="javascript:void(0);">眼唇护理</a><a
											href="javascript:void(0);">彩妆</a><a
											href="javascript:void(0);">香氛</a> <a
											href="javascript:void(0);">眼唇护理</a><a
											href="javascript:void(0);">彩妆</a><a
											href="javascript:void(0);">香氛</a> <a
											href="javascript:void(0);">眼唇护理</a><a
											href="javascript:void(0);">彩妆</a><a
											href="javascript:void(0);">香氛</a> <a
											href="javascript:void(0);">眼唇护理</a><a
											href="javascript:void(0);">彩妆</a><a
											href="javascript:void(0);">香氛</a> <a
											href="javascript:void(0);">眼唇护理</a><a
											href="javascript:void(0);">彩妆</a><a
											href="javascript:void(0);">香氛</a> <a
											href="javascript:void(0);">眼唇护理</a><a
											href="javascript:void(0);">彩妆</a><a
											href="javascript:void(0);">香氛</a>  -->
											<a href="javascript:void(0);" class="add-more-tag">关注更多</a>
									</p>
								</div>
							</div>
							<!-- 关注的标签 结束  -->
							<!-- 关注的用户 开始 -->
							<div class="followed-user">
								<div class="follow-title">关注的用户</div>
								<hr class="title-line">
								<!-- 关注的用户 列表 -->
								<div class="user-list-wrapped">
									<c:choose>
										<c:when test="${empty followedUsers or followedUsers.size() == 0}">
											<div class="no-follow">
							                	  暂时没有关注的用户
							              	</div>
										</c:when>
										<c:otherwise>
											<ul class="user-list">
												<c:forEach items="${followedUsers }" var="followedUser">
												<li><a class="portrait-wrapped"
													href="javascript:void(0);"><img class="user-portrait"
														src="${followedUser.portrait }"></a>
													<div class="user-info">
														<div class="user-name-p">
															<a class="user-name" href="javascript:void(0);">${followedUser.nickname }</a>
															<div class="more-info-down">
																<div class="arrow">
																	<div class="arrow-inner"></div>
																</div>
																<div class="more-info-container">
																	<div class="more-info"></div>
																</div>
															</div>
														</div>
														<p class="user-introduction-p">
															<span class="user-introduction">${followedUser.introduction }</span>
														</p>
													</div>
													<div class="user-op">
														<a href="javascript:void(0);" data-user-id="${followedUser.userId }" class="follow-ornot-btn unfollow-user-btn">已关注</a>
													</div></li>
													</c:forEach>
											</ul>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
							<!-- 关注的用户 结束  -->
							<!-- 关注的产品 开始 -->
							<div class="followed-product">
								<div class="follow-title">关注的产品</div>
								<hr class="title-line">
								<c:choose>
									<c:when test="${empty followedProducts or followedProducts.size() == 0 }">
										<div class="no-follow">
							                	  暂时没有关注的产品
							              	</div>
									</c:when>
									<c:otherwise>
										<ul class="product-list">
										<c:forEach items="${followedProducts }" var="followedProduct">
									<li class="product-item">
										<div class="picture-wrapped">
											<div class="picture-container">
												<img class="product-picture" src="${followedProduct.coverImage }">
												<div class="product-count">
													<p class="browser">
														浏览：<a href="javascript:void(0);">99</a>
													</p>
													<p class="follow">
														收藏：<a href="javascript:void(0);">999</a>
													</p>
													<p class="comment">
														点评：<a href="javascript:void(0);">999</a>
													</p>
													<a href="javascript:void(0);"
														class="cancel-love unfollow-product-btn" data-product-id="${followedProduct.productId }">
													<i class="layui-icon layui-icon-star-fill"></i> 
													</a>
												</div>
											</div>
											<p class="product-name-p">
												<a class="product-name" target="_blank" href="${basePath }product/info/${followedProduct.productId }.html">${followedProduct.productName }</a>
											</p>
											<p class="follow-time">收藏于：${followedProduct.followTime }</p>
										</div>
									</li>
									</c:forEach>
									</ul>
									</c:otherwise>
								</c:choose>
								<!-- <ul class="product-list">
									<li class="product-item">
										<div class="picture-wrapped">
											<div class="picture-container">
												<img class="product-picture" src="static\img\commodity_test.jpg">
												<div class="product-count">
													<p class="browser">
														浏览：<a href="javascript:void(0);">99</a>
													</p>
													<p class="follow">
														收藏：<a href="javascript:void(0);">999</a>
													</p>
													<p class="comment">
														点评：<a href="javascript:void(0);">999</a>
													</p>
													<a href="javascript:void(0);"
														class="glyphicon glyphicon-trash cancel-love"></a>
												</div>
											</div>
											<p class="product-name-p">
												<a class="product-name" href="javascript:void(0);">清扬去屑精华乳</a>
											</p>
											<p class="follow-time">收藏于：2017-3-22</p>
										</div>
									</li>
									<li class="product-item">
										<div class="picture-wrapped">
											<div class="picture-container">
												<img class="product-picture" src="static\img\commodity_test.jpg">
												<div class="product-count">
													<p class="browser">
														浏览：<a href="javascript:void(0);">99</a>
													</p>
													<p class="follow">
														收藏：<a href="javascript:void(0);">999</a>
													</p>
													<p class="comment">
														点评：<a href="javascript:void(0);">999</a>
													</p>
													<a href="javascript:void(0);"
														class="glyphicon glyphicon-trash cancel-love"></a>
												</div>
											</div>
											<p class="product-name-p">
												<a class="product-name" href="javascript:void(0);">清扬去屑精华乳</a>
											</p>
											<p class="follow-time">收藏于：2017-3-22</p>
										</div>
									</li>
									<li class="product-item">
										<div class="picture-wrapped">
											<div class="picture-container">
												<img class="product-picture" src="static\img\commodity_test.jpg">
												<div class="product-count">
													<p class="browser">
														浏览：<a href="javascript:void(0);">99</a>
													</p>
													<p class="follow">
														收藏：<a href="javascript:void(0);">999</a>
													</p>
													<p class="comment">
														点评：<a href="javascript:void(0);">999</a>
													</p>
													<a href="javascript:void(0);"
														class="glyphicon glyphicon-trash cancel-love"></a>
												</div>
											</div>
											<p class="product-name-p">
												<a class="product-name" href="javascript:void(0);">清扬去屑精华乳</a>
											</p>
											<p class="follow-time">收藏于：2017-3-22</p>
										</div>
									</li>
									<li class="product-item">
										<div class="picture-wrapped">
											<div class="picture-container">
												<img class="product-picture" src="static\img\commodity_test.jpg">
												<div class="product-count">
													<p class="browser">
														浏览：<a href="javascript:void(0);">99</a>
													</p>
													<p class="follow">
														收藏：<a href="javascript:void(0);">999</a>
													</p>
													<p class="comment">
														点评：<a href="javascript:void(0);">999</a>
													</p>
													<a href="javascript:void(0);"
														class="glyphicon glyphicon-trash cancel-love"></a>
												</div>
											</div>
											<p class="product-name-p">
												<a class="product-name" href="javascript:void(0);">清扬去屑精华乳</a>
											</p>
											<p class="follow-time">收藏于：2017-3-22</p>
										</div>
									</li>
									<li class="product-item">
										<div class="picture-wrapped">
											<div class="picture-container">
												<img class="product-picture" src="static\img\commodity_test.jpg">
												<div class="product-count">
													<p class="browser">
														浏览：<a href="javascript:void(0);">99</a>
													</p>
													<p class="follow">
														收藏：<a href="javascript:void(0);">999</a>
													</p>
													<p class="comment">
														点评：<a href="javascript:void(0);">999</a>
													</p>
													<a href="javascript:void(0);"
														class="glyphicon glyphicon-trash cancel-love"></a>
												</div>
											</div>
											<p class="product-name-p">
												<a class="product-name" href="javascript:void(0);">清扬去屑精华乳</a>
											</p>
											<p class="follow-time">收藏于：2017-3-22</p>
										</div>
									</li>
									<li class="product-item">
										<div class="picture-wrapped">
											<div class="picture-container">
												<img class="product-picture" src="static\img\commodity_test.jpg">
												<div class="product-count">
													<p class="browser">
														浏览：<a href="javascript:void(0);">99</a>
													</p>
													<p class="follow">
														收藏：<a href="javascript:void(0);">999</a>
													</p>
													<p class="comment">
														点评：<a href="javascript:void(0);">999</a>
													</p>
													<a href="javascript:void(0);"
														class="glyphicon glyphicon-trash cancel-love"></a>
												</div>
											</div>
											<p class="product-name-p">
												<a class="product-name" href="javascript:void(0);">清扬去屑精华乳</a>
											</p>
											<p class="follow-time">收藏于：2017-3-22</p>
										</div>
									</li>
								</ul> -->
							</div>
							<!-- 关注的产品 结束 -->
						</div>
					</div>
					<div class="more-info-templet" style="display: none;">
						<div class="left">
							<img src="static\img\portrait_test.jpg">
							<p>
								<a href="javascript:void(0);" class="more-name">姓赖的先生</a>
							</p>
							<p class="second">
								<span>关注<a href="javascript:void(0);">99</a></span> <span>心得<a
									href="javascript:void(0);">999</a></span>
							</p>
						</div>
						<div class="right">
							<p class="first">
								<span>男</span><span>广东省 广州市</span>
							</p>
							<p class="second">
								<span class="more-introduction">接受真实的自己接受真实的自己接受真实的自己接受真实的自己接受真实的自己接受真实的自己接受真实的自己接受真实的自己接受真实的自己接受真实的自己接受真实的自己接受真实的自己接受真实的自己接受真实的自己</span>
							</p>
							<p class="third">
								<a href="javascript:void(0);" class="follow-her-btn">关注她</a> <a
									href="javascript:void(0);" class="send-message-btn">发消息</a>
							</p>
						</div>
					</div>
				</div>
			</div>
			<!-- 主体内容结束 -->
		</div>
	</div>
	<!-- 包含尾部 -->
	<jsp:include page="../common/page_footer.jsp" />

	<script>
		var basePath = "${basePath}";
	</script>
	<script src="static\js\jquery.js"></script>
	<script src="static\plugins\BeAlert\BeAlert.js"></script>
	<script src="static\js\my_follow.js"></script>
</body>
</html>
