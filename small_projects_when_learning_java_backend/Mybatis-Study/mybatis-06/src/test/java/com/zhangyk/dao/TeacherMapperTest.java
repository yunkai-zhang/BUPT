package com.zhangyk.dao;

import com.zhangyk.pojo.Student;
import com.zhangyk.pojo.Teacher;
import com.zhangyk.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class TeacherMapperTest {

    @Test
    public void getTeacherMapperTest() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
        Teacher teacher = mapper.getTeacher(1);

        System.out.println(teacher);

        sqlSession.close();
    }

    @Test
    public void getStudentTest(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = studentMapper.getStudent();

        for (Student student : students) {
            System.out.println(student);
        }

        sqlSession.close();

    }

    @Test
    public void getStudent2Test(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = studentMapper.getStudent2();

        for (Student student : students) {
            System.out.println(student);
        }

        sqlSession.close();

    }
}