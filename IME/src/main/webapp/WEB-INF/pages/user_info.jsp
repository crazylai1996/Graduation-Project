<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="common/base.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <base href="${basePath }">
    <title>用户中心 - 爱美丽</title>
    <link rel="stylesheet" href="static\css\bootstrap.min.css">
    <link rel="stylesheet" href="static\plugins\Jcrop\jquery.Jcrop.min.css">
    <link rel="stylesheet" href="static\plugins\BeAlert\BeAlert.css"/>
    <link rel="stylesheet" href="static\css\user_info.css">
  </head>
  <body>
	<!-- 头部包含 -->
  	<jsp:include page="common/page_header.jsp"/>

    <div class="main-wrapped">
      <div class="main-container">
        <!-- 当前位置开始 -->
        <div class="current-pos">
          <a href="#">首页</a><span class="split-line"></span>
          <a href="#">用户中心</a><span class="split-line"></span>
          <sapn class="title">基本信息</span>
        </div>
        <!-- 当前位置结束 -->
        <!-- 主体内容开始 -->
        <div class="main-contents">
          <div class="main-left">
            <span class="navi-label">用户中心</span>
            <div class="navi-list">
              <div class="navi-item">
                <span class="title">用户信息<span  class="to-down glyphicon glyphicon-chevron-down"></span></span>
                <ul class="navi-item-child">
                  <li><a href="javascript:void(0);">基本信息</a></li>
                  <li><a href="javascript:void(0);">账号设置</a></li>
                </ul>
              </div>
              <div class="navi-item">
                <span class="title">我的点评<span  class="to-down glyphicon glyphicon-chevron-down"></span></span>
                <ul class="navi-item-child">
                  <li><a href="javascript:void(0);">我的关注</a></li>
                  <li><a href="javascript:void(0);">我发布的</a></li>
                  <li><a href="javascript:void(0);">我的评论</a></li>
                </ul>
              </div>
              <div class="navi-item">
                <span class="title">消息中心<span  class="to-down glyphicon glyphicon-chevron-down"></span></span>
                <ul class="navi-item-child">
                  <li><a href="javascript:void(0);">系统消息</a></li>
                  <li><a href="javascript:void(0);">互动消息</a></li>
                  <li><a href="javascript:void(0);">我的私信</a></li>
                </ul>
              </div>
            </div>
          </div>
          <div class="main-right">
            <div class="base-info">
              <h1 class="title">基本信息</h1>
              <hr class="line">
              <div class="info-detail">
              <form class="userinfo-form">
                <p class="info-item">
                  <span class="item-label">
                    用户名：
                  </span>
                  <span class="final-val item-val">
                    ${userInfoVO.userName }
                  </span>
                </p>
                <p class="info-item portrait-container">
                  <span class="item-label label-top">头像：</span>
                  <span class="portrait-wrapped">
                    <img class="portrait-img" src="${userInfoVO.portrait }">
                    <span class="change-protrait">
                      <a href="javascript:void(0);">更换头像</a>
                    </span>
                  </span>

                </p>
                <p class="info-item">
                  <span class="item-label">
                    昵称：
                  </span>
                  <input class="item-value nick-name item-val" type="text" name="nickname" value="${userInfoVO.nickname }">
                </p>
                <p class="info-item">
                  <span class="item-label">
                    性别：
                  </span>
                  <span class="item-val">
	                  <c:forEach items="${genderList }" var="gender">
	                  	<c:choose>
	                  		<c:when test="${gender.code == userInfoVO.gender}">
	                  			<input id="gender${gender.code }" type="radio" name="gender" value="${gender.code }" checked>
	                  		</c:when>
	                  		<c:otherwise>
	                  			<input id="gender${gender.code }" type="radio" name="gender" value="${gender.code }">
	                  		</c:otherwise>
	                  	</c:choose>
	                  	<label class="radio-label" for="gender${gender.code }">${gender.name }</label>
	                  </c:forEach>
                  </span>
                </p>
                <p class="info-item">
                  <span class="item-label">
                    年龄：
                  </span>
                  <span class="final-val item-val">${userInfoVO.age }</span>
                </p>
                <p class="info-item">
                  <span class="item-label">
                    肤质：
                  </span>
                  <span class="final-val item-val">${userInfoVO.skinTexture }</span>
                </p>
                <p class="info-item">
                  <span class="item-label">
                    地区：
                  </span>
                  <span class="item-val">
                    <select class="province-selector" name="">
                      <c:forEach items="${provinceList }" var="province">
                      	<c:choose>
                      		<c:when test="${province.areaId == userInfoVO.area.parentAreaId }">
                      			<option value="${province.areaId }" selected>${province.areaName }</option>
                      		</c:when>
                      		<c:otherwise>
                      			<option value="${province.areaId }">${province.areaName }</option>
                      		</c:otherwise>
                      	</c:choose>
                      </c:forEach>
                    </select>
                    <select class="city-selector" name="area.areaId">
                      <c:forEach items="${cityList }" var="city">
                      	<c:choose>
                      		<c:when test="${city.areaId == userInfoVO.area.areaId }">
                      			<option value="${city.areaId }" selected>${city.areaName }</option>
                      		</c:when>
                      		<c:otherwise>
                      			<option value="${city.areaId }">${city.areaName }</option>
                      		</c:otherwise>
                      	</c:choose>
                      </c:forEach>
                    </select>
                  </span>
                </p>
                <p class="info-item">
                  <span class="item-label label-top">个人简介</span>
                  <textarea class="item-val" name="introduction" rows="9" cols="87">${userInfoVO.introduction }</textarea>
                </p>
                </form>
              </div>
              <div class="info-ops">
                <a href="javascript:void(0);" class="update-confirm">更新</a>
              </div>
              
            </div>
          </div>
        </div>
        <!-- 主体内容结束 -->
      </div>
    </div>
    <!-- 包含尾部 -->
  	<jsp:include page="common/page_footer.jsp"/>

  <script>
  	var basePath = "${basePath}";
  </script>
  <script src="static\js\jquery.js"></script>
  <script src="static\plugins\Jcrop\jquery.Jcrop.min.js"></script>
  <script src="static\plugins\BeAlert\BeAlert.js"></script>
  <script src="static\js\user_info.js"></script>
  </body>
</html>
