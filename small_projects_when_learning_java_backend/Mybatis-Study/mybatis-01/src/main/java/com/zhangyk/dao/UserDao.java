package com.zhangyk.dao;

import com.zhangyk.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    //查询全部用户
    List<User> getUserList();

    //根据id查询用户
    User getUserById(int givenId);

    //insert一个用户
    int addUser(User user);

    //更新一个用户
    int updateUser(User user);

    //使用map来操作数据库
    int addUser2(Map<String,Object> map);

    //删除一个用户
    int deleteUser(int id);

    //模糊查询
    List<User> getUserLike(String value);
}
