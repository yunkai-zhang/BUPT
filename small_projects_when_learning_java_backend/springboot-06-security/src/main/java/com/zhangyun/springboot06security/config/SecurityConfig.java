package com.zhangyun.springboot06security.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//@EnableWebSecurity让类被spring托管。WebSecurityConfigurerAdapter让类成为springsecurity配置类。
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //授权！！
    //alt+insert重写configure(HttpSecurity http)方法。固定。
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //请求授权的规则：首页所有人可以访问，功能页只有对应有权限的人才能访问。
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("vip1")
                .antMatchers("/level2/**").hasRole("vip2")
                .antMatchers("/level3/**").hasRole("vip3");

        /*正常情况下，没有权限应该跳到登录页面，而不是403（permision denied）页面。
        * 配置下面这行代码，没有权限自动跳到登录页（springsecurity自带的登录页）。
        *
        * 用.loginPage()设置“/toLogin”为springsecurity的默认登录页。就可以避免springsecurity那个本身丑陋的登录界面了。
        *
        * loginProcessingUrl定义url请求，该请求让springsecurity接收包含用户名密码的表单。
        *
        * usernameParameter和passwordParameter定义springsecurity想从前端表单接收的用户名和密码栏的name
        * */
        http.formLogin()
                .loginPage("/toLogin")
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password");

        /*使springboot允许以get方式提交/logout
        * */
        http.csrf().disable();

        /*注销,开启了注销功能。注销完跳到首页。
        *
        * logoutUrl定义springsecurity指定何路径为”请求登出“的路径
        *
        * logoutSuccessUrl定义springsecurity指定的登出成功跳转页面
        * */
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/");

        /*开启“记住我”功能，本质cookie，默认保存两周
        *
        * rememberMeParameter定义用于登录的前端表单中什么参数传递rememberme的值。
        * */
        http.rememberMe().rememberMeParameter("rememberMe");
    }

    //认证！！
    /*密码不加密的话，在springboot 2.1.X可以直接使用。但是高版本的springboot强制要求密码加密，防止反编译为class文件造成密码泄露。
    * 密码编码：PasswordEncoder，在springsecurity5.0+中新增了很多的加密方法。BCryptPasswordEncoder是比较推荐的加密方式。
    * */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //可以在数据库或者内存认证，现在没连数据库，就在内存认证:inMemoryAuthentication()。
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                //这些数据正常应该从数据库中读，但是后面讲shiro的时候再从数据库读。
                .withUser("zhangyun").password(new BCryptPasswordEncoder().encode("123456")).roles("vip2","vip3")
                .and()//拼接不同的用户
                .withUser("root").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1","vip2","vip3")
                .and()
                .withUser("guest").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1");
    }
}
