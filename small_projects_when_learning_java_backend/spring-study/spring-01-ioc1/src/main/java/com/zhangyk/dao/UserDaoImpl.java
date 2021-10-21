package com.zhangyk.dao;
/*
* Dao是和数据库交互，提供数据结构；Service接口是提供操作接口，和Servlet交互的
* */
public class UserDaoImpl implements UserDao{


    public void getUser() {
        System.out.println("默认获取用户的数据");
    }
}
