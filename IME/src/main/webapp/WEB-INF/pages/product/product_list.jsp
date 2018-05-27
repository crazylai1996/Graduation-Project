<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/base.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <base href="${basePath }">
    <title>化妆品大全 - 爱美丽</title>
    <link rel="stylesheet" href="static\css\bootstrap.min.css">
    <link rel="stylesheet" href="static\plugins\star-rating\star-rating.css">
    <link rel="stylesheet" href="static\css\product_list.css">
  </head>
  <body>
<!-- 头部包含 -->
  <jsp:include page="../common/page_header.jsp"/>
    <div class="main-wrapped">
      <div class="main-container">
        <!-- 当前位置 -->
        <div class="current-pos">
          <a href="#">首页</a><span class="split-line"></span>
          <a href="#">化妆品大全</a><span class="split-line"></span>
          <sapn class="title">化妆水</span>
        </div>
        <div class="main-contents">
          <!-- 产品相关属性条件筛选开始 -->
          <div class="property-filter-div">
            <h2>化妆水</h2>
            <ul class="filter-list">
              <li class="has-border">
                <div class="property-label">
                  品牌：
                </div>
                <div class="property-items">
                  <a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a>
                  <a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a>
                  <a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a>
                  <a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a>
                  <a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a>
                  <a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a>
                </div>
                <div class="property-more">
                  <a class="property-more-btn" href="javascript:void(0);">更多</a>
                </div>
                <div class="clear-float"></div>
              </li>
              <li class="has-border">
                <div class="property-label">
                  属性：
                </div>
                <div class="property-items">
                  <a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a>
                  <a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a>
                  <a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a>
                  <a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a>
                  <a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a>
                  <a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a>
                </div>
                <div class="property-more">
                  <a class="property-more-btn" href="javascript:void(0);">更多</a>
                </div>
                <div class="clear-float"></div>
              </li>
              <li>
                <div class="property-label">
                  功效：
                </div>
                <div class="property-items">
                  <a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a>
                  <a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a>
                  <a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a>
                  <a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a>
                  <a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a>
                  <a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a>
                </div>
                <div class="property-more">
                  <a class="property-more-btn" href="javascript:void(0);">更多</a>
                </div>
                <div class="clear-float"></div>
              </li>
            </ul>
          </div>
          <!-- 产品相关属性条件筛选结束 -->
          <!-- 左 -->
          <div class="main-left">
            <!-- 产品排序条件开始  -->
            <ul class="order-navi">
              <li><a href="#" class="current-order">最新</a></li>
              <li><a href="#">评分最高</a></li>
              <li><a href="#">点评最多</a></li>
              <li><a href="#">浏览最多</a></li>
              <li><a href="#">关注最多</a></li>
            </ul>
            <!-- 产品排序条件结束 -->
            <!-- 产品列表开始 -->
            <ul class="product-list">
            	<jsp:include page="product_item_fragment.jsp"/>
              <!-- <li class="product-item">
                <div class="left">
                  <div class="product-img-wrapped">
                    <img src="static\img\commodity_test.jpg">
                  </div>
                </div>
                <div class="right">
                  <p>
                    <a class="product-name" href="javascript:void(0);">雅漾舒护活泉喷雾</a>
                  </p>
                  <p class="rating-p">
                    <span class="rating-label">综合评价</span><span class="rating-wrapped"><input value="4" type="number"
                       class="rating" min=0 max=5
                       step=0.5 data-size="xxs"
                      data-only-show="true" data-symbol="&#xe005;"></span>
                  </p>
                  <p class="smaller-p">
                    <span class="p-label">参考价格：</span><span class="p-value">125-185元</span>
                    <span class="p-label">点评数：</span><span class="p-value">12810</span>
                  </p>
                  <div class="product-desc">
                    <p class="product-introduction">
                      <span>产品简介：</span>
                      <a href="#">拥有270年历史的纯天然雅漾活泉水，含有...</a>
                    </p>
                    <p class="product-newest-comment">
                      <span>最新点评：</span>
                      <a href="#">这款喷雾，喷出的喷雾非常细腻，可以直接喷在脸上拍一拍当做爽肤水，也可以喷在化妆棉上，借助化妆棉的擦拭作为保湿二次清洁水，...</a>
                    </p>
                  </div>
                </div>
              </li>
              <li class="product-item">
                <div class="left">
                  <div class="product-img-wrapped">
                    <img src="static\img\commodity_test.jpg">
                  </div>
                </div>
                <div class="right">
                  <p>
                    <a class="product-name" href="javascript:void(0);">雅漾舒护活泉喷雾</a>
                  </p>
                  <p class="rating-p">
                    <span class="rating-label">综合评价</span><span class="rating-wrapped"><input value="4" type="number"
                       class="rating" min=0 max=5
                       step=0.5 data-size="xxs"
                      data-only-show="true" data-symbol="&#xe005;"></span>
                  </p>
                  <p class="smaller-p">
                    <span class="p-label">参考价格：</span><span class="p-value">125-185元</span>
                    <span class="p-label">点评数：</span><span class="p-value">12810</span>
                  </p>
                  <div class="product-desc">
                    <p class="product-introduction">
                      <span>产品简介：</span>
                      <a href="#">拥有270年历史的纯天然雅漾活泉水，含有...</a>
                    </p>
                    <p class="product-newest-comment">
                      <span>最新点评：</span>
                      <a href="#">这款喷雾，喷出的喷雾非常细腻，可以直接喷在脸上拍一拍当做爽肤水，也可以喷在化妆棉上，借助化妆棉的擦拭作为保湿二次清洁水，...</a>
                    </p>
                  </div>
                </div>
              </li>-->
            </ul>
            <!-- 产品列表结束 -->
          </div>
        </div>
      </div>
    </div>
	<!-- 包含尾部 -->
 	<jsp:include page="../common/page_footer.jsp"/>

	 <script>
	 	var basePath = "${basePath}";
 	</script>
    <script src="static\js\jquery.js"></script>
    <script src="static\plugins\star-rating\star-rating.js"></script>
    <script src="static\js\product_list.js"></script>
  </body>
</html>
