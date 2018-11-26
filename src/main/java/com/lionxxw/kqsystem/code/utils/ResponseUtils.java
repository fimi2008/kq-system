package com.lionxxw.kqsystem.code.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>Description: 异步返回各种格式 json,xml,text</p>
 *
 * @author lionxxw
 * @version 1.0
 * @time 16/5/21 下午10:35
 */
public class ResponseUtils {

    public static void render(HttpServletResponse response, String contentType, String text){
        response.setContentType(contentType);
        try {
            response.getWriter().write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**		
     * <p>Description: 发送JSON格式数据 </p>
     * 
     * @param response
     * @param obj
     * @author lionxxw
     * @date 16/5/21 下午10:43
     * @version 1.0
     */
    public static void renderJson(HttpServletResponse response, Object obj){
        String txt = "";
        try {
            txt = JsonUtils.toJson(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        render(response, "application/json;charset=UTF-8", txt);
    }

    /**		
     * <p>Description: 发送XML格式数据 </p>
     * 
     * @param response
     * @param text
     * @author lionxxw
     * @date 16/5/21 下午10:44
     * @version 1.0
     */
    public static void renderXml(HttpServletResponse response, String text){
        render(response, "text/xml;charset=UTF-8", text);
    }

    /**		
     * <p>Description: 发送TEXT格式数据 </p>
     * 
     * @param response
     * @param text
     * @author lionxxw
     * @date 16/5/21 下午10:44
     * @version 1.0
     */
    public static void renderText(HttpServletResponse response, String text){
        render(response, "text/plain;charset=UTF-8", text);
    }
}
