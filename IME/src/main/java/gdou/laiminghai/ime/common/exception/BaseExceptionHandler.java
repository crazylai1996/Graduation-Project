package gdou.laiminghai.ime.common.exception;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import gdou.laiminghai.ime.common.util.AjaxUtil;
import gdou.laiminghai.ime.common.util.ResultDTOUtil;

/**
 * 
 * @ClassName: BaseExceptionHandler.java
 * @Description:  异常处理基类
 *
 * @author: laiminghai
 * @datetime: 2018年4月22日 下午1:49:18
 */
public class BaseExceptionHandler {
	
	/**
	 * 
	 * @Description:处理普通页面请求
	 * @param url 跳转的页面
	 * @param errorMessage
	 * @param viewName
	 * @return
	 * 
	 * @author: laiminghai
	 * @datetime: 2018年4月22日 下午1:47:26
	 */
	protected ModelAndView handleViewException(String url, String errorMessage, String viewName) {        
        ModelAndView mav = new ModelAndView();       
        mav.addObject("redirectUrl", url);     
        mav.addObject("errorMessage", errorMessage);  
        mav.setViewName(viewName);    
        
        return mav;   
	}
	
	/**
	 * 
	 * @Description: 处理Ajax请求
	 * @param response
	 * @param code
	 * @param execptionMessage
	 * @return
	 * 
	 * @author: laiminghai
	 * @datetime: 2018年4月22日 下午1:47:38
	 */
	protected ModelAndView handleAjaxException(HttpServletResponse response,
			Integer code,String execptionMessage){
		response.setContentType("text/xml;charset=utf-8");
		AjaxUtil.writeJsonObject(ResultDTOUtil.error(code, execptionMessage), response);
	    return null;    
    }
}
