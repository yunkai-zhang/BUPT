package com.zhangyk.diy;

/*方式三：使用注解方式实现AOP
*
* 用注解定义了切面，切入点，通知。所以这些定义在Spring配置文件中就不用做了
* */

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

//标注这个类是一个切面
@Aspect
public class AnnotationPointCut {

    // @Before表示在切入点的前面执行“通知”，注解的括号中填写切入点
    @Before("execution(* com.zhangyk.service.UserServiceImpl.*(..))")
    public void before(){
        System.out.println("===方法执行前===");
    }

    // @After表示在切入点的后面执行“通知”，注解的括号中填写切入点
    @After("execution(* com.zhangyk.service.UserServiceImpl.*(..))")
    public void after(){
        System.out.println("===方法执行后===");
    }

    /*使用环绕增强
    *
    * 在环绕增强中，我们就可以给定一个参数，代表我们要获取处理切入的点：ProceedingJoinPoint jp 这个是写死的
    *
    * 用around方法，可以拿到原方法的参数等，做各种校验，判断redis锁存在不存在
    * */
    @Around("execution(* com.zhangyk.service.UserServiceImpl.*(..))")
    public void around(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("环绕前");

        //执行方法
        Object proceed = jp.proceed();
        System.out.println(">>>>Signature:"+jp.getSignature());
        System.out.println(">>>>proceed:"+proceed);

        System.out.println("环绕后");

    }
}
