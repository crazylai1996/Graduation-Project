<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/base.jsp"%>
<c:forEach items="${pageResult.list }" var="commentReplyVO">
	<li class="reply-item">
		<div class="left">
			<div class="portrait-wrapped">
				<img src="${commentReplyVO.userInfoVO.portrait }">
				<p>${commentReplyVO.userInfoVO.userName }</p>
				<p>
					点评数( <font>999</font>)
				</p>
			</div>
		</div>
		<div class="right">
			<div class="triangle-pointer">
				<div class="background"></div>
			</div>
			<div class="reply-detail">
				<div class="reply-user">
					By<a>${commentReplyVO.userInfoVO.nickname }</a>
					&nbsp;&nbsp;${commentReplyVO.userInfoVO.skinTexture }
					&nbsp;&nbsp;${commentReplyVO.userInfoVO.age }岁
				</div>
				<p class="reply-content">${commentReplyVO.replyDetail }</p>
				<span class="reply-time"><span
					class="glyphicon glyphicon-time"></span>${commentReplyVO.replyTime }</span>
				<hr>
			</div>
		</div>
	</li>
</c:forEach>
