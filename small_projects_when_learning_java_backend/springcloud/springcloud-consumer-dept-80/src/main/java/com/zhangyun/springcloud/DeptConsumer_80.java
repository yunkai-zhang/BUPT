package com.zhangyun.springcloud;

import com.zhangyun.myrule.ZhangRuleConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@EnableEurekaClient
@SpringBootApplication
//在微服务启动时就能加载一些自定义的ribbon类。可以方便看到我们针对某个服务用了哪个负载均衡的配置；而不像在ConfigBean.java中配置的那样对所有服务生效。
@RibbonClient(name="SPRINGCLOUD-PROVIDER-DEPT",configuration = ZhangRuleConfig.class)
public class DeptConsumer_80 {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer_80.class,args);
    }
}
