package com.itniuma;

import com.itniuma.dao.BookDao;
import com.itniuma.service.BookService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        // 获取IoC容器
        ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("applicationContext.xml");
        container.registerShutdownHook();
        // 获取bean
        // BookService bookService = (BookService) container.getBean("bookService");

        // BookService bookService = container.getBean("bookService", BookService.class);
        BookService bookService = container.getBean(BookService.class);


        bookService.save();
    }
}
