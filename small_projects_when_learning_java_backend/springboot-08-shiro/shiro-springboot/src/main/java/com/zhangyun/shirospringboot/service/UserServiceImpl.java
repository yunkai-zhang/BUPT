package com.zhangyun.shirospringboot.service;

import com.zhangyun.shirospringboot.mapper.UserMapper;
import com.zhangyun.shirospringboot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//老师说这里用@Component也可以
@Service
public class UserServiceImpl implements UserService {

    //从spring容器中拿到UserMapper的bean
    @Autowired
    UserMapper userMapper;

    @Override
    public User queryUserByName(String name) {
        return userMapper.queryUserByName(name);
    }
}
