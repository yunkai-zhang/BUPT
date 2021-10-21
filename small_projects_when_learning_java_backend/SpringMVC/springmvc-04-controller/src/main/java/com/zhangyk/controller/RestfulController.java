package com.zhangyk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RestfulController {

    /*
    * 原来的给参访问方式： http://localhost:8080/springmvc_04_controller_war_exploded/add?a=2&b=3
    * restful风格下： http://localhost:8080/springmvc_04_controller_war_exploded/add/a的值/b的值
    * testful下，注意传入a和b的类型要和方法中定义的类型一致，否则会报400错误
    *
    * @RequestMapping的value和path属性互为别名，用法相同
    *
    * 使用Post方法访问add/a/b会使用该方法
    * */
    @RequestMapping(value="/add/{a}/{b}", method = RequestMethod.POST)
    public String test1(@PathVariable int a, @PathVariable int b, Model model){

        model.addAttribute("msg","使用Post结果为"+(a+b));
        return "hello";
    }

    /*
    * @GetMapping(value="/add/{a}/{b}")等效于@RequestMapping(value="/add/{a}/{b}", method = RequestMethod.GET)
    * 但是GetMapping更简洁，一般用这种方法。
    *
    * 使用Get方法访问add/a/b会使用该方法
    *
    * 浏览器访问URL默认使用的就是Get方法
    * */
    @GetMapping(value="/add/{a}/{b}")
    public String test2(@PathVariable int a, @PathVariable int b, Model model){

        model.addAttribute("msg","使用Get结果为"+(a+b));
        return "hello";
    }


}
