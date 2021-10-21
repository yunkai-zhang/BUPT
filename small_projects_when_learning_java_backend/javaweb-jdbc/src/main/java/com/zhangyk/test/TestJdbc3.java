package com.zhangyk.test;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestJdbc3 {

    @Test
     public void test()  {
        //1. 配置信息
        //useUnicode=true&characterEncoding=utf-8 解决中文乱码
        String url = "jdbc:mysql://localhost:3306/jdbc?useUnicode=true&characterEncoding=utf-8";
        String username = "root";
        String password = "123456";


        /*下面例子充分体现了：出现异常，要不自己trycatch处理，要不函数头处就throws exception抛出*/

        //try catch要把对象提到try的外面
        Connection connection = null;
        try {
            //2. 加载驱动
            Class.forName("com.mysql.jdbc.Driver");

            //3. 连接数据库
            //Connection connection = DriverManager.getConnection(url, username, password);
            connection = DriverManager.getConnection(url, username, password);

            //4. 通知数据库开启事务!!!!
            //默认为true。设置为false即开启事务
            connection.setAutoCommit(false);

            String sql = "update account set money=money-100 where name='张云1';";
            connection.prepareStatement(sql).executeUpdate();

            //制造错误
            //int i = 1 / 0;

            String sql2 = "update account set money=money+100 where name='李四';";
            connection.prepareStatement(sql2).executeUpdate();

            //提交事务
            //开启事务后所有语句都成功了，就提交语句
            connection.commit();
            System.out.println("success");
        }
        //事务失败的时候，触发catch，在catch中进行事务回滚
        catch(Exception e){
            //在rollback爆红的时候，alt+enter会自动为rollback生成trycatch。因为回滚也可能失败，所以必须对rollback也trycatch
            try {
                //出现异常，通知数据库回滚事务
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally{
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
