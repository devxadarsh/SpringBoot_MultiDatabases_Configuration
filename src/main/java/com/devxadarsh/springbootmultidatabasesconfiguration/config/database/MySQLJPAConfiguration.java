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
//        entityManagerFactoryRef = "mySqlEntityManagerFactoryBean" // Name of the bean in the jpaconfuguration class)

@Configuration
@EnableJpaRepositories(
        basePackages = "com.devxadarsh.springbootmultidatabasesconfiguration.repository.mysqlrepo",
        entityManagerFactoryRef = "mySqlEntityManagerFactoryBean",
        transactionManagerRef = "mySqlTransactionManager"
)
@EnableTransactionManagement
public class MySQLJPAConfiguration {

    @Bean
    LocalContainerEntityManagerFactoryBean mySqlEntityManagerFactoryBean(
            EntityManagerFactoryBuilder entityManagerFactoryBuilder,
            @Qualifier("mySqlDatasource") DataSource dataSource){
        return entityManagerFactoryBuilder.dataSource(dataSource)
                .packages("com.devxadarsh.springbootmultidatabasesconfiguration.entity.mysqlentity")
                .build();

    }

//    @Bean
//    LocalContainerEntityManagerFactoryBean mySqlEntityManagerFactoryBean(
//            EntityManagerFactoryBuilder entityManagerFactoryBuilder,
//            @Qualifier("mySqlDatasource") DataSource dataSource) {
//        return entityManagerFactoryBuilder.dataSource(dataSource)
//                .packages("com.devxadarsh.springbootmultidatabasesconfiguration.entity.mysqlentity")
//                .build();
//    }

    /**
     * Description:
     *
     * Parameter 0 of method openEntityManagerInViewInterceptorConfigurer in org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration$JpaWebConfiguration required a single bean, but 2 were found:
     * 	- mySqlEntityManagerFactoryBean: defined by method 'mySqlEntityManagerFactoryBean' in class path resource [com/devxadarsh/springbootmultidatabasesconfiguration/config/database/MySQLJPAConfiguration.class]
     * 	- postgresEntityManagerFactoryBean: defined by method 'postgresEntityManagerFactoryBean' in class path resource [com/devxadarsh/springbootmultidatabasesconfiguration/config/database/PostgresJPAConfiguration.class]
     *
     * This may be due to missing parameter name information
     *
     * Action:
     *
     * Consider marking one of the beans as @Primary, updating the consumer to accept multiple beans, or using @Qualifier to identify the bean that should be consumed
     */

// this is coming due to the web app pom.xml dependency because it is designed in the manner to avoid this we have to disable
// to fix this go to the properties file and put: spring.jpa.open-in-view=false  #bydefault it is true

    /**
     * By default their is only one transaction manager in the project if we are using a single database.
     * Here we are using multiple database so  we have to create multiple transaction manger
     * In this demo project we will make two transaction manager
     *
     * We have to also put transactionManagerRef = "mySqlTransactionManager" in the @EnableJpaRepositories
     *
     */
    @Bean
    PlatformTransactionManager mySqlTransactionManager (@Qualifier("mySqlEntityManagerFactoryBean") LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean) {
        return new JpaTransactionManager(Objects.requireNonNull(localContainerEntityManagerFactoryBean.getObject())); // for null safety
//        return new JpaTransactionManager(localContainerEntityManagerFactoryBean.getObject());
    }

    // it will throw table not exist in database error to handle that error we have to add this configuration in properties
    // spring.jpa.generate-ddl=true
    // by default it will be false

    /**
     * Here is a tip
     * If you are creating a transaction manager bean the the best practice is use @EnableTransactionManagement on class level
     */


}


