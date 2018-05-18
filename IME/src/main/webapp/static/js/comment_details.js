var laydate = layui.laydate, layer = layui.layer;
$(document).ready(function() {
  /**
   * [加载评分插件]
   */
  var $input = $('input.rating'), count = Object.keys($input).length;
  if (count > 0) {
      $input.rating();
  }
  
  //加载更多回复
  $(".load-more-btn").click(function(){
	  var commentId = $(".comment-id").val();
	  var pageNum = $(".comment-reply-container").data("pageNum") + 1;
	  var pages = $(".comment-reply-container").data("pages");
	  $(".comment-reply-container").data("pageNum",pageNum);
	  var getUrl = basePath + "commentReply/loadMoreReply.do" + "?pageNum="+pageNum+"&commentId="+commentId;
	  var index = layer.load(0, {shade: false});
	  $.get(getUrl,function(result){
		  $(".comment-reply-container").append(result);
		  layer.close(index);
		  if(pageNum == pages){
			  $(".load-more-wrapped").html('<span class="no-more-load">没有更多了</span>');
		  }
	  });
  });
 
  
  //回复添加按钮
  $(".add-btn").click(function(){
	  var replyDetail = $(".reply-detail-textarea").val();
	  if(replyDetail == ""){
		  layer.msg("回复内容不能留空");
		  return ;
	  }
	  if(replyDetail.length < 10 || replyDetail.length > 150){
		  layer.msg("请输入10-150个字");
		  return ;
	  }
	  
	  var postUrl = basePath + "commentReply/addNewCommentReply.do";
	  $.ajax({
		  url: postUrl,
		  data: $(".reply-form").serialize(),
		  type: "POST",
		  dataType: "json",
		  success: function(result){
			  console.log(JSON.stringify(result));
			  if(result.success){
					alert("回复成功",
		  					"",
		  					function(){
		  						//确认按钮回调
								$(".comment-reply-container").prepend(renderCommentReply(result.data));
		  					},
		  					{type:'success',confirmButtonText: '好的'});
				}else{
					var title = "回复失败";
		  			var tips = "";
		  			var callback = function(){

		  			};
		  			if(result.code == 207){
		  				tips = "登录失效，请重新登录";
		  				callback = function(){
		  					var redirectUrl = window.location.href;
		  					$.get(basePath+"user/login",{target:"login",redirectUrl:redirectUrl},function(data,status){
		  						$(document.body).append("<div class='login-popup-container'>"+data+"</div>");
		  						$("html").css("overflow-y","hidden");
		  					});
		  				}
		  			}else{
		  				tips = "未知错误";
		  			}
		  			alert(title,
							tips,
							callback,
							{type:'error',confirmButtonText: '好的'});
				}
		  }
	  });
  });
  
  function renderCommentReply(commentReplyVO){
	  return '<li class="reply-item">\
		<div class="left">\
		<div class="portrait-wrapped">\
			<img src="'+commentReplyVO.userInfoVO.portrait + '">\
			<p>'+commentReplyVO.userInfoVO.userName + '</p>\
			<p>\
				点评数( <font>999</font>)\
			</p>\
		</div>\
	</div>\
	<div class="right">\
		<div class="triangle-pointer">\
			<div class="background"></div>\
		</div>\
		<div class="reply-detail">\
			<div class="reply-user">\
				By<a>'+commentReplyVO.userInfoVO.nickname +'</a>\
				&nbsp;&nbsp;'+commentReplyVO.userInfoVO.skinTexture +'\
				&nbsp;&nbsp;'+commentReplyVO.userInfoVO.age+'岁\
			</div>\
			<p class="reply-content">'+commentReplyVO.replyDetail +'</p>\
			<span class="reply-time"><span\
				class="glyphicon glyphicon-time"></span>'+commentReplyVO.replyTime+'</span>\
			<hr>\
		</div>\
	</div>\
</li>';
  }
});
