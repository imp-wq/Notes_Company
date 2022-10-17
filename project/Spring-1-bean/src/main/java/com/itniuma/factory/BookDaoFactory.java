package com.itniuma.factory;

import com.itniuma.dao.BookDao;
import com.itniuma.dao.impl.BookDaoImpl;
import com.itniuma.service.BookService;
import com.itniuma.service.impl.BookServiceImpl;

public class BookDaoFactory {
    public static BookDao getBookDao() {
        System.out.println("factory setup...");
        return new BookDaoImpl();
    }
}
