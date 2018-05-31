<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/base.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<base href="${basePath }">
<title>化妆品详情 - 爱美丽</title>
<link rel="stylesheet" href="static\plugins\star-rating\star-rating.css">
<link rel="stylesheet" href="static\css\product_details.css" />
<link rel="stylesheet" href="static\css\bootstrap.min.css">
<link rel="stylesheet" href="static\plugins\layui\css\layui.css"/>
<script type="text/javascript" src="static\plugins\layui\layui.all.js"></script>
<link rel="stylesheet" href="static\plugins\BeAlert\BeAlert.css"/>
<link rel="stylesheet"
	href="static\plugins\kindeditor\themes\default\default.css">
</head>

<body>
	<!-- 头部包含 -->
	<jsp:include page="../common/page_header.jsp" />

	<div class="main-wrapped">
		<div class="main-container">
			<!-- 当前位置 -->
			<div class="current-pos">
				<a href="${basePath }">首页</a><span class="split-line"></span> 
				<a href="${basePath }product/list.html?brand=${productInfoVO.brandName }" target="_blank">
					${productInfoVO.brandName }</a><span
					class="split-line"></span>
				<sapn class="title">${productInfoVO.productName }</span>
			</div>
			<hr>
			<!-- 产品信息 -->
			<div class="product-container">
				<input class="product-id" type="hidden" value="${productInfoVO.productId }">
				<!-- 产品图片展示 -->
				<div class="product-images-container">
					<div class="image-display">
						<c:forEach items="${productInfoVO.productImages }" var="imageUrl"
							varStatus="status">
							<c:if test="${status.index == 0}">
								<div class="current-img">
									<span><img src="${imageUrl }" /></span>
								</div>
							</c:if>
							<c:if test="${status.index != 0}">
								<div>
									<span><img src="${imageUrl }" /></span>
								</div>
							</c:if>
						</c:forEach>
						<!-- <div class="current-img"><span><img src="static\img\commodity_test.jpg"/></span></div>
            <div><span><img src="static\img\commodity_test.jpg"/></span></div>
            <div><span><img src="static\img\commodity_test.jpg"/></span></div>-->
					</div>
					<div class="thumbnail-container">
						<span class="previous-btn"></span>
						<div class="thumbnail-list">
							<c:forEach items="${productInfoVO.productImages }" var="imageUrl"
								varStatus="status">
								<c:if test="${status.index == 0}">
									<a class="current-li"><img src="${imageUrl }" /></a>
								</c:if>
								<c:if test="${status.index != 0}">
									<a><img src="${imageUrl }" /></a>
								</c:if>
							</c:forEach>
							<!-- <a class="current-li"><img src="static\img\commodity_test.jpg"/></a>
              <a><img src="static\img\commodity_test.jpg"/></a>
              <a><img src="static\img\commodity_test.jpg"/></a>-->
						</div>
						<span class="next-btn"></span>
					</div>
				</div>
				<!-- 产品基本信息 -->
				<div class="product-base-info">
					<h1>
						<a href="#">${productInfoVO.productName }</a>
					</h1>
					<p>
						参考价格：<span class="price">${productInfoVO.referencePrice }元</span>
					</p>
					<p>
						产品规格：<span class="spec">${productInfoVO.spec }</span>
					</p>
					<p>
						产品种类：<a href="#">${productInfoVO.classifyName }</a><span></span>产品功效：<a
							href="#">${productInfoVO.effectName }</a>
					</p>
					<p>
						产品属性：<a href="#">${productInfoVO.propertyName }</a>
					</p>
					<p>
						适用肤质：<a href="#">${productInfoVO.skinTexture }</a>
					</p>
					<p>
						品牌：<a href="#">${productInfoVO.brandName }</a>
					</p>
					<div class="product-details">
						<span>产品信息：</span>
						<p>${productInfoVO.desc }
							<a href="javascript:void(0);" class="show-more">展开&gt;&gt;</a>
						</p>
					</div>
				</div>
				<!-- 产品评级分布 -->
				<div class="product-rating-wrapped">
					<div class="product-rating">
						<!-- 评分统计 -->
						<div class="rating-brief">
							<div class="rating-title">综合性价评分</div>
							<div class="rating-count">
								<div class="left">
									<strong>${commentAnalysis.avgScore }</strong>
								</div>
								<div class="right">
									<span class="right-up"> <input value="4.5" type="number"
										class="rating" min=0 max=5 step=0.5 data-size="xxs"
										data-only-show="true" data-symbol="&#xe005;">
									</span> <span class="right-down"><a href="javascript:void(0);">${commentAnalysis.commentCount }个评价</a></span>
								</div>
							</div>
						</div>
						<!-- 评分分布 -->
						<div class="rating-more">
							<ul class="rating-details">
								<!-- 一个 -->
								<li class="rating-on-weight"><span class="rating-wrapped">
										<input value="5" type="number" class="rating" min=0 max=5
										step=0.5 data-size="xxxs" data-only-show="true"
										data-symbol="&#xe005;">
								</span> <span class="rating-power-wrapped"> <span
										class="rating-power" data-power="${commentAnalysis.scoreAnalysis.fiveHearts }"></span>
								</span> <span class="rating-per"> ${commentAnalysis.scoreAnalysis.fiveHearts * 100}% </span></li>
								<!-- 一个结束  -->
								<!-- 一个 -->
								<li class="rating-on-weight"><span class="rating-wrapped">
										<input value="4" type="number" class="rating" min=0 max=5
										step=0.5 data-size="xxxs" data-only-show="true"
										data-symbol="&#xe005;">
								</span> <span class="rating-power-wrapped"> <span
										class="rating-power" data-power="${commentAnalysis.scoreAnalysis.fourHearts}"></span>
								</span> <span class="rating-per"> ${commentAnalysis.scoreAnalysis.fourHearts * 100 }% </span></li>
								<!-- 一个结束  -->
								<!-- 一个 -->
								<li class="rating-on-weight"><span class="rating-wrapped">
										<input value="3" type="number" class="rating" min=0 max=5
										step=0.5 data-size="xxxs" data-only-show="true"
										data-symbol="&#xe005;">
								</span> <span class="rating-power-wrapped"> <span
										class="rating-power" data-power="${commentAnalysis.scoreAnalysis.threeHearts}"></span>
								</span> <span class="rating-per"> ${commentAnalysis.scoreAnalysis.threeHearts* 100}% </span></li>
								<!-- 一个结束  -->
								<!-- 一个 -->
								<li class="rating-on-weight"><span class="rating-wrapped">
										<input value="2" type="number" class="rating" min=0 max=5
										step=0.5 data-size="xxxs" data-only-show="true"
										data-symbol="&#xe005;">
								</span> <span class="rating-power-wrapped"> <span
										class="rating-power" data-power="${commentAnalysis.scoreAnalysis.twoHearts}"></span>
								</span> <span class="rating-per"> ${commentAnalysis.scoreAnalysis.twoHearts* 100}% </span></li>
								<!-- 一个结束  -->
								<!-- 一个 -->
								<li class="rating-on-weight"><span class="rating-wrapped">
										<input value="1" type="number" class="rating" min=0 max=5
										step=0.5 data-size="xxxs" data-only-show="true"
										data-symbol="&#xe005;">
								</span> <span class="rating-power-wrapped"> <span
										class="rating-power" data-power="${commentAnalysis.scoreAnalysis.oneHeart}"></span>
								</span> <span class="rating-per"> ${commentAnalysis.scoreAnalysis.oneHeart* 100}% </span></li>
								<!-- 一个结束  -->
							</ul>
							<hr>
							<div class="product-ops">
								<div class="btn-wrapped">
									<a href="/IME/product/info/${productInfoVO.productId }.html#addComment" class="reply-btn">添加点评</a>
								</div>
								<div class="btn-wrapped follow-ops">
									<c:choose>
										<c:when test="${productInfoVO.follow }">
											<a href="javascript:void(0);" class="love-btn unfollow-btn"> 
												<font><i class="layui-icon layui-icon-star-fill"></i> 已关注&nbsp;&nbsp;</font>|&nbsp;&nbsp;
												<font class="follow-count">${productInfoVO.followCount }</font>人
											</a>
										</c:when>
										<c:otherwise>
											<a href="javascript:void(0);" class="love-btn follow-btn"> 
												<font><i class="layui-icon layui-icon-star"></i> 关注&nbsp;&nbsp;</font>|&nbsp;&nbsp;
												<font class="follow-count">${productInfoVO.followCount }</font>人
											</a>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
		<!-- 产品使用心得分析 -->
		<div class="comment-container">
			<div class="comment-containter-left">
				<!-- 使用心得分析 -->
				<div class="comment-analysis">
					<div class="analysis-title">
						<a class="name" href="javascript:void(0);">${productInfoVO.productName }</a> <span
							class="title">使用心得分析</span>
					</div>
					<div class="analysis-contents">
						<div class="analysis-details">
							<div class="analysis-type">
								<span class="type-name">评分分布</span>
								<ul class="type-details">
									<li class="type-on-weight"><span class="type-item">
											5星 </span> <span class="item-power-wrapped"> <span
											class="item-power" data-power="${commentAnalysis.scoreAnalysis.fiveHearts}"></span>
									</span> <span class="item-per"> ${commentAnalysis.scoreAnalysis.fiveHearts * 100}% </span></li>
									<li class="type-on-weight"><span class="type-item">
											4星 </span> <span class="item-power-wrapped"> <span
											class="item-power" data-power="${commentAnalysis.scoreAnalysis.fourHearts}"></span>
									</span> <span class="item-per"> ${commentAnalysis.scoreAnalysis.fourHearts * 100}% </span></li>
									<li class="type-on-weight"><span class="type-item">
											3星 </span> <span class="item-power-wrapped"> <span
											class="item-power" data-power="${commentAnalysis.scoreAnalysis.threeHearts}"></span>
									</span> <span class="item-per"> ${commentAnalysis.scoreAnalysis.threeHearts * 100}% </span></li>
									<li class="type-on-weight"><span class="type-item">
											2星 </span> <span class="item-power-wrapped"> <span
											class="item-power" data-power="${commentAnalysis.scoreAnalysis.twoHearts}"></span>
									</span> <span class="item-per"> ${commentAnalysis.scoreAnalysis.twoHearts * 100}% </span></li>
									<li class="type-on-weight"><span class="type-item">
											1星 </span> <span class="item-power-wrapped"> <span
											class="item-power" data-power="${commentAnalysis.scoreAnalysis.oneHeart}"></span>
									</span> <span class="item-per"> ${commentAnalysis.scoreAnalysis.oneHeart * 100}% </span></li>
								</ul>
							</div>
							<div class="analysis-type">
								<span class="type-name">肤质分布</span>
								<ul class="type-details">
									<li class="type-on-weight"><span class="type-item">
											中性 </span> <span class="item-power-wrapped"> <span
											class="item-power" data-power="${commentAnalysis.scoreAnalysis.neutralSkin }"></span>
									</span> <span class="item-per"> ${commentAnalysis.scoreAnalysis.neutralSkin * 100 }% </span></li>
									<li class="type-on-weight"><span class="type-item">
											干性 </span> <span class="item-power-wrapped"> <span
											class="item-power" data-power="${commentAnalysis.scoreAnalysis.drySkin }"></span>
									</span> <span class="item-per"> ${commentAnalysis.scoreAnalysis.drySkin * 100 }% </span></li>
									<li class="type-on-weight"><span class="type-item">
											油性 </span> <span class="item-power-wrapped"> <span
											class="item-power" data-power="${commentAnalysis.scoreAnalysis.oilySkin }"></span>
									</span> <span class="item-per"> ${commentAnalysis.scoreAnalysis.oilySkin * 100 }% </span></li>
									<li class="type-on-weight"><span class="type-item">
											混合 </span> <span class="item-power-wrapped"> <span
											class="item-power" data-power="${commentAnalysis.scoreAnalysis.mixedSkin }"></span>
									</span> <span class="item-per"> ${commentAnalysis.scoreAnalysis.mixedSkin * 100 }% </span></li>
									<li class="type-on-weight"><span class="type-item">
											敏感 </span> <span class="item-power-wrapped"> <span
											class="item-power" data-power="${commentAnalysis.scoreAnalysis.sensitiveSkin }"></span>
									</span> <span class="item-per"> ${commentAnalysis.scoreAnalysis.sensitiveSkin * 100 }% </span></li>
								</ul>
							</div>
							<div class="analysis-type">
								<span class="type-name">年龄分布</span>
								<ul class="type-details">
									<li class="type-on-weight"><span class="type-item">
											&#8804;25 </span> <span class="item-power-wrapped"> <span
											class="item-power" data-power="${commentAnalysis.ageAnalysis.phase1 }"></span>
									</span> <span class="item-per"> ${commentAnalysis.ageAnalysis.phase1 * 100 }% </span></li>
									<li class="type-on-weight"><span class="type-item">
											25-30 </span> <span class="item-power-wrapped"> <span
											class="item-power" data-power="${commentAnalysis.ageAnalysis.phase2 }"></span>
									</span> <span class="item-per"> ${commentAnalysis.ageAnalysis.phase2 * 100 }% </span></li>
									<li class="type-on-weight"><span class="type-item">
											30-40 </span> <span class="item-power-wrapped"> <span
											class="item-power" data-power="${commentAnalysis.ageAnalysis.phase3 }"></span>
									</span> <span class="item-per"> ${commentAnalysis.ageAnalysis.phase3 * 100 }% </span></li>
									<li class="type-on-weight"><span class="type-item">
											40-45 </span> <span class="item-power-wrapped"> <span
											class="item-power" data-power="${commentAnalysis.ageAnalysis.phase4 }"></span>
									</span> <span class="item-per"> ${commentAnalysis.ageAnalysis.phase4 * 100}% </span></li>
									<li class="type-on-weight"><span class="type-item">
											&#8805;45 </span> <span class="item-power-wrapped"> <span
											class="item-power" data-power="${commentAnalysis.ageAnalysis.phase5 }"></span>
									</span> <span class="item-per"> ${commentAnalysis.ageAnalysis.phase5 * 100 }% </span></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
				<!-- 使用心得块 -->
				<div class="comment-contents">
					<!-- 使用心得标题 -->
					<div class="comment-list-title">
						<a class="name" href="javascript(0);">${productInfoVO.productName }</a> <span
							class="title">使用心得</span>
					</div>
					<!-- 使用心得筛选条件 -->
					<div class="comment-search-wrapped">
						<span class="search-label">分类浏览</span> <span class="search-params">
							<form class="comment-search-form">
								<select class="rating" name="">
									<option value='' disabled selected style='display: none;'>请指定评分</option>
								</select> <select class="skin-type" name="">
									<option value='' disabled selected style='display: none;'>请指定肤质</option>
								</select> <select class="age" name="">
									<option value='' disabled selected style='display: none;'>请指定年龄</option>
								</select>
							</form>
						</span> <a href="#" class="search-btn">搜索</a>
					</div>
					<!-- 使用心得列表 -->
					<ul class="comment-list" data-page-num="1" data-pages="${pageResult.pages }">
						<jsp:include page="../comment/comment_fragment.jsp"/>
						<!-- <li class="comment-item">
							<div class="left">
								<div class="portrait-wrapped">
									<img src="static\img\portrait_test.jpg">
									<p>crazylai1996</p>
									<p>
										点评数( <font>999</font>)
									</p>
									<p>
										<a href="#" class="love-her-btn">关注她</a>
									</p>
								</div>
							</div>
							<div class="right">
								<div class="triangle-pointer">
									<div class="background"></div>
								</div>
								<div class="comment-detail">
									<a href="#" class="comment-title">精华液有点不够敷的赶脚</a> <input
										value="4" type="number" class="rating" min=0 max=5 step=0.5
										data-size="xxs" data-only-show="true" data-symbol="&#xe005;">
									<div class="comment-user">
										By<a>智慧果</a>&nbsp;&nbsp;合性皮肤&nbsp;&nbsp;31-39岁&nbsp;&nbsp;购买方式：网上购买
									</div>
									<p class="comment-comment">本着支持国货的心，网购了一套鸟家的面膜。
										这款膜不是蚕丝膜，感觉像是纸膜，所以亲肤性方面自然要比蚕丝膜逊色不少。
										精华液有淡淡的玫瑰香，但是量太少了，冬天的话，感觉颇有些不够敷。美白的话，也没有明显感觉。</p>
									<span class="comment-time">2018-03-13</span>
									<hr>
									<div class="comment-ops">
										(0)<a href="#">回复</a>(0)<a>有用</a>
									</div>
								</div>
							</div>
						</li>
						<hr>
						<li class="comment-item">
							<div class="left">
								<div class="portrait-wrapped">
									<img src="static\img\portrait_test.jpg">
									<p>crazylai1996</p>
									<p>
										点评数( <font>999</font>)
									</p>
									<p>
										<a href="#" class="love-her-btn">关注她</a>
									</p>
								</div>
							</div>
							<div class="right">
								<div class="triangle-pointer">
									<div class="background"></div>
								</div>
								<div class="comment-detail">
									<a href="#" class="comment-title">精华液有点不够敷的赶脚</a> <input
										value="4" type="number" class="rating" min=0 max=5 step=0.5
										data-size="xxs" data-only-show="true" data-symbol="&#xe005;">
									<div class="comment-user">
										By<a>智慧果</a>&nbsp;&nbsp;合性皮肤&nbsp;&nbsp;31-39岁&nbsp;&nbsp;购买方式：网上购买
									</div>
									<p class="comment-comment">本着支持国货的心，网购了一套鸟家的面膜。
										这款膜不是蚕丝膜，感觉像是纸膜，所以亲肤性方面自然要比蚕丝膜逊色不少。
										精华液有淡淡的玫瑰香，但是量太少了，冬天的话，感觉颇有些不够敷。美白的话，也没有明显感觉。</p>
									<span class="comment-time"><span
										class="glyphicon glyphicon-time"></span>2018-03-13</span>
									<hr>
									<div class="comment-ops">
										(0)<a href="#">回复</a>(0)<a>有用</a>
									</div>
								</div>
							</div>
						</li>
						<hr>-->
					</ul>
					<!-- 加载更多 -->
		            <div class="load-more-wrapped">
		            	<c:choose>
		            		<c:when test="${pageResult.pages > 1 }">
		            			<a class="load-more-btn" href="javascript:void(0);">加载更多</a>
		            		</c:when>
		            		<c:otherwise>
		            			<span class="no-more-load">没有更多了</span>
		            		</c:otherwise>
		            	</c:choose>
		            </div>
				</div>
				<!-- 评论框 -->
				<div id="addComment" class="comment-add-container">
					<div class="label">我来点评</div>
					<hr>
					<div class="title-input">
						<span class="comment-title-label">标题：</span> 
						<input class="comment-title comment-title-input" type="text" name="">
					</div>
					<div class="rating-input">
						<input type="hidden" class="product-rating">
						<span class="comment-rating-label">性价评分：</span> <input value="0"
							type="number" class="rating product-rating-input" min="0" max="5" step="1" data-size="xs"
							data-symbol="&#xe005;">
						<div class="buy-way">
							<input type="hidden" class="buy-way-input">
							<span class="buy-way-title">购买方式</span>
							<ul class="buy-way-sel more-sel">
								<c:forEach items="${buyWays }" var="buyWay">
									<li data-code="${buyWay.code }">${buyWay.name }</li>
								</c:forEach>
								<!-- <li>天猫商城</li>
								<li>京东商城</li>
								<li>其他方式</li> -->
							</ul>
						</div>
					</div>
					<!-- 评论框 -->
					<textarea cols="0" rows="5" name="introduction"
						class="form-control" id="comment-input"
						style="margin: 0px -0.5px 0px 0px; height: 250px; width: 100%;">
          </textarea>
					<!-- 评论添加按钮 -->
					<div class="comment-add-btn">
						<span class="word-count-wrapped">当前字数：<span class="word-count">0</span></span>
<!-- 						<a href="javascript:void(0);" class="add-btn">写好了</a> -->
							<button type="button" class="add-btn">写好了</button>
					</div>
				</div>
			</div>
			<div class="comment-containter-right">
				<div class="same-class-rec">
					<div class="scr-title">
						<a href="#" class="scr-title-name">同类产品推荐</a> <a href="#"
							class="see-more">换一换</a>
					</div>
					<ul class="scr-list">
						<li class="scr-item"><img class="left picture"
							src="static\img\commodity_test.jpg">
							<div class="right">
								<h3 class="name">
									<a href="#">NEWA/妞娃家用RF射频电子美容仪器</a>
								</h3>
								<p class="count">
									<span>4.9分</span>| <span><a href="#">999</a>点评</span>
								</p>
							</div></li>
						<li class="scr-item"><img class="left picture"
							src="static\img\commodity_test.jpg">
							<div class="right">
								<h3 class="name">
									<a href="#">NEWA/妞娃家用RF射频电子美容仪器</a>
								</h3>
								<p class="count">
									<span>4.9分</span>| <span><a href="#">999</a>点评</span>
								</p>
							</div></li>
						<li class="scr-item"><img class="left picture"
							src="static\img\commodity_test.jpg">
							<div class="right">
								<h3 class="name">
									<a href="#">NEWA/妞娃家用RF射频电子美容仪器</a>
								</h3>
								<p class="count">
									<span>4.9分</span>| <span><a href="#">999</a>点评</span>
								</p>
							</div></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<!-- 包含尾部 -->
	<jsp:include page="../common/page_footer.jsp" />
	
	<script type="text/javascript">
		var basePath = "${basePath}";
	</script>
	<script src="static\js\jquery.js"></script>
	<script src="static\plugins\star-rating\star-rating.js"></script>
	<script type="text/javascript"
		src="static\plugins\kindeditor\kindeditor-all-min.js"></script>
	<script type="text/javascript"
		src="static\plugins\kindeditor\lang\zh-CN.js"></script>
	<script type="text/javascript" src="static\plugins\BeAlert\BeAlert.js"></script>
	<script src="static\js\product_details.js"></script>
</body>

</html>
