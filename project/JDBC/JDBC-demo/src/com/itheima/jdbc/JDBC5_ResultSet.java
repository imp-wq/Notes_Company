package com.itheima.jdbc;

import org.junit.Test;
import pojo.Account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBC5_ResultSet {
    @Test
    public void testResultSet() throws Exception {
        String url = "jdbc:mysql:///db_demo1?useSSL=false";
        String username = "root";
        String password = "mysql";

        Connection conn = DriverManager.getConnection(url, username, password);
        Statement statement = conn.createStatement();

        String sql = "select * from account";
        // 执行sql
        ResultSet resultSet = statement.executeQuery(sql);
        // 通过循环获取所有结果。
        // 光标向下移动一次，获取第一个结果。

        List<Account> list = new ArrayList<>();
        while (resultSet.next()) {
            // 通过列的编号获取
            // int id = resultSet.getInt(1);
            // String name = resultSet.getString(2);
            // double money = resultSet.getDouble(3);
            // 通过列名获取
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            double money = resultSet.getDouble("money");

            // System.out.println("id:" + id + "\tname:" + name + "\tmoney:" + money);
            Account account = new Account();
            account.setId(id);
            account.setName(name);
            account.setMoney(money);

            list.add(account);
        }

        System.out.println(list);

        // 释放资源
        resultSet.close();
        statement.close();
        conn.close();
    }
}
