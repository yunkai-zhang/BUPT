package com.zhangyk.servlet;

import com.zhangyk.pojo.Person;
import org.apache.jasper.runtime.HttpJspBase;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class SessionDemo01 extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //解决乱码问题
        //response.setCharacterEncoding和response.setContentType区别:https://blog.csdn.net/csj50/article/details/86713867
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        //得到session
        HttpSession session = req.getSession();

        //给session中存东西
        session.setAttribute("name",new Person("章昀恺",22));

        //获取session的id
        String id = session.getId();

        //判断session是不是新创建的
        if(session.isNew()){
            resp.getWriter().write("session创建成功，ID："+id);
        }else{
            resp.getWriter().write("session已经在服务器中存在了，ID："+id);
        }

        //session创建的时候做了什么事情
        /*本质应该是做下面的事，不然我们不会在cookie中看到session*/
//        Cookie cookie = new Cookie("JSESSIONID", id);
//        resp.addCookie(cookie);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
