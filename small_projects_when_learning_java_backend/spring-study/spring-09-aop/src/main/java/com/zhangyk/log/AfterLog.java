package com.zhangyk.log;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/*
* 本AfterLog是方法执行后使用的日志
* */
public class AfterLog implements AfterReturningAdvice {

    /*
    * 参数比before多了returnValue：返回值
    *
    * 因为方法执行前是不会有返回值的，但是方法执行后会有
    * */
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {

        System.out.println(target.getClass().getName()+"类的对象的"+method.getName()+"方法被执行了。返回结果为："+returnValue);
    }
}
