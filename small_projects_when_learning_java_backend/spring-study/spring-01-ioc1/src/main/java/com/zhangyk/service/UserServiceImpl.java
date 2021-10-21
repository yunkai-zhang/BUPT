package com.zhangyk.service;

/*
 * Dao是和数据库交互，提供数据结构；Service接口是提供操作接口，和Servlet交互的
 * */

import com.zhangyk.dao.UserDao;
import com.zhangyk.dao.UserDaoImpl;
import com.zhangyk.dao.UserDaoMysqlImpl;

public class UserServiceImpl implements UserService{

    /*
    * 业务层要调DAO层，在java中有个组合的概念（除了继承，就还有个组合）。要用DAO层的东西就把它放过来
    *
    * 组合大于继承
    *
    * 把DAO引到service中；这里是多态的写法
    * */
    //如果想切换UserDao接口的实现类，就得在这改原有代码；每次因为用户请求不同，而对代码做大量改动的话，就不是一个优秀的程序
    //?????这里为什么要用接口来引用实体类对象？而不直接用实例类本身来引用,可能和“java组合”有关
    //private UserDao userDao = new UserDaoImpl();
    //private UserDao userDao = new UserDaoMysqlImpl();
    /*
    * 为了进行优化，利用set进行动态实现值的注入
    * */
    private UserDao userDao;
    public void setUserDao(UserDao userDao){this.userDao=userDao;}

    public void getUser() {

        /*有点代理模式那味道*/
        userDao.getUser();
    }
}
