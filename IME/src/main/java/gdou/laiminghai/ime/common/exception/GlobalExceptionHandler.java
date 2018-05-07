package gdou.laiminghai.ime.common.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import gdou.laiminghai.ime.common.util.AjaxUtil;

@ControllerAdvice
public class GlobalExceptionHandler extends BaseExceptionHandler{
	/**
	 * 日志记录
	 */
	private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	/**
	 * 
	 * @Description:异常处理具体实现
	 * @param request
	 * @param response
	 * @param e
	 * @return
	 * 
	 * @author: laiminghai
	 * @datetime: 2018年4月22日 下午1:45:26
	 */
	private ModelAndView handleException(HttpServletRequest request,HttpServletResponse response,
			Exception e) {
		//Ajax请求异常
		if(AjaxUtil.isAjaxRequest(request)) {
			//处理自定义业务异常
			if (e instanceof ServiceException) {
				ServiceException servicException = (ServiceException)e;
				return handleAjaxException(response, servicException.getCode(), servicException.getMessage());
			} else {//处理其他异常
				logger.error("未知错误：",e);
				return handleAjaxException(response, 
						ServiceResultEnum.UNKONWN_ERROR.getCode(), ServiceResultEnum.UNKONWN_ERROR.getMessage());
			}
		}else {//页面请求异常
			if(e instanceof ServiceException) {
				return handleViewException(request.getRequestURL().toString(), e.getMessage(), "");
			}else {
				logger.error("未知错误：",e);
				return handleViewException(request.getRequestURL().toString(),  ServiceResultEnum.UNKONWN_ERROR.getMessage(), "");
			}
		}
		
	}
	
	/**
	 * 
	 * @Description:捕捉并处理404异常
	 * @param e
	 * @return
	 * 
	 * @author: laiminghai
	 * @datetime: 2018年4月22日 下午1:46:13
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ModelAndView handle404Error(Exception e) {
		 return new ModelAndView("");
	}
	
	
	/**
	 * 
	 * @Description:捕捉并处理自定义业务异常
	 * @param request
	 * @param response
	 * @param e
	 * @return
	 * 
	 * @author: laiminghai
	 * @datetime: 2018年4月22日 下午1:46:35
	 */
	@ExceptionHandler(ServiceException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView handlBusinessException(HttpServletRequest request, HttpServletResponse response, Exception e) {
		logger.error(e.getMessage(), e);
		return this.handleException(request, response, e);
	}

}
