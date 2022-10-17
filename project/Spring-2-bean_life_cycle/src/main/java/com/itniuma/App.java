package com.itniuma;

import com.itniuma.dao.BookDao;
import com.itniuma.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.awt.print.Book;

public class App {
    public static void main(String[] args) {
        // 获取IoC容器
        ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("applicationContext.xml");
        container.registerShutdownHook();
        // 获取bean
        BookDao bookDao = (BookDao) container.getBean("bookDao");

        bookDao.save();
    }
}
