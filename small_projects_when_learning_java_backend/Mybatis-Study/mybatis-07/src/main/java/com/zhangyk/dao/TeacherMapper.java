package com.zhangyk.dao;

import com.zhangyk.pojo.Student;
import com.zhangyk.pojo.Teacher;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TeacherMapper {
    //获得所有老师
    public List<Teacher> getTeachers();

    //获取指定老师下的所有学生及老师的信息
    public Teacher getTeacherContainingStudents(@Param("id") int id);
}
