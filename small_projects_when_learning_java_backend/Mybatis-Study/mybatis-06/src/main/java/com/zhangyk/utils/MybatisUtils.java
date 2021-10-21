package com.zhangyk.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

//工具类，用来获取sqlSessionFactory。从而用sqlSessionFactory（sqlSession的工厂模式）构建sqlSession
public class MybatisUtils {

    //必须“提升作用域”，即把sqlSessionFactory的定义从静态代码块中提取出来，这样才能在另一静态方法(getSqlSession())中被访问
    private static SqlSessionFactory sqlSessionFactory;

    static{
        //!!!!try中的三句话是死的，所以要把这三句话包装成工具类
        //使用mybatis第一步：是为了获取sqlSessionFactory对象，写下面三句
        try {
            //教学视频的resource报错，然后用静态代码块保住resource。一初始就加载，后面也不用管了。
            //可以直接用文件名，因为resource目录下的文件打包后，直接存放在根目录下
            String resource =  "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*既然有了 SqlSessionFactory，顾名思义，我们可以从中获得 SqlSession 的实例。
    SqlSession 提供了在数据库执行 SQL 命令所需的所有方法。
    下面方法返回一个SqlSession实例*/
    public static SqlSession getSqlSession(){
        return sqlSessionFactory.openSession();
    }

}
