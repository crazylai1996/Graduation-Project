<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/base.jsp"%>
<c:forEach items="${pageResult.list}"  var="commentInfoVO">
	<li class="comment-item">
		<div class="left">
			<div class="portrait-wrapped">
				<img src="${commentInfoVO.userInfo.portrait }">
				<p>${commentInfoVO.userInfo.nickname }</p>
				<p>
					点评数( <font>999</font>)
				</p>
				<p>
					<a href="javascript:void(0);" data-user-id="${commentInfoVO.userInfo.userId }" class="love-her-btn follow-her-btn">关注她</a>
				</p>
			</div>
		</div>
		<div class="right">
			<div class="triangle-pointer">
				<div class="background"></div>
			</div>
			<div class="comment-detail">
				<a href="#" class="comment-title">${commentInfoVO.articleTitle }</a> <input value="4"
					type="number" class="rating" min=0 max=5 step=0.5 data-size="xxs"
					data-only-show="true" data-symbol="&#xe005;">
				<div class="comment-user">
					By<a>${commentInfoVO.userInfo.nickname }</a>
					&nbsp;&nbsp;${commentInfoVO.userInfo.skinTexture }
					&nbsp;&nbsp;${commentInfoVO.userInfo.age }
					&nbsp;&nbsp;购买方式：${commentInfoVO.buyWayName }
				</div>
				<p class="comment-comment">${commentInfoVO.contentText }…&nbsp;&nbsp;<a target="_blank" class="show-details" href="/IME/comment/info/${commentInfoVO.commentId }.html">
					阅读全文&gt;&gt;</a></p>
				<span class="comment-time">${commentInfoVO.sendTime }</span>
				<hr>
				<div class="comment-ops">
					(0)<a href="javascript:void(0);">回复</a>(0)<a>有用</a>
				</div>
			</div>
		</div>
	</li>
	<hr>
</c:forEach>