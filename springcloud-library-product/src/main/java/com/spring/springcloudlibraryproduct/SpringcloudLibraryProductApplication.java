package com.spring.springcloudlibraryproduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableFeignClients(basePackages = "com.spring.springcloudlibraryproduct.Service")
@EnableHystrix
@SpringBootApplication(scanBasePackages = "com.spring.springcloudlibraryproduct")
public class SpringcloudLibraryProductApplication {

    @LoadBalanced
    @Bean(name = "restTemplate")
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    public static void main(String[] args) {
        SpringApplication.run(SpringcloudLibraryProductApplication.class, args);
    }

}
