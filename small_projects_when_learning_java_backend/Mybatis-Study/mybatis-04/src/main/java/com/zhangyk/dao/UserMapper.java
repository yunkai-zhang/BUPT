package com.zhangyk.dao;

import com.zhangyk.pojo.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface UserMapper {


    //根据id查询用户
    User getUserById(int givenId);

    //分页
    //由于Mapper.xml中parameterType只能配置一个，所以只能传入一个对象，那么这里可以用map解决
    List<User> getUserByLimit(HashMap<String, Integer> map);



}
