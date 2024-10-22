package com.haders.configs;


import com.alibaba.druid.pool.DruidDataSource;
import com.mysql.jdbc.Driver;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.haders.repositories")  //启动JPA com/haders/repositories
@EnableTransactionManagement    //开启事务
public class ApplicationConfig {

//    @Autowired
//    DataSourceProperties dataSourceProperties;

    @Bean

//    @ConfigurationProperties(prefix = "spring.datasource")
    @Primary
    public DataSource dataSource() {
        //整合Druid 手动读取
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUsername("root");
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://120.55.68.139:3306/haders?serverTimezone=Asia/Shanghai");
        druidDataSource.setPassword("5)fs9%rfF>Ko!");
        return druidDataSource;
            //通过配置读取
//        DruidDataSource druidDataSource = new DruidDataSource();
//        druidDataSource.setUsername(dataSourceProperties.getUsername());
//        druidDataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
//        druidDataSource.setUrl(dataSourceProperties.getUrl());
//        druidDataSource.setPassword(dataSourceProperties.getPassword());
//        return druidDataSource;


//        return DataSourceBuilder.create(properties.getClassLoader())
//                .type(HikariDataSource.class)
//                .driverClassName(properties.determineDriverClassName())
//                .url(properties.determineUrl())
//                .username(properties.determineUsername())
//                .password(properties.determinePassword())
//                .build();


//        return DataSourceBuilder.create().build();


    }

    
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(false);
        vendorAdapter.setShowSql(true);
        
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.haders.entity");//实体类的包全限定名
//        factory.setDataSource(dataSource(dataSourceProperties));
        factory.setDataSource(dataSource());
        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }
    
//    @Bean
//    public PhysicalNamingStrategyStandardImpl physicalNamingStrategy() {
//        return new PhysicalNamingStrategyStandardImpl();
//    }

//    public  String camelToUnderscore(String camelCaseStr) {
//        // 使用正则表达式匹配大写字母
//        String regex = "([a-z])([A-Z])";
//        // 替换匹配到的大写字母，在其前加上下划线
//        String replacement = "$1_$2";
//        // 转换为小写
//        return camelCaseStr.replaceAll(regex, replacement).toLowerCase();
//    }
}