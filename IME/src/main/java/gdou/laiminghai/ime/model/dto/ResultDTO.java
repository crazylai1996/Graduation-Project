package gdou.laiminghai.ime.model.dto;

/**
 * 
 * @ClassName: Result.java
 * @Description:  Ajax请求返回结果
 *
 * @author: laiminghai
 * @datetime: 2018年4月22日 下午12:00:26
 * @param <T>
 */
public class ResultDTO<T> {
	private boolean success;//请求是否成功
	private Integer code;//返回状态码
	private String message;//返回消息
	private T data;//返回数据
	
	/**
	 * 无参构造函数
	 */
	
	public ResultDTO() {
		super();
	}
	
	/**
	 * 构造函数
	 * @param success
	 */
	public ResultDTO(boolean success) {
		super();
		this.success = success;
	}

	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
}
