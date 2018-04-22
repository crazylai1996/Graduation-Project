package gdou.laiminghai.ime.common.statics;

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举：消息类型
 * @author laiminghai
 *
 */
public enum MessageTypeEnum implements BaseEnum<MessageTypeEnum, String>{
	SYSTEM_MSG("0000","系统消息"),
	INTERACTIVE_MSG("0001","互动消息"),
	PRIVATE_MSG("0010","私信消息");

	static Map<String, MessageTypeEnum> enumMap = new HashMap<>();

    static {
    	MessageTypeEnum[] values = MessageTypeEnum.values();
        for (MessageTypeEnum value : values) {
            enumMap.put(value.getCode(), value);
        }
    }
	
	private String code;
	private String name;
	
	private MessageTypeEnum(String code, String name) {
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
	public MessageTypeEnum of(String code) {
		return enumMap.get(code);
	}

}
