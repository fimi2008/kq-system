package com.lionxxw.kqsystem.code.encode;

/**
 * <p>Description: 自定义Md5加密规则接口 </p>
 *
 * @author lionxxw
 * @version 1.0
 * @time 16/6/11 下午10:18
 */
public interface EncodeMd5 {

    /**		
     * <p>Description: 密码加密 </p>
     * 
     * @param password 密码
     * @return string 加密后密码
     * @author lionxxw
     * @date 16/6/11 下午10:50
     * @version 1.0
     */
    String encode(String password);
}
