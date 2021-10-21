package com.zhangyk.service;

import com.zhangyk.dao.UserDaoImpl;
import com.zhangyk.dao.UserDaoMysqlImpl;

public class UserServiceImplTest {

    public static void main(String[] args) {
        //用户实际调用的是业务层；Dao层他们不需要接触
        UserServiceImpl userService = new UserServiceImpl();

        /*
        * 想用什么UserDao的实现，让用户选择实现就可以了
        * */
        //userService.setUserDao(new UserDaoMysqlImpl());
        userService.setUserDao(new UserDaoImpl());

        //业务层此处的作用就是去调用DAO层，相当于通过业务层调DAO层，解耦了
        userService.getUser();
    }
}
