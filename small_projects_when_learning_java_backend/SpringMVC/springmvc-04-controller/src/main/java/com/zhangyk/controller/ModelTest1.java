package com.zhangyk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ModelTest1 {

//    @RequestMapping("/m1/t1")
//    public String test(HttpServletRequest request, HttpServletResponse response){
//        System.out.println(request.getSession().getId());
//
//        return "hello";
//
//    }

    @RequestMapping("/m1/t1")
    public String test1(Model model){
        //转发
        model.addAttribute("msg","forward：ModelTest1");

        /*下面等同于：显示的使用forward前缀：return "forward：/WEB-INF/jsp/hello.jsp";
        *
        * 不需要视图解析器的话，需要写全路径。虽然功能也能实现，但是不推荐这样。推荐在SpringMVC中配置视图解析器，并
        * 配置前缀后缀，这样更规范。
        * */
        return "/WEB-INF/jsp/hello.jsp";
    }

    //重定向
    @RequestMapping("/m1/t2")
    public String test2(Model model){
        /*
        * Model 对象负责在控制器和展现数据的视图之间传递数据。
        * 实际上，放到 Model 属性中的数据将会复制到 Servlet Response 的属性中，这样视图就能在这里找到它们了。
        * 从广义上来说，Model 指的是 MVC 中的 M，即 Model(模型)。从狭义上讲，Model 就是个 key-value 集合。
        * 来源：https://www.cnblogs.com/youcoding/p/13763621.html
        * */
        model.addAttribute("msg","redirect：ModelTest1");

        /*jsp文件夹在web-inf下，是隐秘的，重定向访问不到的（转发可以），会报404。
        *
        * 如果忘记斜杠，写成 return "redirect:index.jsp"; 会重定向到http://localhost:8080/m1/index.jsp?msg=ModelTest1
        * 这就成了以m1为基础重定向，就错了。
        *
        * 如果开启了视图解析器，也不会拼接；因为转发才会触发视图解析器拼接，重定向不会触发（已自己开启视图验证了）。
        * 开启视图时，默认就是转发；如果想重定向，需要在return处显式得加上redirect
        * */
        return "redirect:/index.jsp";
    }
}
