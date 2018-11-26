package com.lionxxw.kqsystem.code.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**		
 * <p>Description: 只加载application.properties配置文件信息 </p>
 * 
 * @author lionxxw
 * @date 16/6/3 上午10:37
 * @version 1.0
 */
public class PropertiesUtil {
	
	protected static final Log logger = LogFactory.getLog(PropertiesUtil.class);
	
	private static Properties prop = new Properties();
	
	static {
		InputStream input = PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties");
		try {
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	public static String getProperty(String propertyKey) {
		String str = "";
		if(prop.isEmpty()){
			return str;
		}
		
		String url = prop.getProperty(propertyKey);
		try {
			str = new String(url.getBytes("ISO-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return str;
		}
		return str;
	}
	
	public static void printPropertyInfo(){
		for(Object key : prop.keySet()){
			logger.info(key + " = " + prop.getProperty(key.toString()));
		}
	}
}
