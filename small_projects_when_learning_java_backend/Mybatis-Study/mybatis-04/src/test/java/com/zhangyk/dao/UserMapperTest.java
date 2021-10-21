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
    public void log4jTest(){

        //之前用sout展示信息，现在用log来实现；一个原因是log可以自动保存在.log文件中

        //展示一些提示信息的时候用info，其功能和严重性程度约等于sout
        logger.info("info:进入了testLog4j");
        //一个地方有问题，要调试的时候，用debug
        logger.debug("debug:进入了testLog4j");
        //紧急错误的时候用error，比如try catch时
        logger.error("debug:进入了testLog4j");

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


}
