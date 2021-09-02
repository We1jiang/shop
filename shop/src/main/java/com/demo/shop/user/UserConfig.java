
package com.demo.shop.user;


import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "UserEntityManagerFactory",
        basePackages = {"com.demo.shop.user.repository"})
public class UserConfig {

    @Autowired
    Environment env;

    @Bean(name = "UserDataSource")
    @ConfigurationProperties(prefix = "user.datasource")
    public DataSource dataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("user.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("user.datasource.url"));
        dataSource.setUsername(env.getProperty("user.datasource.username"));
        dataSource.setPassword(env.getProperty("user.datasource.password"));
        return dataSource;
    }


    @Bean(name = "UserEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean UserEntityManagerFactory(
            EntityManagerFactoryBuilder builder, @Qualifier("UserDataSource") DataSource dataSource) {
        return builder.dataSource(dataSource).packages("com.demo.shop.user.domain").persistenceUnit("User")
                .build();
    }

    @Bean(name = "UserTransactionManager")
    public PlatformTransactionManager UserTransactionManager(
            @Qualifier("UserEntityManagerFactory") EntityManagerFactory UserEntityManagerFactory) {
        return new JpaTransactionManager(UserEntityManagerFactory);
    }
}

