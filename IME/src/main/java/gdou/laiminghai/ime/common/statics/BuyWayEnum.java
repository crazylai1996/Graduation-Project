package gdou.laiminghai.ime.common.statics;

import java.util.HashMap;
import java.util.Map;

public enum BuyWayEnum implements BaseEnum<BuyWayEnum, String> {
	TMALL_ONLINE("0000","天猫商城"),
	JINGDONG_ONLINE("0001","京东商城"),
	BUY_OFFLINE("0010","线下购买"),
	OTHER_WAY("0011","其他方式");
	
	static Map<String, BuyWayEnum> enumMap = new HashMap<>();

    static {
    	BuyWayEnum[] values = BuyWayEnum.values();
        for (BuyWayEnum value : values) {
            enumMap.put(value.getCode(), value);
        }
    }
	
	private String code;
	private String name;
	
	private BuyWayEnum(String code, String name) {
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
	public BuyWayEnum of(String code) {
		return enumMap.get(code);
	}

}
