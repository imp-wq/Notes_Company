package com.itheima.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * JDBC基本使用
 */
public class JDBC_Demo {
    public static void main(String[] args) throws Exception {
        // 1.注册驱动
        // Class.forName("com.mysql.jdbc.Driver");

        // 2.建立连接，获取连接对象。
//        String url = "jdbc:mysql://127.0.0.1:3306/db_demo1?useSSL=false";
        String url = "jdbc:mysql:///db_demo1?useSSL=false";
        String username = "root";
        String password = "mysql";

        Connection conn = DriverManager.getConnection(url, username, password);
        // 3.定义sql
        String sql = "UPDATE account SET money = 2000 WHERE id = 1;";

        // 4.获取statement对象
        Statement statement = conn.createStatement();

        // 5.执行sql，返回值为受影响的行数。
        int count = statement.executeUpdate(sql);
        System.out.println(count);

        // 6.释放资源
        statement.close();
        conn.close();
    }
}
