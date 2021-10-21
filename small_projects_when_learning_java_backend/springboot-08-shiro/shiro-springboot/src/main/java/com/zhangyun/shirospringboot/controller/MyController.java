package com.zhangyun.shirospringboot.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

    @RequestMapping({"/","/index"})
    public String toIndex(Model model){
        model.addAttribute("msg","hello shiro");
        return "index";
    }

    @RequestMapping("/user/add")
    public String add(){
        return "user/add";
    }

    @RequestMapping("/user/update")
    public String update(){
        return "user/update";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/login")
    //username和password是从前端表单提交的参数名
    public String login(String username,String password,Model model){
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        //参考shiro的Quickstart.java,做如登录等操作
        try{
            //执行登录方法，如果没有异常就说明登录成功,前往首页。
            subject.login(token);
            return "index";
        }
        //用户名不存在
        catch (UnknownAccountException e){
            model.addAttribute("msg","用户名不存在");
            //猜测不带斜杠表示resource下的页面，带斜杠的/login表示请求。登陆失败前往登录页。
            return "login";
        }
        //密码错误
        catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误");
            //登陆失败前往登录页。
            return "login";
        }
    }

    //处理未授权事件发生时的url请求
    @RequestMapping("/noAuth")
    //不走视图解析器，直接在前端展示return的字符串
    @ResponseBody
    public String unauthorized(){
        return "未经授权无法访问此页面";
    }

    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();

        //登出后来到首页
        return "index";
    }

}
