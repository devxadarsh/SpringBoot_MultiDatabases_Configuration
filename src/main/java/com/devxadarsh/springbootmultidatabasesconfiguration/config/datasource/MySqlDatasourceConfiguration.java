package com.devxadarsh.springbootmultidatabasesconfiguration.config.datasource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class MySqlDatasourceConfiguration {

    @ConfigurationProperties("spring.datasource.mysql")
    @Bean
    public DataSourceProperties mySqlDatasourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    public DataSource mySqlDatasource(){
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setUsername(mySqlDatasourceProperties().getUsername());
//        dataSource.setPassword(mySqlDatasourceProperties().getPassword());
//        dataSource.setUrl(mySqlDatasourceProperties().getUrl());
//        dataSource.setDriverClassName(mySqlDatasourceProperties().getDriverClassName());
//        return dataSource;

        // We can do all the upper task by using predefined method here how we will use it

        return mySqlDatasourceProperties().initializeDataSourceBuilder().build();
    }
}
