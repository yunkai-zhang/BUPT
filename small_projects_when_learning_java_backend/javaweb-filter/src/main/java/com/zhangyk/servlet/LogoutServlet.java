package com.zhangyk.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*注销的第一直觉是销毁session，毁了后session及sessionid肯定不存在了。不过频繁的创建session消耗资源，我们还是去
        移除session中的USER_SESSION参数为好*/
        Object user_session = req.getSession().getAttribute("USER_SESSION");

        if(user_session!=null){
            req.getSession().removeAttribute("USER_SESSION");
            //移除成功后，重定向到登录页面
            resp.sendRedirect("/Login.jsp");
        }else{
            //为了程序健壮，为空也要跳转到登录页面。同时思考一个问题：防止注销后还能访问success.jsp页面
            resp.sendRedirect("/Login.jsp");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
