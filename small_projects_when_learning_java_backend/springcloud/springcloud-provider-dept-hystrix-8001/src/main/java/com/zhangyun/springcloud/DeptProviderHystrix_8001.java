package com.zhangyun.springcloud;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

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
//启动类添加对hystrix熔断的支持
@EnableCircuitBreaker
public class DeptProviderHystrix_8001 {
    public static void main(String[] args) {
        //第一页参数为启动类的类名，第二个参数为main函数的输入参数args
        SpringApplication.run(DeptProviderHystrix_8001.class,args);
    }

    //对hystrix-dashboard来说，下面的代码是死代码，为了配合监控使用。
    //增加一个Servlet
    @Bean
    public ServletRegistrationBean a(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new HystrixMetricsStreamServlet());
        //像servlet一样添加访问路径。路径为http://localhost:9001/hystrix页面中，
        // “Single Hystrix App: http://hystrix-app:port/actuator/hystrix.stream”字段的“/actuator/hystrix.stream”。
        //访问这个页面就可以被监控了
        registrationBean.addUrlMappings("/actuator/hystrix.stream");
        return registrationBean;
    }
}
