package gdou.laiminghai.ime.common.util;

import gdou.laiminghai.ime.common.exception.ServiceResultEnum;
import gdou.laiminghai.ime.model.dto.ResultDTO;

/**
 * 
 * @ClassName: ResultDTOUtil.java
 * @Description:  构造Ajax返回结果
 *
 * @author: laiminghai
 * @datetime: 2018年4月22日 下午1:25:35
 */
public class ResultDTOUtil {
	/**
	 * 
	 * @Description: 构造成功结果
	 * @param object
	 * @return
	 * 
	 * @author: laiminghai
	 * @datetime: 2018年4月22日 下午1:26:01
	 */
	public static ResultDTO success(Object object) {
		ResultDTO result = new ResultDTO(true);
		result.setCode(ServiceResultEnum.SUCCESS.getCode());
		result.setMessage(ServiceResultEnum.SUCCESS.getMessage());
		result.setData(object);
		return result;
	}
	
	/**
	 * 
	 * @Description: 构造失败结果 
	 * @param code
	 * @param message
	 * @return
	 * 
	 * @author: laiminghai
	 * @datetime: 2018年4月22日 下午1:26:27
	 */
	public static ResultDTO error(Integer code,String message) {
		ResultDTO result = new ResultDTO(false);
		result.setCode(code);
		result.setMessage(message);
		return result;
	}
}
