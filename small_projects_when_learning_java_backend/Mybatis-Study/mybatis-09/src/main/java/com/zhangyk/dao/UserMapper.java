package com.zhangyk.dao;

import com.zhangyk.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    //根据id查询用户
    User queryUserById(@Param("id") int id);

    //更新用户
    int updateUser(User user);

}
