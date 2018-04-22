$(document).ready(function() {
  /**
   * [加载评分插件]
   */
  var $input = $('input.rating'), count = Object.keys($input).length;
  if (count > 0) {
      $input.rating();
  }
});
