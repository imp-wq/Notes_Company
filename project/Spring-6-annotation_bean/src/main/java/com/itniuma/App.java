package com.itniuma;

import com.itniuma.dao.BookDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext container = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookDao bookDao = container.getBean(BookDao.class);
        bookDao.save();
    }
}
