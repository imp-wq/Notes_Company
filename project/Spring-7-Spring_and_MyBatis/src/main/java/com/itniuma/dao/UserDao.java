package com.itniuma.dao;

import com.itniuma.domain.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserDao {
    @Select("select * from tb_user;")
    List<User> selectAll();
}
