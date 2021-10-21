package com.zhangyk.test;

import java.sql.*;

public class TestJdbc {
    //这个静态方法在idea中，左侧有个绿色三角，直接点击就可以执行main。而不用配置tomcat configure，因为本例子没用上tomcat
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
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

        //4. 创建statement对象
        // statement对象是用于向数据库发送sql的（增删改查）
        Statement statement = connection.createStatement();

        //5. 编写SQL
        //sql语句末尾最好带个分号
        String sql = "select * from users;";

        //6. 执行sql
        /*查询语句也可以用excuete（）执行。但是“走什么，用什么”，用executeQuery效率更高
        执行查询sql时返回的是ResultSet（结果集）；执行其他sql时，返回的是int，即受影响的行数
        * */
        ResultSet resultSet = statement.executeQuery(sql);

        while(resultSet.next()){
            System.out.println("id="+ resultSet.getObject("id"));
            System.out.println("name="+ resultSet.getObject("name"));
            System.out.println("password="+ resultSet.getObject("password"));
            System.out.println("email="+ resultSet.getObject("email"));
            System.out.println("birthday="+ resultSet.getObject("birthday"));
        }

        //7. 关闭链接，释放资源（一定要做）
        // 先开的后关
        resultSet.close();
        statement.close();
        connection.close();



    }
}
