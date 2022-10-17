package com.itniuma.factory;

import com.itniuma.dao.BookDao;
import com.itniuma.dao.impl.BookDaoImpl;

public class BookDaoInstanceFactory {
    public BookDao getBookDao() {
        System.out.println("instance factory setup");
        return new BookDaoImpl();
    }
}
