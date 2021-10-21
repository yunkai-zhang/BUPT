package com.zhangyk.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InterceptorController {

    @GetMapping("/t")
    public String test(){
        System.out.println("IntercepterController=>test()执行了");

        return "ok";
    }
}
