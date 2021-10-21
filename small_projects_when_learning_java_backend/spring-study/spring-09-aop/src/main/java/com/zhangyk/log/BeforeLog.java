package com.zhangyk.log;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/*
* MethodBeforeAdvice的parent是BeforeAdvice，MethodBeforeAdvic的出现应该是出于一些扩展性需求的设计
*
* 本BeforeLog类是方法执行前使用的日志
* */
public class BeforeLog implements MethodBeforeAdvice {
    /*
    method:要执行的目标对象的方法 的方法对象
    objects：参数，其实命名为args更合理
    o：目标对象，其实命名为target更合理

     参数命名不规范的问题：可以下载源码来读。点MethodBeforeAdvice接口，代码区的上沿会有“下载源码”的
     标识，点击下载后读的源码都是规范命名的
    */
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println(o.getClass().getName()+"类的对象的"+method.getName()+"方法被执行了");

    }
}
