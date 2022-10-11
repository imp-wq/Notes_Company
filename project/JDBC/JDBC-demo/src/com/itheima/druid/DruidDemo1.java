package com.itheima.druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Map;
import java.util.Properties;

public class DruidDemo1 {
    public static void main(String[] args) throws Exception {

        // System.out.println(System.getProperty("user.dir"));

        // 1. 加载配置文件
        Properties properties = new Properties();
        properties.load(new FileInputStream("JDBC-demo/src/com/itheima/druid.properties"));

        // 2. 获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);

        // 3. 获取数据库连接
        Connection connection = dataSource.getConnection();


    }
}
