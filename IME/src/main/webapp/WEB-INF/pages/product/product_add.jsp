<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/base.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=Edge">
  <base href="${basePath }">
  <title>添加新产品 - 爱美丽</title>
  <link rel="stylesheet" href="static\css\bootstrap.min.css">
  <link rel="stylesheet" href="static\plugins\layui\css\layui.css"/>
  <script type="text/javascript" src="static\plugins\layui\layui.all.js"></script>
  <link rel="stylesheet" href="static\plugins\BeAlert\BeAlert.css"/>
  <link rel="stylesheet" href="static\css\product_add.css">
  <link rel="stylesheet" href="static\css\picture_clip.css">
</head>

<body>
  <!-- 头部包含 -->
  <jsp:include page="../common/page_header.jsp"/>
  
  <div class="main-wrapped">
    <div class="main-container">
      <!-- 当前位置 -->
      <div class="current-pos">
        <a href="${basePath }">首页</a><span class="split-line"></span>
        <sapn class="title">添加新产品</span>
      </div>
      <!-- <div class="quick-get-info">
        <a href="javascript:void(0);" class="pointer">快速获取&gt;&gt;</a>
        <span class="expand-more">
          <span class="" class="tips">输入购买地址:</span>
          <input type="text" name="" value="">
          <a href="#" class="get-btn">获取</a>
        </span>
      </div> -->
      <div class="product-add-container">
        <div class="add-title-label">
          <a href="javascript:void(0);">商品信息</a>
        </div>
        <div class="product-form-div">
          <ul class="input-list">
            <form class="product-form">
              <li class="input-item">
                <div class="input-label">
                  品牌名称：
                </div>
                <div class="input-contents">
                  <input class="lg-input product-brand-input" type="text" name="brandName" required="required" readonly="readonly">
                  <input type="hidden" class="product-brand-id-input" name="brand">
                  <span class="input-tips">*必填</span>
                </div>
              </li>
              <li class="input-item">
                <div class="input-label">
                  化妆品名称：
                </div>
                <div class="input-contents">
                  <input class="lg-input" type="text" name="productName" required="required">
                  <span class="input-tips">*必填</span>
                </div>
              </li>
              <li class="input-item">
                <div class="input-label">
                  产品上市年月：
                </div>
                <div class="input-contents">
                  <input class="md-input product-ttm" type="text" name="comeInDate">
                </div>
              </li>
              <li class="input-item">
                <div class="input-label">
                  产品规格：
                </div>
                <div class="input-contents">
                  <input class="xs-input spec-val" type="text" name="">
                  <select class="xs-input spec-unit">
                    <option value="g">g</option>
                    <option value="ml">ml</option>
                  </select>
                </div>
              </li>
              <li class="input-item">
                <div class="input-label">
                  参考价格：
                </div>
                <div class="input-contents">
                  <input class="xs-input" type="text" name="referencePrice" required="required">元
                  <span class="input-tips">*必填</span>
                </div>
              </li>
              <li class="input-item">
                <div class="input-label">
                  产品种类：
                </div>
                <div class="input-contents">
                  <select class="sm-input classify-1st">
                    <!-- <option value="">面部护肤</option>
                    <option value="">魅力彩妆</option>-->
                    <c:forEach items="${cosmeticClasses }" var="cosmeticClass">
                    	<option value="${cosmeticClass.classId }">${cosmeticClass.className }</option>
                    </c:forEach>
                  </select>
                  <select class="sm-input classify-2nd" name="classify">
                    <!--  <option value="">脸部保养</option>
                    <option value="">卸妆产品</option>-->
                    <c:forEach items="${cosmeticClasses.get(0).childClasses }" var="cosmeticClass">
                    	<option value="${cosmeticClass.classId }">${cosmeticClass.className }</option>
                    </c:forEach>
                  </select>
                  <span class="input-tips">*必填</span>
                </div>
              </li>
              <li class="input-item">
                <div class="input-label">
                  产品属性：
                </div>
                <div class="input-contents">
                  <input class="sm-input product-property-input" type="text" name="propertyName" value="" readonly="readonly">
                  <input type="hidden" class="product-property-id-input" name="property" required="required">
                  <span class="input-tips">*必填</span>
                </div>
              </li>
              <li class="input-item">
                <div class="input-label">
                  产品功效：
                </div>
                <div class="input-contents">
                  <input class="sm-input product-effect-input" type="text" name="effectName" value="" readonly="readonly">
                  <input type="hidden" class="product-effect-id-input" name="effect" required="required">
                  <span class="input-tips">*必填</span>
                </div>
              </li>
              <li class="input-item">
                <div class="input-label">
                  适用肤质：
                </div>
                <div class="input-contents">
                  <select class="sm-input" name="skinTexture">
                    <!--  <option value="">中性</option>
                    <option value="">干性</option>
                    <option value="">油性</option>
                    <option value="">混合</option>
                    <option value="">敏感</option>-->
                    <c:forEach items="${skinTextures }" var="skinTexture">
                    	<option value="${skinTexture.code }">${skinTexture.name }</option>-->
                    </c:forEach>
                  </select>
                  <span class="input-tips">*必填</span>
                </div>
              </li>
              <li class="input-item">
                <div class="input-label">
                  封面图片上传：
                </div>
                <div class="input-contents">
                  <div><a class="select-cover" href="javascript:void(0)">封面选择</a></div>
                  <div class="pre-show-container cover-pre-show">
					
                  </div>
                </div>
              </li>
              <li class="input-item">
                <div class="input-label">
                  产品图片上传：
                </div>
                <div class="input-contents">
                  <a href="javascript:void(0);" class="product-img-input-wrapped">
                    	图片选择<input class="product-img-input" type="file" name="" value="" multiple>
                  </a>
                  <div class="pre-show-container product-pre-show">

                  </div>
                </div>
              </li>
              <li class="input-item vertical-top">
                <div class="input-label ">
                  产品介绍：
                </div>
                <div class="input-contents">
                  <textarea name="desc" rows="10" cols="101"></textarea>
                </div>
              </li>
            </form>
          </ul>
          <div class="add-product-op">
<!--             <a href="javascript:void(0);" class="add-confirm">添加</a> -->
			<button class="add-confirm" type="button">添加</button>
          </div>
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
<script type="text/javascript" src="static\plugins\BeAlert\BeAlert.js"></script>
<script type="text/javascript" src="static\js\product_add.js"></script>
</body>

</html>
