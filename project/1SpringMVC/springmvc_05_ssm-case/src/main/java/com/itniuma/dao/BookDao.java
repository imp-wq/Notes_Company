package com.itniuma.dao;

import java.awt.print.Book;
import java.util.List;

public interface BookDao {
    void save(Book book);

    void update(Book book);

    void delete(Integer id);

    Book getById(Integer id);

    List<Book> getAll();
}
