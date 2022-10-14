package com.itheima.service;

import com.itheima.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.itheima.pojo.User;
import com.itheima.util.SqlSessionFactoryUtil;

public class UserService {
    private SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();

    /**
     * 登录方法，通过用户名和密码查找user
     */
    public User login(String username, String password) {
        SqlSession sqlSession = this.sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = userMapper.select(username, password);

        // 释放资源
        sqlSession.close();

        return user;
    }

    /**
     * 用户注册
     */
    public boolean register(User user) {
        SqlSession sqlSession = this.sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        // 判断用户名是否存在
        User _user = userMapper.selectByUsername(user.getUsername());
        if (_user == null) {
            // 用户名不存在
            userMapper.add(user);
            // 添加后，提交事务。
            sqlSession.commit();
        }
        // 用户名存在
        // 释放资源
        sqlSession.close();

        return _user == null;
    }
}
