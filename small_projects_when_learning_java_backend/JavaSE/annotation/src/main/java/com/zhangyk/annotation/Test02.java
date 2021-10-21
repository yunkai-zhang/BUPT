package com.zhangyk.annotation;

import java.lang.annotation.*;

//测试元注解
@MyAnnotation
public class Test02 {
    @MyAnnotation
    public void test(){

    }
}

//定义一个注解
//target表示我们的注解可以用在哪些地方
@Target( value= {ElementType.METHOD,ElementType.TYPE})
//retention表示我们的注解在什么地方还有效
//source（源代码级）<class（编译后的class级）<**runtime（运行时）
@Retention(value = RetentionPolicy.RUNTIME)
//表示是否将我们的注解生成在javaDOC中
@Documented
//子类可以继承父类的注解
@Inherited
@interface MyAnnotation{

}