//产品上市时间日期选择
layui.use('laydate', function(){
  var laydate = layui.laydate;
  laydate.render({
    elem: '.product-ttm',
    type: 'month',
    format: 'yyyy年MM月',
    theme: '#CC99CC'
  });
});
