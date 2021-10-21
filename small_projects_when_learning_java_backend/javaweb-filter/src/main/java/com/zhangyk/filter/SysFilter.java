package com.zhangyk.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SysFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
        

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //这里ServletRequest和HttpServletRequest是父子关系，要强转一下，否则拿不到session
        HttpServletRequest servletRequest1 = (HttpServletRequest) servletRequest;
        HttpServletResponse servletResponse1 = (HttpServletResponse) servletResponse;

        //USER_SESSION在好多地方都用到，最好在util文件夹下建个Constant类，把USER_SESSION设置一下，别处都来调用，这样以后只用修改一处。
        Object user_session = servletRequest1.getSession().getAttribute("USER_SESSION");
        if(user_session==null){
                servletResponse1.sendRedirect("/error.jsp");
        }

        //忘记这句的话，所有经过过滤器的请求都会中断，因为没办法从过滤器走到被请求的servlet
        filterChain.doFilter(servletRequest,servletResponse);

    }

    public void destroy() {

    }
}
