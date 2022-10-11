package com.itheima.jdbc;

import org.junit.Test;

import java.sql.*;

public class JDBC7_PreparedStatement {
    @Test
    public void testPrepared() throws Exception {
        String url = "jdbc:mysql:///db_demo1?useSSL=false&useServerPrepStmts=true";
        String username = "root";
        String password = "mysql";

        Connection conn = DriverManager.getConnection(url, username, password);

        // 用户登录的用户名和密码。
        String name = "zhangsan 123123";
        String pwd = "' or '1' = '1";
        String sql = "select * from tb_user where username=? and  password=?";

        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        // 编号, 参数值
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, pwd);

        ResultSet resultSet = preparedStatement.executeQuery();
        // 如果有数据，则代表登录成功，没有数据代表登录失败。
        if (resultSet.next()) {
            System.out.println("登录成功");
        } else {
            System.out.println("登录失败");
        }

        // 释放资源
        resultSet.close();
        preparedStatement.close();
        conn.close();
    }
}
