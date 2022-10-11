package com.itheima;

import com.itheima.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * MyBatis的快速入门
 */
public class MyBatisDemo {
    public static void main(String[] args) throws Exception {
        // 1. 加载mybatis的核心配置文件，获取SqlSessionFactory
        // 配置文件在resources根目录下，因此可以直接写文件名。
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2. 从factory获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3. 执行sql
        List<User> users = sqlSession.selectList("test.selectAll");

        System.out.println(users);

        // 4. 释放资源
        sqlSession.close();
    }
}
