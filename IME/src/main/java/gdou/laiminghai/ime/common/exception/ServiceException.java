package gdou.laiminghai.ime.common.exception;

/**
 * 
 * @ClassName: ServiceException.java
 * @Description:  业务异常
 *
 * @author: laiminghai
 * @datetime: 2018年4月22日 下午1:29:23
 */
public class ServiceException extends RuntimeException {
	
	private Integer code;//错误码
	
	public ServiceException(ServiceResultEnum result) {
		super(result.getMessage());
		this.setCode(result.getCode());
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
}
