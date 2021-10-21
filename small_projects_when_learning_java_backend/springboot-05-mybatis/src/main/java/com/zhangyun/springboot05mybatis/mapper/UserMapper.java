package com.zhangyun.springboot05mybatis.mapper;

import com.zhangyun.springboot05mybatis.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

//这个注解表示了这是一个mybatis的mapper类：dao层的东西
@Mapper
//相当于@bean，用于dao层。把类注册到springboot中待使用。
@Repository
public interface UserMapper {

    /*接口中整数的类型
    * public static final int age=18;
    * */

    int addUser(User user);

    int deleteUser(int id);

    int updateUser(User user);

    List<User> queryUserList();
    User queryUserById(int id);


}
