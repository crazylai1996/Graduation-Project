<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/base.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <base href="${basePath }">
    <title>点评详情 - 爱美丽</title>
    <link rel="stylesheet" href="static\css\bootstrap.min.css">
    <link rel="stylesheet" href="static\plugins\star-rating\star-rating.css">
    <link rel="stylesheet" href="static\plugins\layui\css\layui.css"/>
	<script type="text/javascript" src="static\plugins\layui\layui.all.js"></script>
	<link rel="stylesheet" href="static\plugins\BeAlert\BeAlert.css"/>
    <link rel="stylesheet" href="static\css\comment_details.css">

  </head>
  <body>
  
  	<!-- 头部包含 -->
	<jsp:include page="../common/page_header.jsp" />

    <div class="main-wrapped">
      <div class="main-container">
        <div class="current-pos">
          <a href="#">首页</a><span class="split-line"></span>
          <a href="#">${productInfoVO.brandName }</a><span class="split-line"></span>
          <a href="#">${productInfoVO.productName }</a><span class="split-line"></span>
          <a href="#">全部点评</a><span class="split-line"></span>
          <sapn class="title">${commentInfoVO.articleTitle }</span>
        </div>
        <div class="main-contents">
          <div class="main-left">
            <!-- 心得用户 -->
            <div class="comment-user">
              <div class="user-info-wrapped">
                <div class="user-info">
                  <div class="info-left">
                    <a href="javascript:void(0);"><img src="${userInfoVO.portrait }"></a>
                  </div>
                  <div class="info-right">
                    <p class="user-name">${userInfoVO.nickname }</p>
                    <p class="skin-type">肤质<a class="val" href="javascript:void(0);">${userInfoVO.skinTexture }</a></p>
                    <p class="user-age">年龄<a class="val" href="javascript:void(0);">${userInfoVO.age }</a></p>
                    <p class="comment-sum">点评<a class="val" href="javascript:void(0);">999</a></p>
                    <p class="user-love">关注<a class="val" href="javascript:void(0);">0</a></p>
                  </div>
                </div>
              </div>
              <div class="user-more-comments">
                <div class="more-comment-label">
                  TA的其它点评<a href="javascript:void(0);" class="her-more">更多点评&gt;&gt;</a>
                </div>
                <ul class="more-comment-list">
                  <c:choose>
                  	<c:when test="${ commentInfoVO.productInfoVOs !=null and commentInfoVO.productInfoVOs.size()>0 }">
                  		<c:forEach items="${commentInfoVO.productInfoVOs }" var="productInfoVO">
                  			<li>
			                    <img class="pic" src="${productInfoVO.coverImage }">
			                    <p class="name"><a href="/IME/product/info/${productInfoVO.productId }.html">${productInfoVO.productName }</a></p>
			                  </li>
                  		</c:forEach>
                  	</c:when>
                  	<c:otherwise>
                  		<span class="no-more-products">找不到TA的更多点评</span> 
                  	</c:otherwise>
                  </c:choose>
                  <!-- <li>
                    <img class="pic" src="img\commodity_test.jpg">
                    <p class="name"><a href="javascript:void(0);">御泥坊美白嫩肤面膜(第四代升级版)</a></p>
                  </li>
                  <li>
                    <img class="pic" src="img\commodity_test.jpg">
                    <p class="name"><a href="javascript:void(0);">御泥坊美白嫩肤面膜(第四代升级版)</a></p>
                  </li>
                  <li>
                    <img class="pic" src="img\commodity_test.jpg">
                    <p class="name"><a href="javascript:void(0);">御泥坊美白嫩肤面膜(第四代升级版)</a></p>
                  </li>-->
                </ul>
              </div>
            </div>
            <!-- 心得用户结束 -->
            <!-- 心得详情开始 -->
            <div class="comment-details-container">
              <!-- 产品信息 -->
              <div class="product-info-wrapped">
                <div class="product-info">
                  <div class="pi-left">
                    <img src="${productInfoVO.coverImage }">
                  </div>
                  <div class="pi-right">
                    <p><a href="javascript:void(0);">${productInfoVO.productName }</a></p>
                    <p>
                      <a href="javascript:void(0);">${productInfoVO.referencePrice }元</a><span class="split-line"></span>
                      <a href="javascript:void(0);">${productInfoVO.spec }</a><span class="split-line"></span>
                      <a href="javascript:void(0);">${productInfoVO.brandName }</a>
                    </p>
                  </div>
                </div>
              </div>
              <!-- 产品信息结束 -->
              <!-- 心得标题开始 -->
              <div class="comment-title">
                <h1>${commentInfoVO.articleTitle }</h1>
                <span>
                  <input value="${commentInfoVO.worthMark }" type="number"
                     class="rating" min=0 max=5
                     step=0.5 data-size="xxs"
                    data-only-show="true" data-symbol="&#xe005;">
                </span>
              </div>
              <!-- 心得标题结束 -->
              <!-- 心得详情开始 -->
              <div class="comment-details">
                <!-- <p>空气污染，生活压力，
日光侵害，种种城市问题导致肌肤敏感情况愈演愈烈。
敏感肌护肤专家雅漾，携手中国医师协会皮肤科医师分会（CDA），
深入中国三大城市，对近5800名女性和近4400名男性进行敏感肌专业调查，
发现46%的女性和31%的男性有肌肤敏感问题，盲目选择护肤品会让本就脆弱的肌肤受到添加剂（如防腐剂）的二次刺激。</p>-->
				<p>${commentInfoVO.contentHtml }</p>
              </div>
              <!-- 心得详情结束 -->
              <!-- 心得操作 -->
              <div class="comment-btn">
                <a href="javascript:void(0);">(99)有用</a>
              </div>
              <!-- 心得操作结束  -->
              <!-- 上一篇、下一篇开始 -->
              <div class="lr_comment">
                <span class="l_comment">
                  上一篇：<!-- <a href="javascript:void(0);">雅漾舒护活泉喷雾</a> -->
                  <c:choose>
                  	<c:when test="${!empty commentInfoVO.lastCommentId }">
                  		<a href="/IME/comment/info/${commentInfoVO.lastCommentId }.html">${commentInfoVO.lastCommentTitle }</a>
                  	</c:when>
                  	<c:otherwise>
                  		<a href="javascript:void(0);">(暂无上一篇)</a>
                  	</c:otherwise>
                  </c:choose>
                </span>
                <span class="r_comment">
                  下一篇：<!-- <a href="javascript:void(0);">敏感和晒红的时候喷</a> -->
                  <c:choose>
                  	<c:when test="${!empty commentInfoVO.nextCommentId }">
                  		<a href="/IME/comment/info/${commentInfoVO.nextCommentId }.html">${commentInfoVO.nextCommentTitle }</a>
                  	</c:when>
                  	<c:otherwise>
                  		<a href="javascript:void(0);">(暂无下一篇)</a>
                  	</c:otherwise>
                  </c:choose>
                </span>
              </div>
              <!-- 上一篇、下一篇结束 -->
            </div>
            <!-- 心得详情结束 -->
            <!-- 心得回复开始 -->
            <ul class="comment-reply-container" data-page-num="1" data-pages="${pageResult.pages }">
            	<jsp:include page="reply_fragment.jsp"/>
              <!-- <li class="reply-item">
                <div class="left">
                  <div class="portrait-wrapped">
                    <img src="img\portrait_test.jpg">
                    <p>crazylai1996</p>
                    <p>点评数(
                      <font>999</font>)</p>
                  </div>
                </div>
                <div class="right">
                  <div class="triangle-pointer">
                    <div class="background">
                    </div>
                  </div>
                  <div class="reply-detail">
                    <div class="reply-user">
                      By<a>智慧果</a>&nbsp;&nbsp;合性皮肤&nbsp;&nbsp;31-39岁
                    </div>
                    <p class="reply-content">本着支持国货的心，网购了一套鸟家的面膜。 这款膜不是蚕丝膜，感觉像是纸膜，所以亲肤性方面自然要比蚕丝膜逊色不少。 精华液有淡淡的玫瑰香，但是量太少了，冬天的话，感觉颇有些不够敷。美白的话，也没有明显感觉。
                    </p>
                    <span class="reply-time"><span class="glyphicon glyphicon-time"></span>2018-03-13</span>
                    <hr>
                  </div>
                </div>
              </li>-->
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
            <!-- 心得回复结束 -->
            <!-- 心得回复框开始 -->
            <div class="reply-add-container">
              <form class="reply-form">
	              <input name="articleId" type="hidden" class="comment-id" value="${commentInfoVO.commentId }">
	              <input name="parentCommentId" type="hidden" class="parent-comment-id">
	              <div class="label">
	                添加回复
	              </div>
	              <hr>
	              <textarea class="reply-detail-textarea" name="replyDetail" rows="10" cols="88"></textarea>
	              <!-- 回复添加按钮 -->
	              <div class="reply-add-btn">
	                <a href="javascript:void(0);" class="add-btn">写好了</a>
	              </div>
              </form>
            </div>
            <!-- 心得回复框结束 -->
          </div>
          <div class="main-right">
            <!-- 当前产品信息块开始 -->
            <div class="current-product">
              <span class="current-label">当前点评的产品</span>
              <div class="base-info">
                <h1 class="name">
                  <a href="javascript:void(0);">${productInfoVO.productName }</a>
                </h1>
                <div class="pic-wrapped">
                  <img src="${productInfoVO.coverImage }">
                </div>
                <span class="comment-sum">
                  共<font>${productInfoVO.commentCount }</font>条点评
                </span>
                <span class="comment-more">
                  <a href="${basePath }product/info/${productInfoVO.productId}.html" target="_blank">查看它的更多点评&gt;&gt;</a>
                </span>
              </div>
            </div>
            <!-- 当前产品信息块结束 -->
            <!-- 更多热门点评开始 -->

            <!-- 更多热门点评结束 -->
          </div>
        </div>
      </div>
    </div>
	<!-- 包含尾部 -->
	<jsp:include page="../common/page_footer.jsp" />
    <script src="static\js\jquery.js"></script>
    <script src="static\plugins\star-rating\star-rating.js"></script>
    <script type="text/javascript" src="static\plugins\BeAlert\BeAlert.js"></script>
    <script src="static\js\comment_details.js"></script>
  </body>
</html>
