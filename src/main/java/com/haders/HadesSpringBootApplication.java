package com.haders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@SpringBootApplication
//@EntityScan(basePackages = "com.haders.entity")
public class HadesSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(HadesSpringBootApplication.class,args);
    }
}