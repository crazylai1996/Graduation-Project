package gdou.laiminghai.ime.common.statics;

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举：性别
 * @author laiminghai
 *
 */
public enum GenderEnum implements BaseEnum<GenderEnum, String> {
	MALE("1","男"),
	FEMALE("2","女");

	static Map<String, GenderEnum> enumMap = new HashMap<>();

    static {
    	GenderEnum[] values = GenderEnum.values();
        for (GenderEnum value : values) {
            enumMap.put(value.getCode(), value);
        }
    }
	
	private String code;
	private String name;
	
	private GenderEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}
	
	@Override
	public String getCode() {
		return this.code;
	}

	@Override
	public String getName() {
		return this.name;
	}

	public static GenderEnum of(String code) {
		return enumMap.get(code);
	}
	
}
