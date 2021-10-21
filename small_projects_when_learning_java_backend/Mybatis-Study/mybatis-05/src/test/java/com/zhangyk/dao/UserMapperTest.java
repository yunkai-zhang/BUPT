package com.zhangyk.dao;

import com.zhangyk.pojo.User;
import com.zhangyk.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;


public class UserMapperTest {

    //因为日志可能在多个方法中使用，所以要提升作用域
    //网课老师加上了static
    //注意这里的logger要导入的是apache下的版本包，不然会报错
    static Logger logger = Logger.getLogger(UserMapperTest.class);

    @Test
    public void getUserByIdTest(){
        //第一步：获得SqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        //第二步：执行sql；方式一：getMapper
        //获取userdao后，就可以使用dao中的方法
        UserMapper userdao = sqlSession.getMapper(UserMapper.class);
        //getUserList()本质就是UserMapper.xml中配置的sql语句
        User user = userdao.getUserById(1);

        System.out.println(user);

        //第三步关闭sqlsession(和流一样)
        sqlSession.close();
    }

    @Test
    public void getUserByLimitTest(){

        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("startIndex",0);
        map.put("pageSize",2);

        List<User> userByLimit = userMapper.getUserByLimit(map);

        for (User user : userByLimit) {
            System.out.println(user);
        }

        //select不需要commit，用完sqlsession直接关闭即可
        sqlSession.close();

    }

    @Test
    public void getUsersByAnnotationTest(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        //底层主要应用反射。通过反射得到了注解
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        List<User> users = userMapper.getUsersByAnnotation();
        for (User user : users) {
            System.out.println(user);
        }

        sqlSession.close();

    }

    @Test
    public void addUserTest(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        int i = userMapper.addUser(new User(10, "张云10", "123456"));

        System.out.println(i);

        sqlSession.commit();

        sqlSession.close();
    }

    @Test
    public void updateUserTest(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        int i = userMapper.updateUser(new User(1, "顶针", "2333"));

        System.out.println(i);

        sqlSession.commit();
        sqlSession.close();

    }

    @Test
    public void deleteUserTest(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        int i = userMapper.deleteUser(10);

        System.out.println(i);

        sqlSession.commit();
        sqlSession.close();
    }




}
