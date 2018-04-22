package gdou.laiminghai.ime.common.exception;

public enum ServiceResultEnum {
	SUCCESS(0,"操作成功"),
	USER_NO_LOGIN(200,"用户未登录"),
	UNKONWN_ERROR(-1,"未知错误");
	
	private Integer code;//错误码
	private String message;//错误信息
	
	private ServiceResultEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}
	
	public String getMessage() {
		return message;
	}
}
