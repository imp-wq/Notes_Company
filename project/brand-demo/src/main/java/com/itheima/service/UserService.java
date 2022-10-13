package com.itheima.service;

import com.itheima.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.itheima.pojo.User;
import com.itheima.util.SqlSessionFactoryUtil;

public class UserService {
    /**
     * 登录方法，通过用户名和密码查找user
     */
    public User login(String username, String password) {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = userMapper.select(username, password);

        // 释放资源
        sqlSession.close();

        return user;
    }
}
