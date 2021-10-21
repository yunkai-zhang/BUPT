package com.zhangyk.config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor implements HandlerInterceptor {
    //虽然重写方法不是必须的，但是为了实现功能，我们还是要重写

    /*
    * return true; 执行下一个拦截器，放行。
    * return false;不执行下一个拦截器，不放行，进程卡在拦截器不动，不会继续走向控制器等。
    *
    * 一般写处理前即可
    * */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("============处理前============");

        return true;
    }

    //追加拦截日志。实战不一定用
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("============处理后============");
    }

    //清理。实战不一定用
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("============清理============");

    }
}
