package com.zhangyk.springboot03web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
* 如果我们要扩展springmvc，官方建议我们这样去做！即自己写各种config类
* */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    /*
    * 根据请求，配置每个请求对应跳转的视图
    * */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
        //把一个视图解析器的请求，转变成另一个。表面上看像请求main.html，但其实请求的是dashboard页面
        registry.addViewController("/main.html").setViewName("dashboard");
    }

    //注册 MyLocalResolver工具类
    //一定不要忘记@bean注解，否则没注册进去
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocalResolver();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自己写的拦截器
        registry.addInterceptor(new LoginHandlerInterceptor()).
                //会拦截处理所有请求（除了下面exclude指定的）
                addPathPatterns("/**").
                //不拦截 “登录界面”，“首页”，“登录请求”。还不应拦截对静态资源如css的访问,拦截的话页面会变形。
                excludePathPatterns("/index.html","/","/user/login","/css/**","/js/**","/img/**");
    }
}


