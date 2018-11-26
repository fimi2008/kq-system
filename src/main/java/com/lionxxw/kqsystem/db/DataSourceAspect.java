package com.lionxxw.kqsystem.db;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class DataSourceAspect {

    /**
     * spring aop拦截规则
     */
    private static final String AOP_WITHIN = "within(com.lionxxw.kqsystem..*)";

    /**
     * 日志
     */
    private final Logger logger = Logger.getLogger(DataSourceAspect.class);


    /**
     * 方法调用之前执行
     *
     * @param ol 数据源参数信息
     */
    @Before(AOP_WITHIN + "&& @annotation(ol)")
    public synchronized void dataSource(JoinPoint jp, DataSource ol) {
        logger.info("当前数据源=====>"+ol.name());
        DataSourceHolder.setDataSource(ol.name());
    }

    /**
     * 方法调用结束之后执行
     */
    @After(AOP_WITHIN + "&& @annotation(ol)")
    public synchronized void dataSourceWrite(JoinPoint jp, DataSource ol) {
        DataSourceHolder.setDataSource(DataSource.write);
    }
}
