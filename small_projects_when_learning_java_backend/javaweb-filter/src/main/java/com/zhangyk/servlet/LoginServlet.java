package com.zhangyk.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取前端请求的参数
        String username= req.getParameter("username");

        if("admin".equals(username)){//登陆成功
            //登陆成功后，用户信息一般放到session。这样可以在多个servlet中取到
            req.getSession().setAttribute("USER_SESSION",req.getSession().getId());
            //响应一个重定向，把页面转到登陆成功的页面
            resp.sendRedirect("/sys/success.jsp");

        }else{//登陆失败
            resp.sendRedirect("/error.jsp");

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
