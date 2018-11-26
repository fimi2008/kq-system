package com.lionxxw.kqsystem.code.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * <p>Description: session接口 </p>
 *
 * @author lionxxw
 * @version 1.0
 * @time 16/6/11 下午3:23
 */
public interface SessionProvider {

    /**
     * <p>Description: 往session设置值 </p>
     *
     * @param request
     * @param name session key值
     * @param value session value值
     * @author lionxxw
     * @date 16/6/11 下午3:26
     * @version 1.0
     */
    void setAttribute(HttpServletRequest request, String name, Serializable value);

    /**		
     * <p>Description: 从session中取值 </p>
     * 
     * @param request
     * @param name
     * @return Serializable
     * @author lionxxw
     * @date 16/6/11 下午3:32
     * @version 1.0
     */
    Serializable getAttribute(HttpServletRequest request, String name);
    
    /**		
     * <p>Description: 退出登录 </p>
     * 
     * @param request
     * @param response
     * @author lionxxw
     * @date 16/6/11 下午3:33
     * @version 1.0
     */
    void logout(HttpServletRequest request, HttpServletResponse response);
    
    /**		
     * <p>Description: 获取sessionId </p>
     * 
     * @param request
     * @return string sessionId
     * @author lionxxw
     * @date 16/6/11 下午3:34
     * @version 1.0
     */
    String getSessionId(HttpServletRequest request);
}