package com.kuang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    //@RequestMapping(value = "/user/login",method = RequestMethod.POST)
    @PostMapping("/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model, HttpSession session){

        if (!StringUtils.isEmpty(username) && "123456".equals(password)){
            //登录成功！将用户信息放入session
            session.setAttribute("loginUser",username);
            //登录成功！防止表单重复提交，我们重定向
            return "redirect:/main.html";
        }else {
            //登录失败！存放错误信息
            model.addAttribute("msg","用户名密码错误");
            return "index";
        }

    }

    @GetMapping("/user/loginOut")
    public String loginOut(HttpSession session){
        session.invalidate();
        return "redirect:/index.html";
    }

}