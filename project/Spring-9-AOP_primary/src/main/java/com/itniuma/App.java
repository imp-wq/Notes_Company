package com.itniuma;

import com.itniuma.config.SpringConfig;
import com.itniuma.dao.BookDao;
import com.itniuma.dao.impl.BookDaoImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext container = new AnnotationConfigApplicationContext(SpringConfig.class);
        BookDao bookDao = container.getBean(BookDao.class);
        System.out.println(bookDao.selectById("abc"));
        System.out.println("------------");
        System.out.println(bookDao.getClass());
    }
}
