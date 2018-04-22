package gdou.laiminghai.ime.model.vo;

/**
 * 下拉框选择项
 * @author laiminghai
 *
 */
public class SelectItemVO {
	private String code;
	private String name;
	
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
	
	@Override
	public String toString() {
		return "SelectItem [code=" + code + ", name=" + name + "]";
	}
}
