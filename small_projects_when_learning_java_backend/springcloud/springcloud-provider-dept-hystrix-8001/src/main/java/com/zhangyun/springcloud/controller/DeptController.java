package com.zhangyun.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zhangyun.springcloud.pojo.Dept;
import com.zhangyun.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.remoting.RemoteTimeoutException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//提供restful服务
//@RestController使返回字符串不走视图解析器，直接以字符串形式输出在浏览器上
@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;

    //服务提供者的服务url地址，要和消费者的restTemplate.getForObject中的url一致。
    @GetMapping("/dept/qbid/{id}")
    //为controller1方法添加熔断保护机制。本方法失败了就会调用fallbackMethod指定的方法。
    @HystrixCommand(fallbackMethod = "hystrixGet")
    public Dept get(@PathVariable("id") long id){
        Dept dept = deptService.queryById(id);

        //捕获完异常后程序能正常执行，不影响原有代码。
        if (dept==null){
            throw new RemoteTimeoutException("id=>"+id+"，不存在该用户，或信息无法找到");
        }

        return dept;
    }
    //get的备选方案。这个方案更棒，对象能拿到，但是信息是记录了不存在。这个会在get失败时直接被调用，不需要写自己的url绑定
    public Dept hystrixGet(@PathVariable("id") long id){
        return new Dept()
                .setDeptno(id).setDname("id=>"+id+"，没有对应的信息，null--@Hystrix")
                .setDb_source("this database doesnot exist in Mysql");
    }
}
