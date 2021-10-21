package com.zhangyun.swaggerdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

//@Configuration等价与@Component。加了这个注解，类中的配置就会集成到springboot的配置中。
@Configuration
//开启Swagger2
@EnableSwagger2
public class SwaggerConfig {

    /*
    * 配置不同的docket，由不同的groupname区分,由不同的人负责，可以在swagger首页右上角选择group进入对应的swagger页面
    * */
    @Bean
    public Docket docket1(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("A");
    }
    @Bean
    public Docket docket2(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("B");
    }
    @Bean
    public Docket docket3(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("C");
    }


    /*
    * 把Swagger的Docket的bean注册到spring容器中：
    * 1. 注册一个bean首先要@bean注解;
    * 2. 想要注册Docket对象的实例，函数的返回值就得是Docket
    * 3. bean的名字为函数名docket
    *
    * Docket类中有很多参数，都是可以配置的，比如apiInfo。
    * */
    @Bean
    public Docket docket(Environment environment){

        /*
        * 实现swagger在不同环境中开启与否的功能
        * */
        //设置“swagger生效的环境”
        Profiles profiles = Profiles.of("dev", "test");
        //这个flag标记当前环境是不是在配置的“swagger生效的环境”中，如果在，apiInfo().enable(flag)会启用swagger。
        boolean flag = environment.acceptsProfiles(profiles);

        //对swagger2来说，下面两行是写死的
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //swagger首页右上角的分组，不同Docket设置为不同的组，由不同的人负责
                .groupName("张云group1")
                //enable():是否启用swagger，如果为false则swagger不能在浏览器中访问
                .enable(flag)
                //select()apis()paths()build()是一套
                .select()
                /*
                * RequestHandlerSelectors:配置要扫描接口的方式
                *   basePackage:指定要扫描的包
                *   any():扫描全部
                *   none():不扫描
                *   withClassAnnotation：扫描类上的注解，参数是一个注解的反射对象
                *   withMethodAnnotation:扫描方法上的注解
                * */
                .apis(RequestHandlerSelectors.basePackage("com.zhangyun.swaggerdemo.controller"))
                //paths():过滤处理什么路径：由于/zhangyun路径和basePackage指定的包没有重合部分，所以不会有任何接口被扫描。
                //.paths(PathSelectors.ant("/zhangyun/**"))
                .build();//build体现工厂模式
    }

    /*
    * 配置swagger信息中的apiInfo
    *
    * 下面函数不是一个bean，是真正的方法，用来给docket()bean注册中的Docket.apiInfo反参数的
    * */
    private ApiInfo apiInfo(){
        //new apiinfo的时候需要提供default_contact（作者信息），在这里创建
        Contact contact = new Contact("张云", "https://www.baidu.com/", "10000@qq.com");

        //去ApiInfo看源码，可以看到最后有apiinfo的构造函数默认的填充方法，复制到这返回一个默认的Apiinfo的bean
        return new ApiInfo(
                //swagger首页的title，可以自定义
                "张云的swaggerapi文档",
                //swagger首页的描述，可以自定义
                "这是张云写的描述：学学学",
                "1.0",
                //swagger首页可以点的url。一定要有http，否则无法正确导向页面。
                "https://www.baidu.com/",
                //存入预先定义好的contact
                contact,
                //license不用变
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                //默认要填的，是空的
                new ArrayList()
        );
    }
}
