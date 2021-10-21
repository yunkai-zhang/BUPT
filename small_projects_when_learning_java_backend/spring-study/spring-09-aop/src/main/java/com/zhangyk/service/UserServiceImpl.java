package com.zhangyk.service;

public class UserServiceImpl implements UserService{

    public void add(){
        System.out.println("增加了一个用户");
    }
    public void delete(){
        System.out.println("删除了一个用户");
    }
    public void update(){
        System.out.println("更新了一个用户");
    }
    public void select(){
        System.out.println("选择了一个用户");
    }
}
