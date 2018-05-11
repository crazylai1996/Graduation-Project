<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="common/base.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=Edge">
  <base href="${basePath }">
  <title>爱美丽 - 化妆品导购与评价平台</title>
  <link rel="stylesheet" href="static\plugins\layui\css\layui.css"/>
  <script type="text/javascript" src="static\plugins\layui\layui.js"></script>
  <link rel="stylesheet" href="static\css\index.css" />
</head>

<body>
  <!-- 头部包含 -->
  <jsp:include page="common/page_header.jsp"/>

  <!-- 信息索引（商品，用户） -->
  <div class="index-wrapped">
    <!-- 分类索引 -->
    <div class="category-wrapped">
      <div class="category-title">全部分类</div>
      <ul class="category-index">
        <li>
          <a href="javascript:void(0);">面部护肤</a>
          <ul class="sub-category">
            <li><a href="javascript:void(0);">脸部保养</a></li>
            <li><a href="javascript:void(0);">洁面产品</a></li>
            <li><a href="javascript:void(0);">脸部保养</a></li>
            <li><a href="javascript:void(0);">洁面产品</a></li>
            <li><a href="javascript:void(0);">脸部保养</a></li>
          </ul>
        </li>
        <li><a href="javascript:void(0);">魅力彩妆</a></li>
        <li><a href="javascript:void(0);">身体保养</a></li>
        <li><a href="javascript:void(0);">美发护理</a></li>
        <li><a href="javascript:void(0);">香水香氛</a></li>
        <li><a href="javascript:void(0);">孕妈/婴童</a></li>
        <li><a href="javascript:void(0);">工具/隐形眼镜</a></li>
        <li><a href="javascript:void(0);">男士用品</a></li>
      </ul>
    </div>
    <!-- 标签索引 -->
    <div class="label-index">
      <div class="label-title">
        热门标签
      </div>
      <table class="label-items">
        <tbody>
          <tr>
            <td class="title">属性：</td>
            <td class="contents">
              <a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a>
              <a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a>
              <a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a>
              <a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a><a href="#">散粉/蜜粉</a>
            </td>
          </tr>
          <tr>
            <td class="title">功效：</td>
            <td class="contents">
              <a href="#">抗氧化</a><a href="#">抗氧化</a><a href="#">抗氧化</a>
              <a href="#">抗氧化</a><a href="#">抗氧化</a><a href="#">抗氧化</a>
              <a href="#">抗氧化</a><a href="#">抗氧化</a><a href="#">抗氧化</a>
              <a href="#">抗氧化</a><a href="#">抗氧化</a><a href="#">抗氧化</a>
              <a href="#">抗氧化</a><a href="#">抗氧化</a><a href="#">抗氧化</a>
            </td>
          </tr>
          <tr>
            <td class="title">品牌：</td>
            <td class="contents">
              <a href="#">SulWhaSoo雪花秀</a><a href="#">SulWhaSoo雪花秀</a><a href="#">SulWhaSoo雪花秀</a>
              <a href="#">SulWhaSoo雪花秀</a><a href="#">SulWhaSoo雪花秀</a><a href="#">SulWhaSoo雪花秀</a>
              <a href="#">SulWhaSoo雪花秀</a><a href="#">SulWhaSoo雪花秀</a><a href="#">SulWhaSoo雪花秀</a>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <!-- 用户信息、用户推荐 -->
    <div class="user-center">
      <!-- 用户头像 -->
      <div class="portrait-wrapped">
        <a href="javascript:void(0);">
          <img src="static\img\portrait_test.jpg" alt="点击进入个人中心">
        </a>
      </div>
      <!-- 用户名 -->
      <div class="username-wrapped">
        Hi!&nbsp;<strong>crazylai1996</strong>
      </div>
      <!-- 用户积分 -->
      <div class="user-integration">
        <span>积分:<a href="#">7845</a></span>
        <span>会员类型:<a href="#">超级会员</a></span>
      </div>
      <!-- 用户操作 -->
      <div class="user-ops">
        <a href="javascript:void(0);">&#10010;添加评价</a>
        <a href="javascript:void(0);"><i class="layui-icon layui-icon-star"></i> 我的关注</a>
      </div>
      <!-- 公告 -->
      <div class="notice-wrapped">
        <div class="notice-title">
          公告消息
        </div>
        <ul class="notice-list">
          <li><a href="#">第二支半价稚优泉口红正品持久保湿不易脱色防水南瓜脏橘色姨妈</a></li>
          <li><a href="#">第二支半价稚优泉口红正品持久保湿不易脱色防水南瓜脏橘色姨妈</a></li>
          <li><a href="#">第二支半价稚优泉口红正品持久保湿不易脱色防水南瓜脏橘色姨妈</a></li>
          <li><a href="#">第二支半价稚优泉口红正品持久保湿不易脱色防水南瓜脏橘色姨妈</a></li>
        </ul>
      </div>
    </div>
  </div>

  <!-- 内容 -->
  <div class="main-contents">
    <div class="main-left">
      <!-- 猜你喜欢-->
      <div class="guess-you-need">
        <div class="gyn-title">
          <a class="left" href="#">猜你喜欢</a>
          <a class="right" href="#">换一批</a>
        </div>
        <ul class="gyn-contents">
          <li><a href="#">
            <img src="static\img\commodity_test.jpg">
            <span class="commodity-title">
              Green Skin格润丝 花青素明眸眼霜Green Skin格润丝 花青素明眸眼霜Green Skin格润丝 花青素明眸眼霜
            </span></a>
          </li>
          <li><a href="#">
            <img src="static\img\commodity_test.jpg">
            <span class="commodity-title">
              Green Skin格润丝 花青素明眸眼霜Green Skin格润丝 花青素明眸眼霜Green Skin格润丝 花青素明眸眼霜
            </span></a>
          </li>
          <li><a href="#">
            <img src="static\img\commodity_test.jpg">
            <span class="commodity-title">
              Green Skin格润丝 花青素明眸眼霜Green Skin格润丝 花青素明眸眼霜Green Skin格润丝 花青素明眸眼霜
            </span></a>
          </li>
          <li><a href="#">
            <img src="static\img\commodity_test.jpg">
            <span class="commodity-title">
              Green Skin格润丝 花青素明眸眼霜Green Skin格润丝 花青素明眸眼霜Green Skin格润丝 花青素明眸眼霜
            </span></a>
          </li>
          <li><a href="#">
            <img src="static\img\commodity_test.jpg">
            <span class="commodity-title">
              Green Skin格润丝 花青素明眸眼霜Green Skin格润丝 花青素明眸眼霜Green Skin格润丝 花青素明眸眼霜
            </span></a>
          </li>
        </ul>
      </div>
      <!-- 最新点评 -->
      <div class="newest-comments">
        <ul class="nc-title">
          <li class="current">最新点评</li>
          <li>关注动态</li>
        </ul>
        <ul class="nc-list">
          <li>
            <a class="item-title" href="www.baidu.com">每日白菜精选：无痕免钉钩、单肩迷你小包、玻璃储物罐等</a>
            <div class="item-contents">
              <img src="static\img\commodity_test.jpg" alt="商品图片">
              <div class="contents-right">
                <div class="contents-label">
                  <span class="label-user">推荐人：<a href="javascript:void(0);">白菜君</a></span>
                  <span class="label-rank">性价评分：<a class="rank-point" href="javascript:void(0);">4</a></span>
                </div>
                <div class="contents-detail">
                  采用纯天然原材料，
                  偶然一次路过百度百丽专卖店时被她的专修吸引走进去，
                  发现了这个。买回家试用一段时间后，可以感受到那种天然的力量，真的很好用。
                </div>
                <div class="item-btns">
                  <span class="time-label">2018-02-16 23:36</span>
                  <a class="view-all" href="#">查看全文</a>
                </div>
              </div>
            </div>
          </li>
            <li>
              <a class="item-title" href="www.baidu.com">每日白菜精选：无痕免钉钩、单肩迷你小包、玻璃储物罐等</a>
              <div class="item-contents">
                <img src="static\img\commodity_test.jpg" alt="商品图片">
                <div class="contents-right">
                  <div class="contents-label">
                    <span class="label-user">推荐人：<a href="javascript:void(0);">白菜君</a></span>
                    <span class="label-rank">性价评分：<a href="javascript:void(0);">4</a></span>
                  </div>
                  <div class="contents-detail">
                    采用纯天然原材料，
                    偶然一次路过百度百丽专卖店时被她的专修吸引走进去，
                    发现了这个。买回家试用一段时间后，可以感受到那种天然的力量，真的很好用。
                  </div>
                  <div class="item-btns">
                    <span class="time-label">2018-02-16 23:36</span>
                    <a class="view-all" href="#">查看全文</a>
                  </div>
                </div>
              </div>
            </li>
        </ul>
      </div>
    </div>
    <div class="main-right">
      <!-- 最多点击 -->
      <div class="most-click">
        <div class="mc-head">
          <span class="mc-head-title">最多点击</span>
          <ul class="mc-head-time">
            <li class="current">7天</li>
            <li>30天</li>
          </ul>
        </div>
        <ul class="mc-body">
          <li class="current">
            <a class="brief-title" href="#">1 欧植萃 天然玫瑰保湿护手霜欧植萃 天然玫瑰保湿护手霜</a>
            <img src="static\img\commodity_test.jpg" alt="化妆品图片">
            <div class="mc-body-right">
              <a class="mc-body-title" href="#">欧植萃 天然玫瑰保湿护手霜欧植萃 天然玫瑰保玫瑰保湿玫瑰保湿湿玫瑰保湿护手霜</a>
              <div class="mc-body-ext">
                <span class="love-sum"><i class="layui-icon layui-icon-reply-fill"></i>49</span>
                <a href="#" class="comment-sum"><i class="layui-icon layui-icon-star-fill"></i>11</a>
              </div>
            </div>
          </li>
          <li>
            <a class="brief-title" href="#">2 欧植萃 天然玫瑰保湿护手霜欧植萃</a>
            <img src="static\img\commodity_test.jpg" alt="化妆品图片">
            <div class="mc-body-right">
              <a class="mc-body-title" href="#">欧植萃 天然玫瑰保湿护手霜欧植萃</a>
              <div class="mc-body-ext">
                <span class="love-sum">49</span><a href="#" class="comment-sum">11</a>
              </div>
            </div>
          </li>
          <li>
            <a class="brief-title" href="#">3 欧植萃 天然玫瑰保湿护手霜欧植萃 </a>
            <img src="static\img\commodity_test.jpg" alt="化妆品图片">
            <div class="mc-body-right">
              <a class="mc-body-title" href="#">欧植萃 天然玫瑰保湿护手霜欧植萃 </a>
              <div class="mc-body-ext">
                <span class="love-sum">49</span><a href="#" class="comment-sum">11</a>
              </div>
            </div>
          </li>
          <li>
            <a class="brief-title" href="#">4 欧植萃 天然玫瑰保湿护手霜欧植萃 </a>
            <img src="static\img\commodity_test.jpg" alt="化妆品图片">
            <div class="mc-body-right">
              <a class="mc-body-title" href="#">欧植萃 天然玫瑰保湿护手霜欧植萃 </a>
              <div class="mc-body-ext">
                <span class="love-sum">49</span><a href="#" class="comment-sum">11</a>
              </div>
            </div>
          </li>
          <li>
            <a class="brief-title" href="#">5 欧植萃 天然玫瑰保湿护手霜欧植萃 </a>
            <img src="static\img\commodity_test.jpg" alt="化妆品图片">
            <div class="mc-body-right">
              <a class="mc-body-title" href="#">欧植萃 天然玫瑰保湿护手霜欧植萃 </a>
              <div class="mc-body-ext">
                <span class="love-sum">49</span><a href="#" class="comment-sum">11</a>
              </div>
            </div>
          </li>
        </ul>
      </div>
      <!-- 性价精选 -->
      <div class="">
      <!-- TODO -->
      </div>
    </div>
  </div>
  <!-- 包含尾部 -->
  <jsp:include page="common/page_footer.jsp"/>

  <script src="static\js\jquery.js"></script>
  <script src="static\js\index.js"></script>
</body>

</html>
