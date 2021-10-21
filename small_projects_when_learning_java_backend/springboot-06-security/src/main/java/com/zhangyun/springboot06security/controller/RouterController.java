package com.zhangyun.springboot06security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RouterController {

    @GetMapping({"/","/index"})
    public String index(){
        //也可以写成 return "index.html";
        return "index";
    }

    @GetMapping("/toLogin")
    public String toLogin(){
        return "views/login";
    }

    //使用restful风格传递参数，并用参数控制访问不同的页面
    @GetMapping("/level1/{id}")
    public String level1(@PathVariable("id") int id){
        return "views/level1/"+id;
    }

    @GetMapping("/level2/{id}")
    public String level2(@PathVariable("id") int id){
        return "views/level2/"+id;
    }

    @GetMapping("/level3/{id}")
    public String level3(@PathVariable("id") int id){
        return "views/level3/"+id;
    }

}
