package com.zhangyk.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*
        * 放行：判断什么情况下登录:有session就证明登录了
        * */
        //服务器上有session，说明已登录，放行
        HttpSession session = request.getSession();
        if(session.getAttribute("userLoginInfo")!=null){
            return true;
        }
        //登录页面也得放行，不然用户无法输入账号密码;getRequestURI()会提取出request中的url请求。第一次登录的/login也得放行
        //！！！注意contains区分大小写
        if(request.getRequestURI().contains("login")||request.getRequestURI().contains("goLogin")){
            return true;
        }


        /*
        * 判断什么时候没有登录
        * */
        //除了上面放行的情况，其他的都没有登录，直接把请求"转发"到登录页面：login.jsp
        //比如：未登录状态，点击“首页”，就会自动跳转到登录页面
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request,response);



        return false;
    }
}
