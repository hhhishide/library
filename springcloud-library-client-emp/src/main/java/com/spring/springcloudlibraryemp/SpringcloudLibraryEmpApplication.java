package com.spring.springcloudlibraryemp;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@MapperScan(basePackages = "com.spring.springcloudlibraryemp.Dao")
@EnableEurekaClient
@SpringBootApplication
public class SpringcloudLibraryEmpApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudLibraryEmpApplication.class, args);
    }

}
