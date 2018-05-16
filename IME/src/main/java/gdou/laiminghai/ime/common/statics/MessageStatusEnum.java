package gdou.laiminghai.ime.common.statics;

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举：消息状态
 * @author laiminghai
 *
 */
public enum MessageStatusEnum implements BaseEnum<MessageStatusEnum, String> {
	READ_STATUS("0001","已读"),
	DELETED_STATUS("0010","删除");
	
	static Map<String, MessageStatusEnum> enumMap = new HashMap<>();

    static {
    	MessageStatusEnum[] values = MessageStatusEnum.values();
        for (MessageStatusEnum value : values) {
            enumMap.put(value.getCode(), value);
        }
    }
	
	private String code;
	private String name;
	
	private MessageStatusEnum(String code, String name) {
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

	public static MessageStatusEnum of(String code) {
		return enumMap.get(code);
	}
}
