package gdou.laiminghai.ime.common.statics;

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举：用户状态
 * @author laiminghai
 *
 */
public enum UserStatusEnum implements BaseEnum<UserStatusEnum, String> {
	NON_ACTIVATION("0000","未激活"),
	FIRST_LOGIN("0001","第一次登录"),
	NORMAL_STATUS("0010","正常状态"),
	FREEZEN_STATUS("0011","冻结状态"),
	DELETED_STATUS("0100","删除状态");
	
	static Map<String, UserStatusEnum> enumMap = new HashMap<>();

    static {
    	UserStatusEnum[] values = UserStatusEnum.values();
        for (UserStatusEnum value : values) {
            enumMap.put(value.getCode(), value);
        }
    }
	
	private String code;
	private String name;
	
	private UserStatusEnum(String code, String name) {
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
	public UserStatusEnum of(String code) {
		return enumMap.get(code);
	}
}
