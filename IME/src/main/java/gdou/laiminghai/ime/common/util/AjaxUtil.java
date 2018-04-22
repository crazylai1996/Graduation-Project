package gdou.laiminghai.ime.common.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AjaxUtil {
	
    private  static  ObjectMapper mapper = new ObjectMapper();
    
    /**
     * 
     * @Description:  判断请求是否Ajax请求
     * @param request
     * @return
     *
     * @author: laiminghai
     * @datetime: 2018年4月22日 上午11:39:28
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String requestHeader = request.getHeader("X-Requested-With");
        return requestHeader != null ? "XMLHttpRequest".equals(requestHeader) : false;
    }

    /**
     * 
     * @Description: Ajax请求时，往页面回写Json对象
     * @param value
     * @param response
     * 
     * @author: laiminghai
     * @datetime: 2018年4月22日 上午11:41:43
     */
    public static  void writeJsonObject(Object value, HttpServletResponse response){
        JsonGenerator jsonGenerator = null;
        try {
            jsonGenerator=mapper.getFactory().createGenerator(response.getOutputStream(), JsonEncoding.UTF8);
            if(jsonGenerator!=null){
                jsonGenerator.writeObject(value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
