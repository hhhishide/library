package com.spring.springcloudlibraryproduct.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;


/**
 * web配置
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        // "file:D:/ideas/library/springcloud-library-product/src/main/resources/static/images/tou/"
        registry.addResourceHandler("/images/tou/**").addResourceLocations("file:D://ideas/library/springcloud-library-product/src/main/resources/static/images/tou/");
    }


}
