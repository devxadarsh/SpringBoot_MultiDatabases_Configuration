package com.devxadarsh.springbootmultidatabasesconfiguration.config.database;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;


// to fix the error required a bean named 'entityManagerFactory' that could not be found.
// we can change mySqlEntityManagerFactoryBean to entityManagerFactory because when application start
// multiple source will ask for th entityManagerFactory

// we can also use @Bean(name = "entityManagerFactory")

// we can also use @EnableJpaRepositories( entityManagerFactoryRef = "mySqlEntityManagerFactoryBean" )

// it will still give bean problem to fix use
//@EnableJpaRepositories(
//        basePackages = "repository path of desired repo",
//        entityManagerFactoryRef = "mySqlEntityManagerFactoryBean" // Name of the bean in the jpaconfiguration class)

@Configuration
@EnableJpaRepositories(
        basePackages = "com.devxadarsh.springbootmultidatabasesconfiguration.repository.postgresrepo",
        entityManagerFactoryRef = "postgresEntityManagerFactoryBean",
        transactionManagerRef = "postgresTransactionManager"
)
@EnableTransactionManagement
public class PostgresJPAConfiguration {

    @Bean
    LocalContainerEntityManagerFactoryBean postgresEntityManagerFactoryBean(
            EntityManagerFactoryBuilder entityManagerFactoryBuilder,
            @Qualifier("postgreSqlDatasource") DataSource dataSource){
        return entityManagerFactoryBuilder.dataSource(dataSource)
                .packages("com.devxadarsh.springbootmultidatabasesconfiguration.entity.postgresentity")
                .build();

    }


    /**
     * By default, there is only one transaction manager in the project if we are using a single database.
     * Here we are using multiple database, so  we have to create multiple transaction manger
     * In this demo project we will make two transaction manager
     *
     * We have to also put transactionManagerRef = "postgresTransactionManager" in the @EnableJpaRepositories
     *
     */
    @Bean
    PlatformTransactionManager postgresTransactionManager (@Qualifier("postgresEntityManagerFactoryBean") LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean) {
        return new JpaTransactionManager(Objects.requireNonNull(localContainerEntityManagerFactoryBean.getObject())); // for null safety
//        return new JpaTransactionManager(localContainerEntityManagerFactoryBean.getObject());
    }

    // it will throw table not exist in database error to handle that error we have to add this configuration in properties
    // spring.jpa.generate-ddl=true
    // by default it will be false


    /**
     * Here is a tip
     * If you are creating a transactionmanager bean  the best practice is use @EnableTransactionManager on class level
     */
}
