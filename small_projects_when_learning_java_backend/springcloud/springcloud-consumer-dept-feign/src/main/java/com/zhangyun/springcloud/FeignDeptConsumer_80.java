package com.zhangyun.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@SpringBootApplication
/*
* 老实说，这个注解可以扫描到springcloud-api子module下的DeptClientService.java，但是为什么可以跨module扫描？：
* 因为api是公共模块，每个子模块都有引入这个公共模块，就没有跨模块编程。
* */
@EnableFeignClients(basePackages = {"com.zhangyun.springcloud"})
/*
* 有人问：这里需要@ComponentScan去扫描自己的FeignConfigBean.java吗？：
* 其实不用，因为冗余的声明，springbootapplication已应用给定的@componentscan
* */
public class FeignDeptConsumer_80 {
    public static void main(String[] args) {
        SpringApplication.run(FeignDeptConsumer_80.class,args);
    }
}
