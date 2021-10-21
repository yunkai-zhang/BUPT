package com.zhangyk.demo04;


import com.zhangyk.demo03.Rent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//等会会用这个类自动生成代理类，而不是像demo1中写死代理类
public class ProxyInvocationHandler implements InvocationHandler {

    //实现了被代理的接口的某类的对象。之前demo3写死为rent，这里写成Object（活的）
    private Object target;
    //对象通过public set设置。其实该set方法可以浓缩到getProxy()中
    public void setTarget(Object target){
        this.target=target;
    }

    //生成得到代理对象
    public Object getProxy(){
        /*
        * 下面这行代码是死的
        * */
        return Proxy.newProxyInstance(
                //代理类入口
                this.getClass().getClassLoader(),
                //被代理的类（userService）的接口
                target.getClass().getInterfaces(),
                //如何代理实现的方法的位置
                this);
    }

    //处理代理实例proxy调用的方法，并返回结果，此结果没有明显用处
    /*
    * invoke是jvm调的
    *
    * 代理实例proxy调用了任何方法，都会通过该强制实现的invoke方法来反射实现那些方法
    * */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //！！！！不改变UserServiceImpl的前提下，AOP，利用动态代理横向增加日志功能
        log(method.getName());

        /*
        * 用反射来执行方法,之前反射讲过，经典
        *
        * 动态代理的本质，就是使用反射机制实现
        *
        * 这里的method对象是proxy对象调用的方法的方法对象；本例子中是add方法（可见于Client类的第24行）
        *
        * 这里的target是实现了target接口的某类的一个对象；本例子中target为userService对象（Client类中用set赋值的）。
        * 由于Client类中，代理proxy调用了add方法，这里method便是add方法的方法类，
        * add方法的方法类“method”，通过invoke，作用于实现了UserService接口的对象userService，
        * 而该target对象是userService对象，所以此行invoke本质是执行userService对象中的add方法。
        * */
        Object result = method.invoke(target, args);
        return result;
    }

    public void log(String msg){
        System.out.println("执行了"+msg+"方法");
    }

}
