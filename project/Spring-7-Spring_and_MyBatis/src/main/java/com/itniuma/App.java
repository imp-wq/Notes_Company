package com.itniuma;

import com.itniuma.config.SpringConfig;
import com.itniuma.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        // 通过注解形式获取Spring IoC容器
        AnnotationConfigApplicationContext container = new AnnotationConfigApplicationContext(SpringConfig.class);

        UserService userService = container.getBean(UserService.class);
        userService.showAllUser();
    }
}
