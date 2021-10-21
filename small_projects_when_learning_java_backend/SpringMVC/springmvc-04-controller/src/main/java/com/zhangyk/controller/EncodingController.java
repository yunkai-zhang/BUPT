package com.zhangyk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EncodingController {

    //过滤器解决乱码

    //由于表单的提交方式是Post，这里得用postmapping去处理url路径，否则url请求映射不到该方法。
    @PostMapping("/e/t1")
    public String test1(String name, Model model){
        //form表单提交的name被存入model，model被反给前端hello.jsp显示
        model.addAttribute("msg","form表单提交的是："+name);
        return "hello";
    }
}
