package com.ruoyi.framework.datasource;

import java.util.Map;
import javax.sql.DataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author 终于白发始于青丝
 * @Classname DynamicDataSource
 * @Description 类方法说明：动态数据源
 * @Date 2022/3/25 下午 14:56
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    public DynamicDataSource(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSourceType();
    }
}