package com.lionxxw.kqsystem.code.utils;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.StringWriter;

/**
 * <p>Description: 基于springmvc转json工具类 </p>
 *
 * @author lionxxw
 * @version 1.0
 * @time 16/6/20 下午5:49
 */
public class JsonUtils {

    /**		
     * <p>Description: 对象转json </p>
     * 
     * @param obj
     * @return String
     * @author lionxxw
     * @date 16/6/20 下午5:53
     * @version 1.0
     */
    public static String toJson(Object obj) throws Exception{
        // springmvc
        ObjectMapper om = new ObjectMapper();
        om.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);// 设置序列化,不显示null
        // 流
        StringWriter sw = new StringWriter();
        // 对象转json 写的过程
        om.writeValue(sw, obj);
        return sw.toString();
    }

    public static <T> T  toObject(String json, Class<T> obj) throws Exception{
        // springmvc
        ObjectMapper om = new ObjectMapper();
        // json转对象 读的过程
        return om.readValue(json, obj);
    }
}