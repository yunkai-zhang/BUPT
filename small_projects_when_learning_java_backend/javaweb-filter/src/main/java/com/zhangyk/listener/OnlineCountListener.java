package com.zhangyk.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

//统计网站在线人数:统计session
public class OnlineCountListener implements HttpSessionListener{

    //创建session监听：看你的一举一动
    //一旦创建一个session，就会触发一次这个事件
    public void sessionCreated(HttpSessionEvent se) {
        //要统计所有人，就要拿到最高的作用域：servletcontext
        ServletContext ctx = se.getSession().getServletContext();
        //由于ctx.getAttribute返回的是object类，只能强转为Integer包装类，不能直接转为int基本数据类型
        Integer onlineCount = (Integer) ctx.getAttribute("onlineCount");

        //如果没有计数，则设置为1。如果有计数，则加一。
        if (onlineCount==null){
            onlineCount = new Integer(1);
        }else{
            int count = onlineCount.intValue();
            onlineCount = new Integer(count+1);
        }

        ctx.setAttribute("onlineCount",onlineCount);

//        显示的时候，可能会显示多个id，这是因为tomcat启动的时候可能会失败重试，导致多个sessionid记录，但是f12看浏览器记录，
//        只有一个sessionid，说明前两个已经被销毁了，所以我们redeloy tomcat即可
        System.out.println(se.getSession().getId());
    }

    //销毁session监听
    //一旦销毁一个session，就会触发一次这个事件
    public void sessionDestroyed(HttpSessionEvent se) {

        //要统计所有人，就要拿到最高的作用域：servletcontext
        ServletContext ctx = se.getSession().getServletContext();
        //由于ctx.getAttribute返回的是object类，只能强转为Integer包装类，不能直接转为int基本数据类型
        Integer onlineCount = (Integer) ctx.getAttribute("onlineCount");

        //和create反过来
        if (onlineCount==null){
            onlineCount = new Integer(0);
        }else{
            int count = onlineCount.intValue();
            onlineCount = new Integer(count-1);
        }

        ctx.setAttribute("onlineCount",onlineCount);

    }

    /*
    * session销毁的两种手段
    * 1. 手动销毁: se.getSession().invalidate();
    * 2. 自动销毁: web.xml中配置session-config的session-timeout
    *
    * */
}
