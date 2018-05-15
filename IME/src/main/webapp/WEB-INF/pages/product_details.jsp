<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="common/base.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=Edge">
  <base href="${basePath }">
  <title>化妆品详情 - 爱美丽</title>
  <link rel="stylesheet" href="static\plugins\star-rating\star-rating.css">
  <link rel="stylesheet" href="static\css\product_details.css" />
  <link rel="stylesheet" href="static\css\bootstrap.min.css">
  <link rel="stylesheet" href="static\plugins\kindeditor\themes\default\default.css">
</head>

<body>
  <!-- 头部包含 -->
  <jsp:include page="common/page_header.jsp"/>

  <div class="main-wrapped">
    <div class="main-container">
      <!-- 当前位置 -->
      <div class="current-pos">
        <a href="#">首页</a><span class="split-line"></span>
        <a href="#">Green Skin格润丝</a><span class="split-line"></span>
        <sapn class="title">Green Skin格润丝 花青素明眸眼霜</span>
      </div>
      <hr>
      <!-- 产品信息 -->
      <div class="product-container">
        <!-- 产品图片展示 -->
        <div class="product-images-container">
          <div class="image-display">
            <div class="current-img"><span><img src="static\img\commodity_test.jpg"/></span></div>
            <div><span><img src="static\img\commodity_test.jpg"/></span></div>
            <div><span><img src="static\img\commodity_test.jpg"/></span></div>
          </div>
          <div class="thumbnail-container">
            <span class="previous-btn"></span>
            <div class="thumbnail-list">
              <a class="current-li"><img src="static\img\commodity_test.jpg"/></a>
              <a><img src="static\img\commodity_test.jpg"/></a>
              <a><img src="static\img\commodity_test.jpg"/></a>
            </div>
            <span class="next-btn"></span>
          </div>
        </div>
        <!-- 产品基本信息 -->
        <div class="product-base-info">
          <h1><a href="#">法国希思黎植物养护精华卷翘睫毛膏</a></h1>
          <p>参考价格：<span class="price">198.00元</span></p>
          <p>产品规格：<span class="spec">常规</span></p>
          <p>产品种类：<a href="#">睫毛膏</a><span></span>产品功效：<a href="#">滋润</a></p>
          <p>适用肤质：<a href="#">所有肤质</a></p>
          <p>购买来源：<a href="#">天猫</a></p>
          <div class="product-details">
            <span>产品信息：</span>
            <p>#1经典黑 #2深邃棕 #3深海蓝 融合了睫毛养护液的保养成分，深入睫毛根部强健睫毛，让睫毛从根部
              <a href="#" class="show-more">展开&gt;&gt;</a>
            </p>
          </div>
        </div>
        <!-- 产品评级分布 -->
        <div class="product-rating-wrapped">
          <div class="product-rating">
            <!-- 评分统计 -->
            <div class="rating-brief">
              <div class="rating-title">
                综合性价评分
              </div>
              <div class="rating-count">
                <div class="left">
                  <strong>9.0</strong>
                </div>
                <div class="right">
                  <span class="right-up">
                    <input value="4.5" type="number"
                     class="rating" min=0 max=5
                     step=0.5 data-size="xxs"
                    data-only-show="true" data-symbol="&#xe005;">
                  </span>
                  <span class="right-down"><a href="#">283471人评价</a></span>
                </div>
              </div>
            </div>
            <!-- 评分分布 -->
            <div class="rating-more">
              <ul class="rating-details">
                <!-- 一个 -->
                <li class="rating-on-weight">
                  <span class="rating-wrapped">
                    <input value="5" type="number"
                   class="rating" min=0 max=5
                   step=0.5 data-size="xxxs"
                  data-only-show="true" data-symbol="&#xe005;">
                  </span>
                  <span class="rating-power-wrapped">
                    <span class="rating-power" data-power="1.0"></span>
                  </span>
                  <span class="rating-per">
                    100%
                  </span>
                </li>
                <!-- 一个结束  -->
                <!-- 一个 -->
                <li class="rating-on-weight">
                  <span class="rating-wrapped">
                    <input value="4" type="number"
                   class="rating" min=0 max=5
                   step=0.5 data-size="xxxs"
                  data-only-show="true" data-symbol="&#xe005;">
                  </span>
                  <span class="rating-power-wrapped">
                    <span class="rating-power" data-power="1.0"></span>
                  </span>
                  <span class="rating-per">
                    100%
                  </span>
                </li>
                <!-- 一个结束  -->
                <!-- 一个 -->
                <li class="rating-on-weight">
                  <span class="rating-wrapped">
                    <input value="3" type="number"
                   class="rating" min=0 max=5
                   step=0.5 data-size="xxxs"
                  data-only-show="true" data-symbol="&#xe005;">
                  </span>
                  <span class="rating-power-wrapped">
                    <span class="rating-power" data-power="1.0"></span>
                  </span>
                  <span class="rating-per">
                    100%
                  </span>
                </li>
                <!-- 一个结束  -->
                <!-- 一个 -->
                <li class="rating-on-weight">
                  <span class="rating-wrapped">
                    <input value="2" type="number"
                   class="rating" min=0 max=5
                   step=0.5 data-size="xxxs"
                  data-only-show="true" data-symbol="&#xe005;">
                  </span>
                  <span class="rating-power-wrapped">
                    <span class="rating-power" data-power="1.0"></span>
                  </span>
                  <span class="rating-per">
                    100%
                  </span>
                </li>
                <!-- 一个结束  -->
                <!-- 一个 -->
                <li class="rating-on-weight">
                  <span class="rating-wrapped">
                    <input value="1" type="number"
                   class="rating" min=0 max=5
                   step=0.5 data-size="xxxs"
                  data-only-show="true" data-symbol="&#xe005;">
                  </span>
                  <span class="rating-power-wrapped">
                    <span class="rating-power" data-power="1.0"></span>
                  </span>
                  <span class="rating-per">
                    100%
                  </span>
                </li>
                <!-- 一个结束  -->
              </ul>
              <hr>
              <div class="product-ops">
                <div class="btn-wrapped">
                  <a href="#" class="reply-btn">发表使用心得</a>
                </div>
                <div class="btn-wrapped">
                  <a href="#" class="love-btn">
                    <font>&#10010;关注&nbsp;&nbsp;</font>|&nbsp;&nbsp;0人</a>
                </div>
              </div>
            </div>
          </div>
        </div>

      </div>
    </div>
    <!-- 产品使用心得分析 -->
    <div class="comment-container">
      <div class="comment-containter-left">
        <!-- 使用心得分析 -->
        <div class="comment-analysis">
          <div class="analysis-title">
            <a class="name" href="#">Green Skin格润丝 花青素明眸眼霜</a>
            <span class="title">使用心得分析</span>
          </div>
          <div class="analysis-contents">
            <div class="analysis-details">
              <div class="analysis-type">
                <span class="type-name">评分分布</span>
                <ul class="type-details">
                  <li class="type-on-weight">
                    <span class="type-item">
                        5星
                      </span>
                    <span class="item-power-wrapped">
                        <span class="item-power" data-power="1.0"></span>
                    </span>
                    <span class="item-per">
                        100%
                      </span>
                  </li>
                  <li class="type-on-weight">
                    <span class="type-item">
                        4星
                      </span>
                    <span class="item-power-wrapped">
                        <span class="item-power" data-power="1.0"></span>
                    </span>
                    <span class="item-per">
                        100%
                      </span>
                  </li>
                  <li class="type-on-weight">
                    <span class="type-item">
                        3星
                      </span>
                    <span class="item-power-wrapped">
                        <span class="item-power" data-power="1.0"></span>
                    </span>
                    <span class="item-per">
                        100%
                      </span>
                  </li>
                  <li class="type-on-weight">
                    <span class="type-item">
                        2星
                      </span>
                    <span class="item-power-wrapped">
                        <span class="item-power" data-power="1.0"></span>
                    </span>
                    <span class="item-per">
                        100%
                      </span>
                  </li>
                  <li class="type-on-weight">
                    <span class="type-item">
                        1星
                      </span>
                    <span class="item-power-wrapped">
                        <span class="item-power" data-power="1.0"></span>
                    </span>
                    <span class="item-per">
                        100%
                      </span>
                  </li>
                </ul>
              </div>
              <div class="analysis-type">
                <span class="type-name">肤质分布</span>
                <ul class="type-details">
                  <li class="type-on-weight">
                    <span class="type-item">
                                      中性
                                    </span>
                    <span class="item-power-wrapped">
                                      <span class="item-power" data-power="1.0"></span>
                    </span>
                    <span class="item-per">
                                      100%
                                    </span>
                  </li>
                  <li class="type-on-weight">
                    <span class="type-item">
                                      干性
                                    </span>
                    <span class="item-power-wrapped">
                                      <span class="item-power" data-power="1.0"></span>
                    </span>
                    <span class="item-per">
                                      100%
                                    </span>
                  </li>
                  <li class="type-on-weight">
                    <span class="type-item">
                                      油性
                                    </span>
                    <span class="item-power-wrapped">
                                      <span class="item-power" data-power="1.0"></span>
                    </span>
                    <span class="item-per">
                                      100%
                                    </span>
                  </li>
                  <li class="type-on-weight">
                    <span class="type-item">
                                      混合
                                    </span>
                    <span class="item-power-wrapped">
                                      <span class="item-power" data-power="1.0"></span>
                    </span>
                    <span class="item-per">
                                      100%
                                    </span>
                  </li>
                  <li class="type-on-weight">
                    <span class="type-item">
                                      敏感
                                    </span>
                    <span class="item-power-wrapped">
                                      <span class="item-power" data-power="1.0"></span>
                    </span>
                    <span class="item-per">
                                      100%
                                    </span>
                  </li>
                </ul>
              </div>
              <div class="analysis-type">
                <span class="type-name">年龄分布</span>
                <ul class="type-details">
                  <li class="type-on-weight">
                    <span class="type-item">
                                                    &#8804;19
                                                  </span>
                    <span class="item-power-wrapped">
                                                    <span class="item-power" data-power="1.0"></span>
                    </span>
                    <span class="item-per">
                                                    100%
                                                  </span>
                  </li>
                  <li class="type-on-weight">
                    <span class="type-item">
                                                    20-24
                                                  </span>
                    <span class="item-power-wrapped">
                                                    <span class="item-power" data-power="1.0"></span>
                    </span>
                    <span class="item-per">
                                                    100%
                                                  </span>
                  </li>
                  <li class="type-on-weight">
                    <span class="type-item">
                                                    25-29
                                                  </span>
                    <span class="item-power-wrapped">
                                                    <span class="item-power" data-power="1.0"></span>
                    </span>
                    <span class="item-per">
                                                    100%
                                                  </span>
                  </li>
                  <li class="type-on-weight">
                    <span class="type-item">
                                                    30-34
                                                  </span>
                    <span class="item-power-wrapped">
                                                    <span class="item-power" data-power="1.0"></span>
                    </span>
                    <span class="item-per">
                                                    100%
                                                  </span>
                  </li>
                  <li class="type-on-weight">
                    <span class="type-item">
                                                     &#8805;35
                                                  </span>
                    <span class="item-power-wrapped">
                                                    <span class="item-power" data-power="1.0"></span>
                    </span>
                    <span class="item-per">
                                                    100%
                                                  </span>
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </div>
        <!-- 使用心得块 -->
        <div class="comment-contents">
          <!-- 使用心得标题 -->
          <div class="comment-list-title">
            <a class="name" href="#">Green Skin格润丝 花青素明眸眼霜</a>
            <span class="title">使用心得</span>
          </div>
          <!-- 使用心得筛选条件 -->
          <div class="comment-search-wrapped">
            <span class="search-label">分类浏览</span>
            <span class="search-params">
              <form class="comment-search-form">
                <select class="rating" name="">
                  <option value='' disabled selected style='display:none;'>请指定评分</option>
                </select>
                <select class="skin-type" name="">
                  <option value='' disabled selected style='display:none;'>请指定肤质</option>
                </select>
                <select class="age" name="">
                  <option value='' disabled selected style='display:none;'>请指定年龄</option>
                </select>
              </form>
            </span>
            <a href="#" class="search-btn">搜索</a>
          </div>
          <!-- 使用心得列表 -->
          <ul class="comment-list">
            <li class="comment-item">
              <div class="left">
                <div class="portrait-wrapped">
                  <img src="static\img\portrait_test.jpg">
                  <p>crazylai1996</p>
                  <p>点评数(
                    <font>999</font>)</p>
                  <p><a href="#" class="love-her-btn">关注她</a></p>
                </div>
              </div>
              <div class="right">
                <div class="triangle-pointer">
                  <div class="background">
                  </div>
                </div>
                <div class="comment-detail">
                  <a href="#" class="comment-title">精华液有点不够敷的赶脚</a>
                  <input value="4" type="number" class="rating" min=0 max=5 step=0.5 data-size="xxs" data-only-show="true" data-symbol="&#xe005;">
                  <div class="comment-user">
                    By<a>智慧果</a>&nbsp;&nbsp;合性皮肤&nbsp;&nbsp;31-39岁&nbsp;&nbsp;购买方式：网上购买
                  </div>
                  <p class="comment-comment">本着支持国货的心，网购了一套鸟家的面膜。 这款膜不是蚕丝膜，感觉像是纸膜，所以亲肤性方面自然要比蚕丝膜逊色不少。 精华液有淡淡的玫瑰香，但是量太少了，冬天的话，感觉颇有些不够敷。美白的话，也没有明显感觉。
                  </p>
                  <span class="comment-time">2018-03-13</span>
                  <hr>
                  <div class="comment-ops">
                    (0)<a href="#">回复</a>(0)<a>有用</a>
                  </div>
                </div>
              </div>
            </li>
            <hr>
            <li class="comment-item">
              <div class="left">
                <div class="portrait-wrapped">
                  <img src="static\img\portrait_test.jpg">
                  <p>crazylai1996</p>
                  <p>点评数(
                    <font>999</font>)</p>
                  <p><a href="#" class="love-her-btn">关注她</a></p>
                </div>
              </div>
              <div class="right">
                <div class="triangle-pointer">
                  <div class="background">
                  </div>
                </div>
                <div class="comment-detail">
                  <a href="#" class="comment-title">精华液有点不够敷的赶脚</a>
                  <input value="4" type="number" class="rating" min=0 max=5 step=0.5 data-size="xxs" data-only-show="true" data-symbol="&#xe005;">
                  <div class="comment-user">
                    By<a>智慧果</a>&nbsp;&nbsp;合性皮肤&nbsp;&nbsp;31-39岁&nbsp;&nbsp;购买方式：网上购买
                  </div>
                  <p class="comment-comment">本着支持国货的心，网购了一套鸟家的面膜。 这款膜不是蚕丝膜，感觉像是纸膜，所以亲肤性方面自然要比蚕丝膜逊色不少。 精华液有淡淡的玫瑰香，但是量太少了，冬天的话，感觉颇有些不够敷。美白的话，也没有明显感觉。
                  </p>
                  <span class="comment-time"><span class="glyphicon glyphicon-time"></span>2018-03-13</span>
                  <hr>
                  <div class="comment-ops">
                    (0)<a href="#">回复</a>(0)<a>有用</a>
                  </div>
                </div>
              </div>
            </li>
          </ul>
        </div>
        <!-- 评论框 -->
        <div class="comment-add-container">
          <div class="label">
            我来点评
          </div>
          <hr>
          <div class="title-input">
            <span class="comment-title-label">标题：</span>
            <input class="comment-title" type="text" name="">
          </div>
          <div class="rating-input">
            <span class="comment-rating-label">性价评分：</span>
            <input value="0" type="number"
              class="rating" min=0 max=5
              step=0.5 data-size="xs" data-symbol="&#xe005;">
            <div class="buy-way">
              <span class="buy-way-title">购买方式</span>
              <ul class="buy-way-sel more-sel">
                <li>天猫商城</li>
                <li>京东商城</li>
                <li>其他方式</li>
              </ul>
            </div>
          </div>
          <!-- 评论框 -->
          <textarea cols="0" rows="5" name="introduction" class="form-control" id="comment-input" style="margin: 0px -0.5px 0px 0px; height: 250px; width:100%;">
          </textarea>
          <!-- 评论添加按钮 -->
          <div class="comment-add-btn">
            <a href="#" class="add-btn">写好了</a>
          </div>
        </div>
      </div>
      <div class="comment-containter-right">
        <div class="same-class-rec">
          <div class="scr-title">
            <a href="#" class="scr-title-name">同类产品推荐</a>
            <a href="#" class="see-more">换一换</a>
          </div>
          <ul class="scr-list">
            <li class="scr-item">
              <img class="left picture" src="static\img\commodity_test.jpg">
              <div class="right">
                <h3 class="name"><a href="#">NEWA/妞娃家用RF射频电子美容仪器</a></h3>
                <p class="count">
                  <span>4.9分</span>|
                  <span><a href="#">999</a>点评</span>
                </p>
              </div>
            </li>
            <li class="scr-item">
              <img class="left picture" src="static\img\commodity_test.jpg">
              <div class="right">
                <h3 class="name"><a href="#">NEWA/妞娃家用RF射频电子美容仪器</a></h3>
                <p class="count">
                  <span>4.9分</span>|
                  <span><a href="#">999</a>点评</span>
                </p>
              </div>
            </li>
            <li class="scr-item">
              <img class="left picture" src="static\img\commodity_test.jpg">
              <div class="right">
                <h3 class="name"><a href="#">NEWA/妞娃家用RF射频电子美容仪器</a></h3>
                <p class="count">
                  <span>4.9分</span>|
                  <span><a href="#">999</a>点评</span>
                </p>
              </div>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
  <!-- 包含尾部 -->
  <jsp:include page="common/page_footer.jsp"/>
  
  <script src="static\js\jquery.js"></script>
  <script src="static\plugins\star-rating\star-rating.js"></script>
  <script type="text/javascript" src="static\plugins\kindeditor\kindeditor-all-min.js"></script>
  <script type="text/javascript" src="static\plugins\kindeditor\lang\zh-CN.js"></script>
  <script src="static\js\product_details.js"></script>
  <script type="text/javascript">
  /**
   *[初始化富文本]
   */
  function initkindEditor() {
    KindEditor.ready(function(K) {
      var editor = K.create('#comment-input', {
        themeType: "simple",
        uploadJson: '',
        resizeType: 0,
        pasteType: 2,
        syncType: "",
        filterMode: true,
        allowPreviewEmoticons: false,
        items: [
          'source', 'undo', 'redo', 'plainpaste', 'wordpaste', 'clearhtml', 'quickformat',
          'selectall', 'fullscreen', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor',
          'bold', 'italic', 'underline', 'hr', 'removeformat', '|', 'justifyleft', 'justifycenter',
          'justifyright', 'insertorderedlist', 'insertunorderedlist', '|', 'link', 'image',
          'unlink', 'emoticons'
        ],
        afterCreate: function() {
          this.sync();
        },
        afterBlur: function() {
          this.sync();
        },
        afterChange: function() {
          //富文本输入区域的改变事件，一般用来编写统计字数等判断
          K('.word_count1').html("最多20000个字符,已输入" + this.count() + "个字符");
        },
        afterUpload: function(url) {
          //上传图片后的代码
        },
        allowFileManager: false,
        allowFlashUpload: false,
        allowMediaUpload: false,
        allowFileUpload: false
      });
    });
  }
  initkindEditor();
  </script>
</body>

</html>
