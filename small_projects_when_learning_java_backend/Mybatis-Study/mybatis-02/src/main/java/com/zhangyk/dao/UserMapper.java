package com.zhangyk.dao;

import com.zhangyk.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    //查询全部用户
    List<User> getUserList();

    //根据id查询用户
    User getUserById(int givenId);

    //insert一个用户
    int addUser(User user);

    //更新一个用户
    int updateUser(User user);

    //删除一个用户
    int deleteUser(int id);

}
