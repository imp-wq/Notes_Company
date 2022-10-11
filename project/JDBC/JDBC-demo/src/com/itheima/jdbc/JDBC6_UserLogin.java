package com.itheima.jdbc;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBC6_UserLogin {

    @Test
    public void testSQLInject() throws Exception {
        String url = "jdbc:mysql:///db_demo1?useSSL=false";
        String username = "root";
        String password = "mysql";

        Connection conn = DriverManager.getConnection(url, username, password);

        // 用户登录的用户名和密码。
        String name = "zhangsan 123123";
        String pwd = "' or '1' = '1";
        String sql = "select * from tb_user where username='" + name + "' and  password='" + pwd + "' ";

        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        // 如果有数据，则代表登录成功，没有数据代表登录失败。
        if (resultSet.next()) {
            System.out.println("登录成功");
        } else {
            System.out.println("登录失败");
        }

        // 释放资源
        resultSet.close();
        statement.close();
        conn.close();
    }
}
