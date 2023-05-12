package com.exercise;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
public class MyDataSourceConfiguration {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.db1")
    public DataSource db1DataSource() {
        log.info("db1 datasource init ......");
        return DataSourceBuilder.create().build();
    }
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.db2")
    public DataSource db2DataSource() {
        log.info("db2 datasource init ......");
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public DataSource primaryDataSource(
            @Autowired @Qualifier("db1DataSource") DataSource db1DataSource,
            @Autowired @Qualifier("db2DataSource") DataSource db2DataSource
    ) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("db1DataSource", db1DataSource);
        map.put("db2DataSource", db2DataSource);
        RoutingDataSource routingDataSource = new RoutingDataSource();
        routingDataSource.setTargetDataSources(map);
        routingDataSource.afterPropertiesSet();
        return routingDataSource;
    }

}
