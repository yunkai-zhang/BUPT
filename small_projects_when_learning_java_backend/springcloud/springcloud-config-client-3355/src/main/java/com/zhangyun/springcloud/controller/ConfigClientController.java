package com.zhangyun.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ConfigClientController {
    @Value("${spring.application.name}")
    private String applicationName;
    @Value("${eureka.client.service-url.defaultZone}")
    private String eurekaServer;
    @Value("${server.port}")
    private String port;

    //如果访问下面的链接能拿到消息，说明客户端通过服务端从远程git库拿到了配置信息
    @RequestMapping("/config")
    public String getConfig(){
        return "applicationName:"+applicationName+
                "。eurekaServer:"+eurekaServer+
                "。port:"+port;
    }
}
