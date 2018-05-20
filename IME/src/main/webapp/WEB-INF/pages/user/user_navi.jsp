<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/base.jsp"%>
<base href="${basePath }">
<link rel="stylesheet" href="static\css\user_navi.css">
<span class="navi-label">用户中心</span>
<div class="navi-list">
	<div class="navi-item">
		<span class="title">用户信息<span
			class="to-down glyphicon glyphicon-chevron-down"></span></span>
		<ul class="navi-item-child">
			<li><a href="javascript:void(0);">基本信息</a></li>
			<li><a href="javascript:void(0);">账号设置</a></li>
		</ul>
	</div>
	<div class="navi-item">
		<span class="title">我的点评<span
			class="to-down glyphicon glyphicon-chevron-down"></span></span>
		<ul class="navi-item-child">
			<li><a href="javascript:void(0);">我的关注</a></li>
			<li><a href="javascript:void(0);">我发布的</a></li>
			<li><a href="javascript:void(0);">我的评论</a></li>
		</ul>
	</div>
	<div class="navi-item">
		<span class="title">消息中心<span
			class="to-down glyphicon glyphicon-chevron-down"></span></span>
		<ul class="navi-item-child">
			<li><a href="javascript:void(0);">系统消息</a></li>
			<li><a href="javascript:void(0);">互动消息</a></li>
			<li><a href="javascript:void(0);">我的私信</a></li>
		</ul>
	</div>
</div>