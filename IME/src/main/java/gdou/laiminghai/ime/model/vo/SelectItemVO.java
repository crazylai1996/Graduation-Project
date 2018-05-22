package gdou.laiminghai.ime.model.vo;

/**
 * 下拉框选择项
 * @author laiminghai
 *
 */
public class SelectItemVO {
	private String code;
	private String name;
	//我是分割线
	/**
	 * 是否已被选中
	 */
	private boolean selected;
	
	public SelectItemVO() {
		
	}
	
	public SelectItemVO(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	@Override
	public String toString() {
		return "SelectItemVO [code=" + code + ", name=" + name + ", selected=" + selected + "]";
	}
	
}
