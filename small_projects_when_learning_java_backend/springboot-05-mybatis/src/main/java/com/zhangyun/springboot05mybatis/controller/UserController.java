package com.zhangyun.springboot05mybatis.controller;

import com.zhangyun.springboot05mybatis.mapper.UserMapper;
import com.zhangyun.springboot05mybatis.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//前后端分离
@RestController
public class UserController {
    //拿到注册的userMapper接口，从而可以调用接口中的方法
    @Autowired
    private UserMapper userMapper;

    //添加一个用户
    //URL请求都是get
    @GetMapping("/addUser")
    public String addUser(){
        userMapper.addUser(new User(5,"阿东","pswnow"));
        return "addUser done";
    }

    //删除一个用户
    @GetMapping("/deleteUser")
    public String deleteUser(){
        userMapper.deleteUser(5);
        return "deleteUser done";
    }

    //更新一个用户
    @GetMapping("/updateUser")
    public String updateUser(){
        userMapper.updateUser(new User(5,"阿阳","pswchanged"));
        return "updateUser done";
    }

    //查询所有用户
    @GetMapping("/queryUserList")
    public List<User> queryUserList(){
        List<User> users = userMapper.queryUserList();
        return users;
    }

}
