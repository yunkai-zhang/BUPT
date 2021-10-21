package com.zhangyun.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/*
* @Configuration相当于spring中的applicationContext.xml。
* 最开始spring在applicationContext.xml中配置bean，但是springboot和cloud偏好java配置类来配置bean，java配置类需要有@Configuration注解。
* */
@Configuration
public class ConfigBean {
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
