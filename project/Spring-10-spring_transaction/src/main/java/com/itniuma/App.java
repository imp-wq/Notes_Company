package com.itniuma;

import com.itniuma.config.SpringConfig;
import com.itniuma.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext container = new AnnotationConfigApplicationContext(SpringConfig.class);
        AccountService accountService = container.getBean(AccountService.class);
        accountService.showAll();
        accountService.transfer("Tom", "Jerry", 100d);
    }
}
