package com.lionxxw.kqsystem.code.web;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>Description: 自定义时间转换器 </p>
 *  用于spring mvc 全局日期格式转换
 *
 *  用法:
 *    <!--配置全局日期转换器-->
 *    <bean class="org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter">
 *       <property name="webBindingInitializer">
 *          <bean class="com.lionxxw.common.web.CustomDateEdtor"/>
 *       </property>
 *    </bean>
 *
 * @author lionxxw
 * @version 1.0
 * @time 16/5/21 下午6:00
 */
public class CustomDateEdtor implements WebBindingInitializer {

    public void initBinder(WebDataBinder binder, WebRequest request) {
        // 转换日期格式
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        /**
         * CustomDateEditor(dateFormat, allowEmpty)
         * 参数说明:
         *     dateFormat 日期格式
         *     allowEmpty 是否允许为空
         */
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
