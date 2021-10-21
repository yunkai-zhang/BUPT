package com.zhangyun.service;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

//zookeeper：服务注册与发现

/*
* 使用dubbo后尽量不要用spring自带的@Service注解，因为dubbo自己也有一个service注解用于dubbo扫描，容易弄混，不如用万能的Component注解
* */
@Component
/*
* 加了dubbo的@Service后，类可以被dubbo扫描到，在项目启动时就自动注册到注册中心。
* */
@Service
public class TicketServiceImpl implements TicketService{

    @Override
    public String getTicket() {
        return "张云学java";
    }
}
