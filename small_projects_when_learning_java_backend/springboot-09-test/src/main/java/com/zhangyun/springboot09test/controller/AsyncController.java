package com.zhangyun.springboot09test.controller;

import com.zhangyun.springboot09test.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AsyncController {
    //从spring容器拿到asyncService实例
    @Autowired
    AsyncService asyncService;

    //写一个方法调用service层异步的方法
    @RequestMapping("hello")
    public String hello(){
        asyncService.hello();//停止三秒,网站是转圈的
        return "ok";//走到这才相应一个ok
    }
}
