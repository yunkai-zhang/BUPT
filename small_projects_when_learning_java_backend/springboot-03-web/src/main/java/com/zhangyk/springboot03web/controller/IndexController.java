package com.zhangyk.springboot03web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

//自定义的扩展mvc的类中，已经做了视图跳转的设置，这里不用做了。
//    //有下面两个请求的时候，走到当前函数
//    @RequestMapping({"/","/index.html"})
//    public String index(){
//        return "index";
//    }
}
