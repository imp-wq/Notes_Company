package com.itheima;

import com.itheima.mapper.UserMapper;
import com.itheima.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * MyBatis的Mapper代理开发
 */
public class MyBatisDemo2 {
    public static void main(String[] args) throws Exception {
        // 1. 加载mybatis的核心配置文件，获取SqlSessionFactory
        // 配置文件在resources根目录下，因此可以直接写文件名。
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2. 从factory获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3. 通过Mapper代理对象执行sql
        // 3.1 获取UserMapper接口的代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        // 3.2 通过Mapper代理对象执行sql语句。
        List<User> users = userMapper.selectAll();

        System.out.println(users);

        // 4. 释放资源
        sqlSession.close();
    }
}
