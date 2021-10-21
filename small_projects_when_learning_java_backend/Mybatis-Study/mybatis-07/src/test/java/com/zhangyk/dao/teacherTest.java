package com.zhangyk.dao;

import com.zhangyk.pojo.Teacher;
import com.zhangyk.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class teacherTest {

    @Test
    public void getTeachersTest(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        TeacherMapper teacherMapper = sqlSession.getMapper(TeacherMapper.class);

        List<Teacher> teachers = teacherMapper.getTeachers();
        for (Teacher teacher : teachers) {
            System.out.println(teacher);
        }

        sqlSession.close();
    }

    @Test
    public void getTeacherContainingStudentsTest(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        TeacherMapper teacherMapper = sqlSession.getMapper(TeacherMapper.class);

        Teacher teacher = teacherMapper.getTeacherContainingStudents(1);

        System.out.println(teacher);
        /**查询结果如下
         * Teacher(id=1, name=章老师, students=[Student(id=1, name=小明, tid=1), Student(id=2, name=小红, tid=1), Student(id=3, name=小张, tid=1), Student(id=4, name=小李, tid=1), Student(id=5, name=小王, tid=1)])
         */

        sqlSession.close();
    }
}
