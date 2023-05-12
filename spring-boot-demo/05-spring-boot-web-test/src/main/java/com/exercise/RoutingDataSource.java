package com.exercise;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

@Slf4j
public class RoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        String dataSourceKey = RoutingDataSourceContext.getDataSourceKey();
        log.info("当前数据源是:{}", dataSourceKey);
        return dataSourceKey;
    }
}
