package com.zhangyk.config;

import com.zhangyk.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/*
1. 这个也会Spring容器托管，注册到容器中，因为他本来就是一个@Component（点进注解可以看到）
2. @Configuration代表这是一个配置类，就和我们之前看的applicationContext.xml是一样的
3. @Configuration会启动容器，@Bean会注册bean对象
 */
@Configuration
/*
1. 这里没扫描也能用是因为这个配置类里面已经用@Bean注册了User，不需要pojo类中User的Component导入
2. 你可能会想：扫描component+@configuration同时使用的话，会不会相当于容器中有两个User类的bean对象？
即：一个是通过配置类注册的bean，一个是通过pojo类自带注解注册的bean？
答案是不会的，因为只要加了Coinfiguration默认作用域就为单例模式，同一类型的bean，只会留下一个。
* */
@ComponentScan("com.zhangyk.pojo")
//引入别的配置类，相当于之前xml配置文件中的import标签
@Import(ZhangConfig2.class)
public class ZhangConfig {

    /*
    注册一个bean，就相当于我们之前写的一个bean标签
    这个方法的名字，就相当于bean标签中的id属性
    这个方法的返回值，就相当于bean标签中的class属性
    */
    @Bean
    public User user(){
        //就是返回要注入到bean的对象
        return new User();
    }
}
