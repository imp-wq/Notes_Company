package com.itniuma;

import com.itniuma.config.SpringConfig;
import com.itniuma.dao.BookDao;
import com.itniuma.dao.impl.Jdbc;
import com.itniuma.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;

public class AppForAnnotation {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext container = new AnnotationConfigApplicationContext(SpringConfig.class);
        container.registerShutdownHook();

        // BookDao bookDao = container.getBean(BookDao.class);
        // bookDao.save();

        BookService bookService = container.getBean(BookService.class);
        bookService.save();

        Jdbc jdbc = container.getBean(Jdbc.class);
        jdbc.save();
    }
}
