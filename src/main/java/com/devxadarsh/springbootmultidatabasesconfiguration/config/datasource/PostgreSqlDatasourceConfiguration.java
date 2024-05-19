package com.devxadarsh.springbootmultidatabasesconfiguration.config.datasource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class PostgreSqlDatasourceConfiguration {

    @ConfigurationProperties("spring.datasource.pg")
    @Bean
    public DataSourceProperties postgreSqlDatasourceProperties(){
        return new DataSourceProperties();
    }

    @Primary
    @Bean
    public DataSource postgreSqlDatasource(){
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setUsername(postgreSqlDatasourceProperties().getUsername());
//        dataSource.setPassword(postgreSqlDatasourceProperties().getPassword());
//        dataSource.setUrl(postgreSqlDatasourceProperties().getUrl());
//        dataSource.setDriverClassName(postgreSqlDatasourceProperties().getDriverClassName());
//
//        return dataSource;

        // We can do all the upper task by using predefined method here how we will use it
        return postgreSqlDatasourceProperties().initializeDataSourceBuilder().build();
    }
}
