package com.zhangyk.controller;

import org.springframework.web.servlet.ModelAndView;
//web mvc下的接口，要找对
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//实现Controller接口或者应用了Controller注解的就是controller
public class HelloController implements Controller {
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        //该行写死：提供mv
        ModelAndView modelAndView = new ModelAndView();

        //业务代码
        String result= "helloSpringMVC";
        modelAndView.addObject("msg",result);

        //视图跳转:设置视图的名字即可
        modelAndView.setViewName("show");

        //该行写死：提供mv
        return modelAndView;
    }
}
