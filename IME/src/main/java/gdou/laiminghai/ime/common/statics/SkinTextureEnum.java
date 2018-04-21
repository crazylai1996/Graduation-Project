package gdou.laiminghai.ime.common.statics;

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举：用户肤质
 * @author laiminghai
 *
 */
public enum SkinTextureEnum implements BaseEnum<SkinTextureEnum, String>{
	NEUTRAL_SKIN("0000","中性肤质"),
	DRY_SKIN("0001","干性肤质"),
	OILY_SKIN("0010","油性肤质"),
	MIXED_SKIN("0011","混合性肤质"),
	SENSITIVE_SKIN("0100","敏感性肤质");
	
	static Map<String, SkinTextureEnum> enumMap = new HashMap<>();

    static {
    	SkinTextureEnum[] values = SkinTextureEnum.values();
        for (SkinTextureEnum value : values) {
            enumMap.put(value.getCode(), value);
        }
    }
	
	private String code;
	private String name;
	
	private SkinTextureEnum(String code, String name) {
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

	@Override
	public SkinTextureEnum of(String code) {
		return enumMap.get(code);
	}
}
