package com.itheima.exercise;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.itheima.pojo.Brand;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BrandExercise {

    /**
     * 查询所有
     */
    @Test
    public void testSelectAll() throws Exception {

        // System.out.println(System.getProperty("user.dir"));
        // 获取数据库连接
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/com/itheima/druid.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        Connection connection = dataSource.getConnection();

        String sql = "select * from tb_brand";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        List<Brand> brands = new ArrayList<>();

        ResultSet resultSet = preparedStatement.executeQuery();
        Brand brand = null;
        while (resultSet.next()) {

            int id = resultSet.getInt("id");
            String brandName = resultSet.getString("brand_name");
            String companyName = resultSet.getString("company_name");
            int ordered = resultSet.getInt("ordered");
            String description = resultSet.getString("description");
            int status = resultSet.getInt("status");

            brand = new Brand();
            brand.setId(id);
            brand.setBrandName(brandName);
            brand.setCompanyName(companyName);
            brand.setOrdered(ordered);
            brand.setDescription(description);
            brand.setStatus(status);

            brands.add(brand);
        }

        System.out.println(brands);

        //    释放资源
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

    /**
     * 添加
     * 参数：除了id之外所有参数
     * 结果：boolean是否添加成功
     */
    @Test
    public void testAdd() throws Exception {
        // 页面接收的参数
        String brandName = "香飘飘";
        String companyName = "香飘飘";
        int ordered = 1;
        String description = "绕地球一圈";
        int status = 1;


        // 获取数据库连接
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/com/itheima/druid.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        Connection connection = dataSource.getConnection();

        String sql = "insert into tb_brand(brand_name, company_name,ordered,description,status) values (?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        // 设置参数
        preparedStatement.setString(1, brandName);
        preparedStatement.setString(2, companyName);
        preparedStatement.setInt(3, ordered);
        preparedStatement.setString(4, description);
        preparedStatement.setInt(5, status);

        int count = preparedStatement.executeUpdate();

        // 处理结果
        if (count > 0) {
            System.out.println("执行成功");
        } else {
            System.out.println("执行失败");
        }

        //    释放资源
        preparedStatement.close();
        connection.close();
    }

    /**
     * 修改
     * 参数：所有数据，根据id查找。
     * 结果：boolean，是否修改成功
     */
    @Test
    public void testUpdate() throws Exception {
        // 页面接收的参数
        String brandName = "香飘飘";
        String companyName = "香飘飘";
        int ordered = 1000;
        String description = "绕地球三圈";
        int status = 1;
        int id = 4;

        // 获取数据库连接
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/com/itheima/druid.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        Connection connection = dataSource.getConnection();

        // 一定要注意字符串拼接处的空格位置，保证关键字前后至少留有一个空格。
        String sql = "update tb_brand set " +
                "brand_name = ?," +
                "company_name = ?," +
                "ordered = ?," +
                "description = ?," +
                "status = ? " +
                " where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        // 设置参数
        preparedStatement.setString(1, brandName);
        preparedStatement.setString(2, companyName);
        preparedStatement.setInt(3, ordered);
        preparedStatement.setString(4, description);
        preparedStatement.setInt(5, status);
        preparedStatement.setInt(6, id);

        int count = preparedStatement.executeUpdate();
        // 处理结果
        if (count > 0) {
            System.out.println("执行成功");
        } else {
            System.out.println("执行失败");
        }

        //    释放资源
        preparedStatement.close();
        connection.close();
    }

    /**
     * 删除
     * 参数：id
     * 结果：boolean，是否删除成功
     */
    @Test
    public void testDeleteById() throws Exception {
        // 页面接收的参数
        int id = 4;

        // 获取数据库连接
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/com/itheima/druid.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        Connection connection = dataSource.getConnection();

        // 一定要注意字符串拼接处的空格位置，保证关键字前后至少留有一个空格。
        String sql = "delete from tb_brand where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        // 设置参数
        preparedStatement.setInt(1, id);

        int count = preparedStatement.executeUpdate();
        // 处理结果
        if (count > 0) {
            System.out.println("执行成功");
        } else {
            System.out.println("执行失败");
        }

        //    释放资源
        preparedStatement.close();
        connection.close();
    }
}
