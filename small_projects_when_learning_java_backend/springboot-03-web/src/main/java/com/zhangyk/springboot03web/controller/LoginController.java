package com.zhangyk.springboot03web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    //标记哪个请求会走哪个方法
    @RequestMapping("/user/login")
    //@ResponseBody表示方法返回的是字符串，不走视图解析器。本程序希望跳转到视图，所以要注销掉responsebody
    //@ResponseBody
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model,
                        HttpSession session){

        //业务处理
        if(!StringUtils.isEmpty(username)&&"123456".equals(password)){

            //登录成功的话，为session添加参数，方便拦截器检查
            session.setAttribute("loginUser",username);

            /*账号密码符合要求，去到登录成功页面。页面看上去是访问了main.html,但是实际在MyMvcConfig中配置了mian.html到dashboard的
            映射，所以实际重定向到了dashboard页面*/
            return "redirect:/main.html";
        }else{
            //告诉用户你登录失败了
            model.addAttribute("msg","用户名或密码错误");
            return "index";

        }
    }

    //注销session，使得用户登出
    @RequestMapping("/user/loginOut")
    //后台取出公用的session
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/index.html";
    }
}
