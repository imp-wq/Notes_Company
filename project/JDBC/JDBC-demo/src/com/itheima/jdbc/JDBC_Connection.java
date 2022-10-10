package com.itheima.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC_Connection {
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
        String sql1 = "UPDATE account SET money = 3000 WHERE id = 1;";
        String sql2 = "UPDATE account SET money = 3000 WHERE id = 2;";

        // 4.获取statement对象
        Statement statement = conn.createStatement();

        // 开启事务

        try {
            conn.setAutoCommit(false);

            // 5.执行sql，返回值为受影响的行数。
            int count1 = statement.executeUpdate(sql1);
            System.out.println(count1);

            // 制造异常
//            int i = 3 / 0;

            int count2 = statement.executeUpdate(sql2);
            System.out.println(count2);

            // 提交事务
            conn.commit();
        } catch (Exception throwables) {
            // 回滚事务
            conn.rollback();
            throwables.printStackTrace();
        }


        // 6.释放资源
        statement.close();
        conn.close();
    }
}
