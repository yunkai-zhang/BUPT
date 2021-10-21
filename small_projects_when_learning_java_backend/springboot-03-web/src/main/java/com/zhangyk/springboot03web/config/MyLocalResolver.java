package com.zhangyk.springboot03web.config;

import org.springframework.web.servlet.LocaleResolver;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class MyLocalResolver implements LocaleResolver {
//    public MyLocalResolver() {
//        super();
//    }

    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {

        //获取请求中的请求参数
        String l = httpServletRequest.getParameter("l");

        //如果前端没有传递使用哪种语言，使用默认的locale并返回
        Locale locale = Locale.getDefault();

        //如果请求的链接携带了国际化的参数
        if(!StringUtils.isEmpty(l)){
            //把国际化参数分割并储存，如zh_CN切割为zh和CN
            String[] s = l.split("_");
            //覆盖掉默认的locale
            locale = new Locale(s[0], s[1]);
        }

        System.out.println("locale:"+locale);

        return locale;

    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
