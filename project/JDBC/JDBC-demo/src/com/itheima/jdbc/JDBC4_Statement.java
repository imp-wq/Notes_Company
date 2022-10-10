package com.itheima.jdbc;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JDBC4_Statement {

    @Test
    public void testDML() throws Exception {
        // 2.建立连接，获取连接对象。
        String url = "jdbc:mysql:///db_demo1?useSSL=false";
        String username = "root";
        String password = "mysql";

        Connection conn = DriverManager.getConnection(url, username, password);
        // 3.定义sql
        String sql = "UPDATE account SET money = 3000 WHERE id = 5;";

        // 4.获取statement对象
        Statement statement = conn.createStatement();

        // 开启事务

        try {
            conn.setAutoCommit(false);

            // 5.执行sql，返回值为受影响的行数。
            int count = statement.executeUpdate(sql);
            if (count > 0) {
                System.out.println("修改成功");
            } else {
                System.out.println("修改失败");
            }
            System.out.println(count);

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
