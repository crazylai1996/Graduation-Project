<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="base.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=Edge">
  <base href="${basePath }">
  <title>爱美丽 - 化妆品导购与评价平台 - Header</title>
  <link rel="stylesheet" href="static\css\login_or_signup.css" />
  <link rel="stylesheet" href="static\css\common\page_header.css" />
  <link rel="stylesheet" href="static\plugins\layui\css\layui.css"/>
  <script type="text/javascript" src="static\plugins\layui\layui.all.js"></script>
</head>
<body>
	<!-- 顶部栏 -->
  <div class="bar-wrapped">
    <div class="top-bar">
      <div class="bar-left">
        在这里，一起发现你的专属美丽。
      </div>
      <div class="bar-right">
        <!-- 未登录显示 -->
        <c:if test="${empty sessionScope.userInfo }">
	        <ul class="no-login-navi">
	          <li><a class="gologin-btn" href="javascript:void(0);">[登录]</a></li>
	          <li><a class="gosignup-btn" href="javascript:void(0);">[注册]</a></li>
	        </ul>
        </c:if>
        <!-- 登录后显示 -->
        <c:if test="${!empty sessionScope.userInfo }">
	        <ul class="user-navi">
	          <li class="message-btn">
	            <a href="javascript:void(0);">消息中心</a>
	            <div class="message-box more">
	              <div class="message-type">
	                <a class="first current" href="javascript:void(0);">系统</a>
	                <a class="middle" href="javascript:void(0);">互动</a>
	                <a class="last" href="javascript:void(0);">私信</a>
	              </div>
	              <ul class="message-items">
	                <li><p>新MV！王菲刚刚发布了新MV《将爱》，马上来看！</p></li>
	              </ul>
	              <a class="read-more-btn" href="javascript:void(0);">查看更多</a>
	            </div>
	          </li>
	          <li class="userinfo-btn">
	            <a href="javascript:void(0);">${userInfo.userName }</a>
	            <ul class="user-tools-box more">
	              <li><a href="${basePath }user/info.html">个人中心</a></li>
	
	              <li><a href="javascript:void(0);">我的评价</a></li>
	              <li><a href="javascript:void(0);">我的文章</a></li>
	              <li><a href="javascript:void(0);">我的消息</a></li>
	
	              <li><a href="user/logout">退出登录</a></li>
	            </ul>
	          </li>
	        </ul>
        </c:if>
      </div>
    </div>
  </div>

  <!-- logo位置 -->
  <div class="logo-wrapped">
    <div class="container">
      <img src="static\img\logo.png" alt="爱美丽">
    </div>
  </div>

  <!-- 功能导航栏 -->
  <ul class="func-navi">
    <li class="navi-lv1 left navi-current"><a href="javascript:void(0);">首页</a></li>
    <li class="navi-split-line left">|</li>
    <li class="navi-lv1 left"><a href="javascript:void(0);">化妆品点评</a></li>
    <li class="navi-split-line left">|</li>
    <li class="navi-lv1 left"><a href="javascript:void(0);">使用心得</a></li>
    <li class="navi-split-line left">|</li>
    <li class="navi-lv1 left"><a href="javascript:void(0);">排行榜</a></li>
    <li class="navi-split-line left">|</li>
    <li class="navi-lv1 left"><a href="javascript:void(0);">Q&A</a></li>

    <li class="navi-lv1 right navi-has-more more-beauty">
      <a href="javascript:void(0);">更多</a>
      <ul class="more">
        <li><a href="javascript:void(0);">天猫美妆</a></li>
        <li><a href="javascript:void(0);">京东美妆馆</a></li>
      </ul>
    </li>
  </ul>

  <!-- 搜索框 -->
  <div class="search-container">
    <span class="want-search left">我要找：</span>
    <span class="search-box left">
      <a class="search-type" href="javascript:void(0);">点评</a>
      <input type="text" class="keyword search-keyword" name="keyword" 
      	autocomplete="off" placeholder="请输入点评标题、内容等" value="${param.keyword }">
      <ul class="s-type-slector">
        <li><a href="javascript:void(0);">商品</a></li>
        <li><a href="javascript:void(0);">点评</a></li>
<!--         <li><a href="javascript:void(0);">文章</a></li> -->
      </ul>
    </span>
    <a class="search-btn left" href="javascript:void(0);">搜&nbsp;索</a>
    <span class="hot-search right">
      <span>热门搜索：</span>
      <span>
        <a href="javascript:void(0);">焕白</a>
        <a href="javascript:void(0);">紧致毛孔</a>
        <a href="javascript:void(0);">细致</a>
        <a href="javascript:void(0);">防晒</a>
        <a href="javascript:void(0);">BB霜</a>
        <a href="javascript:void(0);">密集</a>
      </span>
    </span>
  </div>
  
  <script>
  	var basePath = "${basePath}";
  </script>
  <script src="static\js\jquery.js"></script>
  <script src="static\js\common\page_header.js"></script>
</body>
</html>