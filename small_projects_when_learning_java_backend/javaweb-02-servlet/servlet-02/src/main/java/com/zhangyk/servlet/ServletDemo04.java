package com.zhangyk.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletDemo04 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("进入了sd4");

        ServletContext context = this.getServletContext();

        // 转发的请求路径。/代表当前项目，gp代表请求gp servlet,
        //RequestDispatcher requestDispatcher = context.getRequestDispatcher("/gp");
        //调用forward实现请求转发。forward参数的原理：因为都是从req接收请求处理，从resp响应回去，requestDispatcher.forward只是做了个转发的作用
        //requestDispatcher.forward(req, resp);
        //上面两句话可以合并成一句，如下
        context.getRequestDispatcher("/gp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}