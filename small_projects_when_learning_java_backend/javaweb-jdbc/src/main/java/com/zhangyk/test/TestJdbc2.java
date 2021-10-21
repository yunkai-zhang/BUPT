package com.zhangyk.test;

import java.sql.*;

public class TestJdbc2 {

    //这个静态方法在idea中，左侧有个绿色三角，直接点击就可以执行main。而不用配置tomcat configure，因为本例子没用上tomcat
    public static void main(String[] args) throws Exception {
        //1. 配置信息
        /* url参数编写可以参考：https://www.cnblogs.com/liaojie970/p/5999818.html
        useUnicode=true&characterEncoding=utf-8 解决中文乱码
        url中jdbc:mysql://localhost:3306是本地数据库所固定的，jdbc是数据库名，问好后跟的都是url配置的参数
        */
        String url = "jdbc:mysql://localhost:3306/jdbc?useUnicode=true&characterEncoding=utf-8";
        String username = "root";
        String password = "123456";

        //2. 加载驱动
        /*导入jar包中：\maven-repo\mysql\mysql-connector-java\5.1.47\mysql-connector-java-5.1.47.jar!\com\mysql\jdbc\Driver.class
        把driver类的package+“.Driver”拿来填入forname
        forname会报错说异常（可能找不到包）未被处理，所以alt+enter，选择add exception signature to the method即可
        */
        Class.forName("com.mysql.jdbc.Driver");

        //3. 连接数据库
        /*getconnection方法需要alt+enter抛出异常
        connection代表数据库，可以用它对数据库进行各种操作
        * */
        Connection connection = DriverManager.getConnection(url, username, password);

        //4. 编写SQL
        //问号要传递的五个属性，预编译好了后，值会传进去
        String sql = "insert into users(id, name, password, email, birthday) values (?,?,?,?,?);";


        //5. 创建PreparedStatement对象,预编译
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,4);//给第一占位符？的值赋值为1
        preparedStatement.setString(2,"张云学java");//给第二占位符？的值赋值为"张云学java"
        preparedStatement.setString(3,"123456");//给第三占位符？的值赋值为"123456"
        preparedStatement.setString(4,"99@qq.com");//给第四占位符？的值赋值为"99@qq.com"
        preparedStatement.setDate(5,new Date(System.currentTimeMillis()));//给第五占位符？的值赋值为当前时间
        /*
         * 下面这种方式，外层的new date用的是文件开头导入的sql包的date，内层是util的date。内层的date创建可以不输入long，
         * 外层的date创建必须输入long，所以内层date要放到外层date的里面。不过这不方便，
         * 还是直接用new java.sql.Date(System.currentTimeMillis()))*/
        //preparedStatement.setDate(5,new Date(new java.util.Date().getTime()));


        //6. 执行sql
        int i = preparedStatement.executeUpdate();
        if(i>0){
            System.out.println("插入成功");
        }else{
            System.out.println("插入失败");
        }

        //7. 关闭链接，释放资源（一定要做）
        // 先开的后关
        //i是基本数据类型，不用关
        preparedStatement.close();
        connection.close();



    }
}
