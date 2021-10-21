package com.zhangyk.demo02;

/*
* 不改变原有业务的情况下，想要去扩展一些功能，代理模式就很好。
* */
public class Client {

    public static void main(String[] args) {
        //真实角色userService只做增删改查，源码不变
        UserServiceImpl userService = new UserServiceImpl();

        //添加日志的功能，通过代理userServiceProxy实现
        UserServiceProxy userServiceProxy = new UserServiceProxy();
        userServiceProxy.setUserService(userService);

        userServiceProxy.add();
        userServiceProxy.delete();
    }
}
