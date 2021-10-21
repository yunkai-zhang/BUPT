package com.zhangyun.shirospringboot.service;

import com.zhangyun.shirospringboot.pojo.User;

public interface UserService {
    public User queryUserByName(String name);
}
