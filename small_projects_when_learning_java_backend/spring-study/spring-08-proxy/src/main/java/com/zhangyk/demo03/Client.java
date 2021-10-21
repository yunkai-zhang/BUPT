package com.zhangyk.demo03;

public class Client {

    public static void main(String[] args) {
        //真实角色
        Host host = new Host();

        //代理角色：现在没有。要通过“程序处理角色：ProxyInvocationHandler”生成一个代理类
        ProxyInvocationHandler proxyInvocationHandler = new ProxyInvocationHandler();
        //通过调用“程序处理角色：ProxyInvocationHandler”的set方法，来设置“实现指定接口的对象”（本例中为host）
        proxyInvocationHandler.setRent(host);
        //生成代理角色
        /*
        * proxy是动态生成的实现了Rent接口的对象，他的rent方法通过ProxyInvocationHandler类中的invoke实现
        *
        * 返回的代理对象，由于ProxyInvocationHandler类中newProxyInstance方法中的限定，只能代理Rent接口
        * */
        Rent proxy = (Rent) proxyInvocationHandler.getProxy();

        //调用代理角色的方法
        proxy.rent();

    }
}
