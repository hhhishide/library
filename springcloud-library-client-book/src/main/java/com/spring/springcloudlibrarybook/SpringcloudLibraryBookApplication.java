package com.spring.springcloudlibrarybook;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@MapperScan(basePackages = "com.spring.springcloudlibrarybook.Dao")
@EnableEurekaClient
@SpringBootApplication
public class SpringcloudLibraryBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudLibraryBookApplication.class, args);
    }

}
