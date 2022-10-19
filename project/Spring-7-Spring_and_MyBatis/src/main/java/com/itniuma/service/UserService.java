package com.itniuma.service;

import com.itniuma.dao.UserDao;
import com.itniuma.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void showAllUser() {
        System.out.println(userDao.selectAll());
    }
}
