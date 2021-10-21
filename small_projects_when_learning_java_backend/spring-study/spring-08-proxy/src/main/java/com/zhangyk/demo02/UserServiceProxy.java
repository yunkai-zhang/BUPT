package com.zhangyk.demo02;

/*
* 如果直接在UserServiceImpl中添加功能，如日志，会修改UserServiceImpl源码。为了避免修改源码，我们使用代理模式
* 建立当前UserServiceProxy类，来做一些在UserServiceImpl不做的附加工作
* */
public class UserServiceProxy implements UserService {

    private UserServiceImpl userService;

    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    public void add() {
        log("add");
        userService.add();
    }

    public void delete() {
        log("delete");
        userService.delete();
    }

    public void update() {
        log("update");
        userService.update();
    }

    public void query() {
        log("query");
        userService.query();
    }

    //日志方法
    public void log(String msg){
        System.out.println("[Debug]使用了"+msg+"方法");
    }
}
