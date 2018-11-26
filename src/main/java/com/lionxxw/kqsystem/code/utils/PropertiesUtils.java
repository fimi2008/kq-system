package com.lionxxw.kqsystem.code.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {
    protected static final Log logger = LogFactory.getLog(PropertiesUtils.class);

    /**
     * 返回properties文件values
     *
     * @param propertiesName properties文件名
     * @param key            properties属性key
     * @return
     */
    public static String[] returnProperties(String propertiesName, String key) {
        Properties props = returnProperties(propertiesName);
        String services = (String) props.get(key);
        return services.split(",");
    }

    public static Properties returnProperties(String propertiesName) {
        InputStream inputStream = null;
        ClassLoader cl = PropertiesUtils.class.getClassLoader();
        if (cl != null) {
            inputStream = cl.getResourceAsStream(propertiesName);
        } else {
            inputStream = ClassLoader.getSystemResourceAsStream(propertiesName);
        }

        Properties props = new Properties();
        try {
            props.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }
}
