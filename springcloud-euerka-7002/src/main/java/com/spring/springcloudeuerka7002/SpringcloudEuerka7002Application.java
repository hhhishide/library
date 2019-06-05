package com.spring.springcloudeuerka7002;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SpringcloudEuerka7002Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudEuerka7002Application.class, args);
    }

}
