package com.zhangyk.dao;

import com.zhangyk.pojo.User;
import com.zhangyk.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

public class UserMapperTest {
    //test方法的名字最好是：被测试方法+Test，比较规范
    @Test
    public void getUserListTest(){
        //第一步：获得SqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        //第二步：执行sql；方式一：getMapper
        //获取userdao后，就可以使用dao中的方法
        UserMapper userdao = sqlSession.getMapper(UserMapper.class);
        //getUserList()本质就是UserMapper.xml中配置的sql语句
        List<User> userList = userdao.getUserList();

        for(User user:userList){
            System.out.println(user);
        }

        //第三步关闭sqlsession(和流一样)
        sqlSession.close();
    }

    @Test
    public void getUserByIdTest(){
        //第一步：获得SqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        //第二步：执行sql；方式一：getMapper
        //获取userdao后，就可以使用dao中的方法
        UserMapper userdao = sqlSession.getMapper(UserMapper.class);
        //getUserList()本质就是UserMapper.xml中配置的sql语句
        User user = userdao.getUserById(3);

        System.out.println(user);

        //第三步关闭sqlsession(和流一样)
        sqlSession.close();

    }

    /*1. 增删改需要提交事务，才能实现对数据库的修改
    2. 成功执行sql插入后，需要过几秒（至少五秒）再去点击idea中开启的表，看内容是否插入。这是因为数据可能没法及时显示。
    还可以点击database模块第一行中的自旋转按钮（synchronize）,把数据库最新内容同步到idea显示
    * */
    @Test
    public void addUserTest(){
        //第一步：获得SqlSession对象
        //!!!!获取sqlsession的时候参数设置为true就会自动提交，不用再程序末尾手动commit。不过还是推荐手动commit，更能体现事务思想
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        //第二步：执行sql；方式一：getMapper
        //获取userdao后，就可以使用dao中的方法
        UserMapper userdao = sqlSession.getMapper(UserMapper.class);
        //getUserList()本质就是UserMapper.xml中配置的sql语句
        int numberOfEffectedLines = userdao.addUser(new User(6,"赵六","123456"));

        System.out.println(numberOfEffectedLines);

        //提交事务
        sqlSession.commit();

        //第三步关闭sqlsession(和流一样)
        sqlSession.close();
    }

    @Test
    public void updateUserTest(){
        //第一步：获得SqlSession对象
        //!!!!获取sqlsession的时候参数设置为true就会自动提交，不用再程序末尾手动commit。不过还是推荐手动commit，更能体现事务思想
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        //第二步：执行sql；方式一：getMapper
        //获取userdao后，就可以使用dao中的方法
        UserMapper userdao = sqlSession.getMapper(UserMapper.class);
        //getUserList()本质就是UserMapper.xml中配置的sql语句
        int numberOfEffectedLines = userdao.updateUser(new User(6,"赵1","654321"));

        System.out.println(numberOfEffectedLines);

        //提交事务
        sqlSession.commit();

        //第三步关闭sqlsession(和流一样)
        sqlSession.close();
    }

    @Test
    public void deleteUserTest(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.deleteUser(3);

        sqlSession.commit();

        sqlSession.close();

    }

}
