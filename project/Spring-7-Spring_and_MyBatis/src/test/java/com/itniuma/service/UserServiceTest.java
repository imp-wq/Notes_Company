package com.itniuma.service;

import com.itniuma.config.SpringConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

// 设置类运行器
@RunWith(SpringJUnit4ClassRunner.class)
// 指定Spring配置类
@ContextConfiguration(classes = SpringConfig.class)
public class UserServiceTest {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Test
    public void testShowAll() {
        userService.showAllUser();
    }
}
