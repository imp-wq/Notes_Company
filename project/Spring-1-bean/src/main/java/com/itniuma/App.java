package com.itniuma;

import com.itniuma.dao.BookDao;
import com.itniuma.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.awt.print.Book;

public class App {
    public static void main(String[] args) {
        // 获取IoC容器
        ApplicationContext container = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 获取bean
        // BookService bookService = (BookService) container.getBean("bookService");
        // BookService bookService = (BookService) container.getBean("service2");
        // BookDao bookDao = (BookDao) container.getBean("bookDaoFromFactory");
        // BookDao bookDao = (BookDao) container.getBean("bookDaoFromInstanceFactory");
        BookDao bookDao = (BookDao) container.getBean("bookDaoFromFactoryBean");

        bookDao.save();
    }
}
