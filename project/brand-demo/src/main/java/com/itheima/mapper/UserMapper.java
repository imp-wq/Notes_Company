package com.itheima.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.itheima.pojo.User;

public interface UserMapper {
    /**
     * 根据用户名和密码查询用户对象
     */
    @Select("select * from tb_user where username=#{username} and password=#{password};")
    User select(@Param("username") String username, @Param("password") String password);

    /**
     * 根据用户名查询用户，用于注册时查询用户名是否被占用
     */
    @Select("select * from tb_user where username=#{username}")
    User selectByUsername(String username);

    /**
     * 增加用户
     */
    @Insert("insert into tb_user (username, password) values (#{username},#{password});")
    void add(User user);
}
