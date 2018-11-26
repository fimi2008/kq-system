package com.lionxxw.kqsystem.code.utils;

/**
 * <p>Description: 异常判断工具类 </p>
 *
 * @author lionxxw
 * @version 1.0
 * @time 16/5/6 上午10:04
 */
public class ExceptionUtils {

    /**		
     * <p>Description: 校验对象是否为空 </p>
     * 
     * @param obj
     * @author lionxxw
     * @date 16/5/6 上午10:09
     * @version 1.0
     */
    public static void checkObjIsNull(Object obj){
        if (null == obj){
            throw new RuntimeException(obj.getClass().getSimpleName()+"对象为空!");
        }
    }

    /**		
     * <p>Description: 校验对象是否存在 </p>
     * 
     * @param obj
     * @author lionxxw
     * @date 16/5/6 上午10:19
     * @version 1.0
     */
    public static void checkObjNotExist(Object obj){
        if (null == obj){
            throw new RuntimeException(obj.getClass().getSimpleName()+"对象不存在!");
        }
    }

    /**		
     * <p>Description: 校验主键id是否为空 </p>
     * 
     * @param id 主键id
     * @param clz service接口类
     * @param method 接口方法名
     * @author lionxxw
     * @date 16/5/6 上午10:10
     * @version 1.0
     */
    public static void checkIdIsNull(Long id, Class clz, String method){
        if (ObjectUtils.isNull(id)){
            throw new RuntimeException(clz.getSimpleName()+"中"+method+"方法主键id为空!");
        }
    }

    public static void checkIdIsNull(Integer id, Class clz, String method){
        if (ObjectUtils.isNull(id)){
            throw new RuntimeException(clz.getSimpleName()+"中"+method+"方法主键id为空!");
        }
    }

    public static void checkIdIsNull(String id, Class clz, String method){
        if (ObjectUtils.isNull(id)){
            throw new RuntimeException(clz.getSimpleName()+"中"+method+"方法主键为空!");
        }
    }
}
