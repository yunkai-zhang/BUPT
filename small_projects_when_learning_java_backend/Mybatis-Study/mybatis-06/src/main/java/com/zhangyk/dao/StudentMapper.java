package com.zhangyk.dao;

import com.zhangyk.pojo.Student;

import java.util.List;

public interface StudentMapper {

    //查询所有的学生信息，以及对应的老师的信息
    public List<Student> getStudent();

    //查询所有的学生信息，以及对应的老师的信息
    public List<Student> getStudent2();
}