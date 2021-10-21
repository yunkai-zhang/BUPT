package com.zhangyk.demo04;

public class Client {

    public static void main(String[] args) {
        //真实角色
        UserServiceImpl userService = new UserServiceImpl();

        //代理角色，不存在
        ProxyInvocationHandler proxyInvocationHandler = new ProxyInvocationHandler();
        /*设置要代理的对象
        *
        * 比喻来说，就是是你找中介谈没错，但是钥匙在房东受理。所以要去找到真实角色，拿到钥匙
        * */
        proxyInvocationHandler.setTarget(userService);
        /*
        * 动态生成代理类。要强转为真实角色所实现的“某一”接口
        *
        * 真实角色可能实现很多接口，通过ProxyInvocationHandler类中的getInterfaces可知。但其实真实开发中，
        * 某一动态代理类一般只实现一个接口，即只代理“实现某一个相同接口的类们”
        * */
        UserService proxy = (UserService) proxyInvocationHandler.getProxy();

        proxy.add();
    }
}
