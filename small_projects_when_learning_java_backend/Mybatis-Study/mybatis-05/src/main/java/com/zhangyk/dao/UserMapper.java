package com.zhangyk.dao;

import com.zhangyk.pojo.User;
import org.apache.ibatis.annotations.*;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

public interface UserMapper {




    //分页
    //由于Mapper.xml中parameterType只能配置一个，所以只能传入一个对象，那么这里可以用map解决
    List<User> getUserByLimit(HashMap<String, Integer> map);

    //使用注解代替Mapper.xml
    @Select("select * from mybatis.user")
    List<User> getUsersByAnnotation();

    //方法存在多个参数，且参数为基本数据类型或String时，所有的参数前面必须加上@param("变量名")，且注解中的变量名的优先级高
    //但是实际工作时，往往把所有参数封装成一个对象传入
    @Select("select * from user where id=#{id}")
    User getUserById(@Param("id") int id);

    //User类中没有pwd参数，只有password参数，所以传参的时候{}中要填password
    @Insert("insert into user (id, name, pwd) values (#{id},#{name},#{password})")
    int addUser(User user);

    @Update("update user set name=#{name},pwd=#{password} where id=#{id}")
    int updateUser(User user);

    @Delete("delete from user where id=#{uid}")
    int deleteUser(@Param("uid") int id);


}
