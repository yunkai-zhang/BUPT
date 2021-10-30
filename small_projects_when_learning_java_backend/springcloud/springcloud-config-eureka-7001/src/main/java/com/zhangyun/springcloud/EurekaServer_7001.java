package com.zhangyun.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

//@EnableEurekaServer表示当前类是一个eureka server（服务端）的启动类，当前module的EurekaServer可以接收别人注册进来。
@EnableEurekaServer
@SpringBootApplication
public class EurekaServer_7001 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServer_7001.class,args);
    }
}
