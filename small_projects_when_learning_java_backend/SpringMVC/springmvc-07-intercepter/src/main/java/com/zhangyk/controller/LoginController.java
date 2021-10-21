package com.zhangyk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class LoginController {

    //main请求做的是就是，直接跳往main.jsp视图
    @RequestMapping("/main")
    public String main(){
        return "main";
    }

    //如果请求为goLogin，说明就是想去登录视图，直接跳往登录视图即可
    @RequestMapping("/goLogin")
    public String login(){
        return "login";
    }

    //login请求，处理登录页面的表单提交的数据
    @RequestMapping("/login")
    //验证用户名密码最好写一个DaoPojo，里面包含username和password两个字段。这里为了方便，直接验证。
    public String login(HttpSession session, String username, String password, Model model){
        //把用户信息存在session中
        session.setAttribute("userLoginInfo",username);

        //把用户信息传输到前端
        model.addAttribute("username",username);

        //类上用的是@Controller，这里不是返回字符串，而是跳转到相应视图
        return "main";
    }

    //注销
    @RequestMapping("/logout")
    //验证用户名密码最好写一个DaoPojo，里面包含username和password两个字段。这里为了方便，直接验证。
    public String logout(HttpSession session){
        /*把session中的userLoginInfo属性移除。
        *
        * 其实注销session也能实现相同功能，但是用户还没关闭浏览器，浏览器关闭才会产生另外一个session。不断销毁创建session，
        * 服务器可能顶不住
        * */
        session.removeAttribute("userLoginInfo");

        /*
        * 类上用的是@Controller，这里不是返回字符串，而是跳转到相应视图。
        *
        * 拦截器只能拦截请求，跳转main.jsp视图是无法拦截的，所以登出的时候要按两次。第一次/logout移除了"userLoginInfo"属性
        * 相当于登出，此时页面跳转到main界面，且main界面的username为空；第二次点击注销从而请求/logout时，
        * 由于/logout请求不是"/login"或"goLogin",并且不在登录状态，所以不会跳转到logout(),而会被拦截器LoginInterceptor直接转发到login.jsp
        * */
        return "login";
    }
}
