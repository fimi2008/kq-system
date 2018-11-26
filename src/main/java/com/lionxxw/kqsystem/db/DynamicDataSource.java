package com.lionxxw.kqsystem.db;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 获取数据源（依赖于spring）
 * Created by lionxxw on 2016/8/4.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceHolder.getDataSource();
    }
}
