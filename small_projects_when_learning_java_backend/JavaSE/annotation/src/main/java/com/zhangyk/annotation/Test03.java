package com.zhangyk.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//自定义注解
public class Test03 {
    //如果没有默认值，我们就必须给注解显式赋值
    @MyAnnotation2(name="张云",schools = {"北邮","港中文"})
    public void test(){}

    @MyAnnotation3("")
    public void test2(){}

}

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation2{
    /*
    注解的参数：参数类型+参数名+（）
    因为是在自定义注解里，所以这不是一个方法
    */
    String name() default "";
    int age() default 0;
    //如果默认值为-1，代表不存在；异曲同工：indexof，如果找不到就返回-1
    int id() default -1;
    String[] schools();
}

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation3{
    /*
    如果只有一个参数，且参数名为value；在注解使用时可以不写"value="
    但如果参数名不是value，那么即使只有一个参数，也要显式写出
    */
    String value();
}