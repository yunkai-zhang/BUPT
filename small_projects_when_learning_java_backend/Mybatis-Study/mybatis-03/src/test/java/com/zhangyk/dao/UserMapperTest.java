package com.zhangyk.dao;

import com.zhangyk.dao.UserMapper;
import com.zhangyk.pojo.User;
import com.zhangyk.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class UserMapperTest {

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

}
