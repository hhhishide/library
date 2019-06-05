package com.springcloud.springcloudlibrarynotice;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@MapperScan(value = "com.springcloud.springcloudlibrarynotice.Dao.noticeMapper")
@EnableEurekaClient
@SpringBootApplication
public class SpringcloudLibraryNoticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudLibraryNoticeApplication.class, args);
    }

}
