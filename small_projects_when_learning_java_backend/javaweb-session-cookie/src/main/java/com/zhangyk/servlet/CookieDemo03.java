package com.zhangyk.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

//中文数据传递
public class CookieDemo03 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //服务器，告诉你，你来的时间，把这个时间封装成一个 信件，你下次带来，我就知道你来了

        //解决中文乱码，请求和响应都要解决
        req.setCharacterEncoding("utf-8");
        //下面两行可以合并：resp.setContentType("text/html;charset=utf-8");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");

        PrintWriter out = resp.getWriter();

        //cookie，服务器端从客户端获取
        Cookie[] cookies = req.getCookies();//这里返回数组，说明cookie可能存在多个

        //判断cookie是否存在。先遍历cookie找不到lastlogintime再打印第一次登录页比较好，因为有些浏览器哪怕第一次访问也有cookie
        if(cookies!=null)
        {
            //如果存在怎么办
            out.write("你上次访问的时间是：");

            //遍历每个cookie，直到找到用来存储lastLoginTime的cookie
            for(int i=0; i<cookies.length;i++)
            {
                Cookie cookie = cookies[i];
                //获取cookie的名字
                if(cookie.getName().equals("name"))
                {
                    //获取cookie的值
                    //未来出现乱码可以用encode1和decode1的组合处理：System.out.println(URLDecoder.decode( cookie.getValue(),"utf-8"));
                    System.out.println(cookie.getValue());
                    out.write(cookie.getValue());
                }
            }

        }
        else
        {
            out.write("这是您第一次访问本站");
        }

        //未来出现乱码可以用encode1和decode1的组合处理：Cookie cookie = new Cookie("name", URLEncoder.encode("章昀恺","utf-8"));
        Cookie cookie = new Cookie("name", "章昀恺");
        resp.addCookie(cookie);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
