package com.zhangyk.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class ImageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //如何让浏览器8秒自动刷新一次
        resp.setHeader("refresh","8");

        //在内存中创建一个图片,但是这是空白图片
        BufferedImage image = new BufferedImage(80, 20, BufferedImage.TYPE_INT_RGB);
        //得到图片,g相当于是一只笔
        Graphics2D g = (Graphics2D)image.getGraphics();
        //设计图片的背景颜色
        g.setColor(Color.white);
        g.fillRect(0,0,80,20);
        //给图片写数据
        //给画笔换个颜色，在之前的白色背景上写东西
        g.setColor(Color.blue);
        g.setFont(new Font(null,Font.BOLD,20));
        g.drawString(makeNum(),0,20);

        //告诉浏览器，这个请求用图片的方式打开
        resp.setContentType("image/jpeg");
        //网站存在缓存，不让浏览器缓存。以后我们写代码不用写这些，这都是老年化的操作
        resp.setDateHeader("expires",-1);
        resp.setHeader("Cache-Control","no-cache");
        resp.setHeader("Pragma","no-cache");

        //把图片写给浏览器
        ImageIO.write(image,"jpg",resp.getOutputStream());



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    //生成随机数
    private String makeNum()
    {
        Random random = new Random();
        String num = random.nextInt(9999999) + "";
        //string和stringbuffer区别：https://www.cnblogs.com/liumaowu/p/8554328.html
        StringBuffer sb = new StringBuffer();

        //随机数num不足7位的部分，在开头补0。老手这么保证7位
        for(int i=0;i<7-num.length();i++)
        {
            sb.append("0");
        }
        num = sb.toString()+ num;

        return num;
    }
}
