package com.itniuma.service.impl;

import com.itniuma.dao.BookDao;
import com.itniuma.dao.impl.BookDaoImpl;
import com.itniuma.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    @Value("kobe")
    private String name;
    @Value("33")
    private int age;
    private BookDao bookDao;

    @Autowired
    // @Qualifier("bookDao")
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public void save() {
        System.out.println("service save running...");
        System.out.println(this.name);
        System.out.println(this.age);
        bookDao.save();
    }

    public void setBookDao(BookDaoImpl bookDao) {
        this.bookDao = bookDao;
    }

}
