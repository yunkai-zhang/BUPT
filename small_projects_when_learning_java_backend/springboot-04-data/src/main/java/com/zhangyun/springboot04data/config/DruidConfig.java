package com.zhangyun.springboot04data.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

    /*要把yaml配置的druid属性，注入到容器中。而要进行注入首先要注册bean，再加上@ConfigurationProperties注解表示绑定成功
    * */
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }

    //后台sql监控：相当于web.xml。因为springboot内置了servlet容器，所以没有web.xml，替代方法就是用ServletRegistrationBean
    //配置类中的方法，不要忘记放到bean里
    @Bean
    public ServletRegistrationBean statViewServlet(){
        /*
        * 这行写死的，只要访问/druid/**就会来到监控页面。监控页面是alibaba写好的，可以拿来就用。
        * */
        ServletRegistrationBean<StatViewServlet> statViewServletServletRegistrationBean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");

        //后台需要有人登录，要配置账号密码。登录的key是固定的：loginUsername loginPassword
        HashMap<String,String> initParameters = new HashMap<>();
        initParameters.put("loginUsername","admin");
        initParameters.put("loginPassword","123456");

        //允许谁可以访问。value为空字符串时表示所有人都可访问
        initParameters.put("allow","");

        //禁止谁能访问。这个不是重点。 initParameters.put("zhangyun","192.168.11.123");

        //输入参数的名字，是看源码函数的接收变量也叫initParameters
        statViewServletServletRegistrationBean.setInitParameters(initParameters);
        return statViewServletServletRegistrationBean;

    }

    //filter
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean() ;
        bean.setFilter(new WebStatFilter()) ;
        //可以过滤哪些请求呢?
        Map<String,String> initParameters = new HashMap<>();
        //这些东西不进行统计~
        initParameters.put( "exclusions","*.js,*.css,/druid/*");
        bean. setInitParameters( initParameters) ;
        return bean;
    }
    }
