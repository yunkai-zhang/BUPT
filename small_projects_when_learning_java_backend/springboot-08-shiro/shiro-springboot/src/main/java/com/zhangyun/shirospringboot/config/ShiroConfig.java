package com.zhangyun.shirospringboot.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    //第三步：ShiroFilterFactoryBean：对应shiro核心对象之Subject
    /*
    * 之所以他是第三步，是因为shiroFilterFactoryBean需要SecurityManager
    *
    * 不要忘记@Bean
    * */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("getDefaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //需要绑定SecurityManager即设置安全管理器，绑定原理同第二步的setRealm()
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        /*
         * 添加shiro的内置过滤器
         *
         * 以下为不同过滤类型，用以过滤url请求
         * 1. anon:无需认证就能访问
         * 2. authc：必须认证了才能访问
         * 3. user：必须拥有了“记住我”功能才能用。一般不用。
         * 4. perms：拥有对某个资源的权限才能访问
         * 5. roles：拥有某个角色权限才能访问
         *
         * */
        Map<String,String> filterMap=new LinkedHashMap<>();

        /*认证（authc）设置
        *
        * 本例中 /user/add 和 /user/update只有认证了的用户才能访问.
        *
        * 这里支持通配符，改成“/user/*”使user下的所有请求都配置为authc过滤方式.
        * */
        filterMap.put("/user/add","authc");
        filterMap.put("/user/update","authc");

        /*
        * 授权（perms）设置
        *
        * 下面这行表示，对于/user/add请求，用户的权限叫"user:add"才能访问
        * */
        filterMap.put("/user/add","perms[user:add]");
        filterMap.put("/user/update","perms[user:update]");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        //设置url请求路径，前往用于提交登录表单的前端
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        //设置未授权事件发生时，跳往何页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth");


        return shiroFilterFactoryBean;
    }

    //第二步：DefaultWebSecurityManager：对应shiro核心对象之SecurityManager
    /*
    * 之所以他为第二步，是因为securityManager需要Realm
    *
    * 如果我们在某个注入点需要另一个 bean，我们需要专门指出它。我们可以通过 @Qualifier 注解来做到这一点。
    *
    * 配置类中的当前函数不要忘记加上@Bean
    * */
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //需要参数，因为需要关联UserRealm，因为securityManager是一个中间商。从spring中取出bean作为securitymanager的参数并给setRealm。
        securityManager.setRealm(userRealm);

        return securityManager;
    }

    //第一步：创建realm对象：对应shiro核心对象之realm
    /*
    * 类配置spring bean知识回顾：
    * 1. 注册UserRealm所以函数的返回值为UserRealm
    * 2. 函数名userRealm为bean的别名，getbean就会get到userRealm
    * 3. 返回值为new “要在spring注册的类”
    * 4. 不要忘记在函数的上面加上@Bean注解
    * 做完上面四步，自己写的UserRealm类就被spring托管了
    *
    * @Component和@Bean的区别：https://blog.csdn.net/qq_35430000/article/details/108910387
    * */
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

    //整合shirodialect，用来整合shiro-thymeleaf
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
