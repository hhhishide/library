package com.springcloud.records;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@MapperScan(basePackages = "com.springcloud.records.Dao")
@EnableEurekaClient
@SpringBootApplication
public class RecordsApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecordsApplication.class, args);
    }

}
