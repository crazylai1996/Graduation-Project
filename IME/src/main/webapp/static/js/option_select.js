var selectedOption = null;
/**
 * 选择项选择
 * @returns
 */
$(".option-list a").click(function() {
	$(".option-list a").removeClass("selected-option");
	//保存选中的值
	selectedOption = new Object();
	selectedOption.value = $(this).data("value");
	selectedOption.name = $(this).text();
	$(this).addClass("selected-option");
});