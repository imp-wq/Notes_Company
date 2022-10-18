package com.itniuma.service.impl;

import com.itniuma.dao.BookDao;
import com.itniuma.dao.impl.BookDaoImpl;
import com.itniuma.service.BookService;

public class BookServiceImpl implements BookService {
    private BookDao bookDao;

    public void save() {
        System.out.println("service save running...");
        bookDao.save();
    }

    public void setBookDao(BookDaoImpl bookDao) {
        this.bookDao = bookDao;
    }

}
