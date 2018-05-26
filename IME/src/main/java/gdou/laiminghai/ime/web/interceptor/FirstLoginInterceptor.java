package gdou.laiminghai.ime.web.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import gdou.laiminghai.ime.common.exception.ServiceException;
import gdou.laiminghai.ime.common.exception.ServiceResultEnum;
import gdou.laiminghai.ime.common.statics.UserStatusEnum;

public class FirstLoginInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse arg1, Object handler, Exception e)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mav)
			throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) 
			throws Exception {
        HttpSession session = request.getSession();  
        Map<String, Object> userInfoMap = (Map<String, Object>) session.getAttribute("userInfo");
        if(userInfoMap != null && 
        		UserStatusEnum.FIRST_LOGIN.getCode().equals(userInfoMap.get("status"))) { 
        	request.getRequestDispatcher("/user/firstLogin.html").forward(request, response); 
        }
        return true; 
	}

}
