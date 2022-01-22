package com.zhangyun.dockerspringboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerForDockerTest {

    @RequestMapping("/hello")
    public String hello(){
        return "hello zy";
    }
}
