package com.zhangyun.springboot04data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

//函数返回字符串，前后端分离
@RestController
public class JDBCController {
    @Autowired
    JdbcTemplate jdbcTemplate;


    @GetMapping("/userList")
    //查询数据库中user表的所有数据
    //没有实体类，数据库中的东西怎么获取？：Map
    public List<Map<String,Object>> userList(){
        String sql="select * from user";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);

        return maps;
    }

    @GetMapping("/addUser")
    public String addUser(){
        String sql="insert into user(id,name,pwd) values (4,'小明','123456')";

        //插入也算一种更新
        jdbcTemplate.update(sql);
        return "add user done";
    }

    //使用restful风格传递参数，体现在“请求路径”和“@PathVariable”上
    @GetMapping("/updateUser/{id}")
    public String updateUser(@PathVariable("id") int id){
        String sql="update user set name=?, pwd=? where id="+id;

        //sql的参数要通过objects[]传入jdbc
        Object[] objects = new Object[2];
        objects[0]="李四1";
        objects[1]="这是更改后的密码密码";

        jdbcTemplate.update(sql,objects);
        return "update user done";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id")int id){
        String sql="delete from user where id="+id;

        jdbcTemplate.update(sql);
        return "delete user done";
    }
}
