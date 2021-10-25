package com.zhangyun.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/*Eureka是C-S架构，主启动类添加该注解后，在子module启动后会自动注册本module的服务到Eureka中
*
* 可能有人会问“提供服务不应该用server吗？怎么用client？”。答：服务的提供者和消费者在Eureka C-S架构中
* 都属于客户端client，两client都需要与服务端server（Eureka-server）交互。
* */
@EnableEurekaClient
//@SpringBootApplication注解让当前类编程子module的主启动类
@SpringBootApplication
//服务发现
@EnableDiscoveryClient
public class DeptProvider_8003 {
    public static void main(String[] args) {
        //第一页参数为启动类的类名，第二个参数为main函数的输入参数args
        SpringApplication.run(DeptProvider_8003.class,args);
    }
}
