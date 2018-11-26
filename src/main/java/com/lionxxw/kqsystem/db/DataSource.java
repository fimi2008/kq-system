package com.lionxxw.kqsystem.db;

import java.lang.annotation.*;

/**
 * Created by lionxxw on 2016/8/4.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {

    String name() default DataSource.write;

    String write = "writeDataSource";

    String read = "readDataSource";
}
