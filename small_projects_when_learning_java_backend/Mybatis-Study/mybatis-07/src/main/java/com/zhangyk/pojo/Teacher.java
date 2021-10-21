package com.zhangyk.pojo;

import lombok.Data;

import java.util.List;

//使用lombok免去生成getset等语句
@Data
public class Teacher {

    private int id;
    private String name;

    //一个老师有多个学生
    private List<Student> students;
}
