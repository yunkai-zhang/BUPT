package com.zhangyun.swaggerdemo.controller;

import com.zhangyun.swaggerdemo.pojo.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//没有前端页面，所以用restcontroller直接给浏览器返回字符串
@RestController
public class HelloController {

    /*
    * 这是controller中一个普通的请求处理和转发函数
    * */
    @RequestMapping(value = "/hello")
    public String hello(){
        return "hello";
    }

    /*
    * 这是一个接口
    *
    * 只要我们的接口中，返回值存在实体类，该实体类就会被扫描到swagger中
    * */
    @PostMapping(value = "/user")
    public User user(){
        return new User();
    }

    //@ApiOperation接口是放在方法上的，不是放在类上的
    @ApiOperation("hello控制类")
    @GetMapping( value = "/hello2")
    //@ApiParam给请求函数的参数添加注释
    public String hello2(@ApiParam("用户名") String username){
        return "hello"+username;
    }
}
