package com.zhangyk.filter;

import javax.servlet.*;
import java.io.IOException;

public class CharacterEncodingFilter implements Filter {
    //初始化：web服务器启动，就已经初始化了，随时等待过滤对象出现！
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("CharacterEncodingFilter初始化");

    }

    //chain:链
    /*
    * 1. 过滤中的所有代码，在过滤特定请求的时候都会执行
    * 2. 必须让过滤器继续通行
    *
    * */
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("utf-8");
        servletResponse.setCharacterEncoding("utf-8");
        servletResponse.setContentType("text/html;charset=utf-8");

        System.out.println("CharacterEncodingFilter执行前");
        //filterChain对象本身只有doFilter一个方法，必须写死，让请求继续通行
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("CharacterEncodingFilter执行后");

    }

    //销毁：web服务器关闭的时候，过滤会销毁
    public void destroy() {
        System.out.println("CharacterEncodingFilter销毁");

    }
}
