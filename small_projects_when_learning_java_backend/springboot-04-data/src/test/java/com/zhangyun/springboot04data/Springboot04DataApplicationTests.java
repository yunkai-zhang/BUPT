package com.zhangyun.springboot04data;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class Springboot04DataApplicationTests {

    @Autowired
    DataSource dataSource;

    @Test
    void contextLoads() throws SQLException {

        //查看默认数据源的类型：class com.zaxxer.hikari.HikariDataSource
        System.out.println("datasource class: "+dataSource.getClass());

        //获得数据库连接
        Connection connection = dataSource.getConnection();
        System.out.println("connection: "+connection);

        //关闭连接
        connection.close();

    }

}
