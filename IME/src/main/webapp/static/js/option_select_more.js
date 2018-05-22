var selectedOptions = [];
var selectedOptionValue = [];

/**
 * 初始化
 */
$(document).ready(function(){
	$(".selected-option").each(function(){
		var value = $(this).data("value");
		var name = $(this).text();
		var option = new Object();
		option.value = value;
		option.name = name;
		console.log(option);
		selectedOptions.push(option);
		selectedOptionValue.push(value);
	});
});
/**
 * 选择项选择
 * @returns
 */
$(".option-list a").click(function() {
	var value = $(this).data("value");
	var name = $(this).text();
	if($(this).hasClass("selected-option")){
		$.each(selectedOptions,function(i, option){
			if(option.value == value){
				selectedOptions.splice(i,1);
				selectedOptionValue.splice(i,1);
			}
		});
		$(this).removeClass("selected-option");
	}else{
		var option = new Object();
		option.value = value;
		option.name = name;
		selectedOptions.push(option);
		selectedOptionValue.push(value);
		$(this).addClass("selected-option");
	}
});
