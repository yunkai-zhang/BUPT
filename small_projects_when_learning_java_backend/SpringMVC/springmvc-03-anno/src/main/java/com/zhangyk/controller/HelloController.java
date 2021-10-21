package com.zhangyk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//正常开发不实现接口，而用注解实现:方法+注解跳转到页面，不用像servlet那么写。
@Controller
//@RequestMapping可以用在类上和方法上。但是一般都只在方法上写，写死。
@RequestMapping("/hello")
/*
如果一个类加上@RestController,这个类就不会被视图解析器解析，类中方法返回的就直接是个字符串。以后传json的时候会用上这个注解
@RestController
*/
public class HelloController {

    /*
    url怎么请求过来?:用注解@RequestMapping。
    方法的真实路径是类上@RequestMapping和方法上@RequestMapping设置的url的拼接.如下面方法的全路径应该是 //localhost:8080/hello/h1
    */
    @RequestMapping("/h1")
    public String hello(Model model){
        //封装数据
        model.addAttribute("msg","Hello, SpringMVCAnnotation");

        //返回的字符串就是视图（jsp）的名字。hello会被视图解析器处理
        return "hello";
    }
}
