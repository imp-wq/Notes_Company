package com.itniuma.service;

import com.itniuma.domain.Book;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface BookService {
    /**
     * save a book
     *
     * @param book
     * @return
     */
    boolean save(com.itniuma.domain.Book book);

    /**
     * update a book
     *
     * @param book
     * @return
     */
    boolean update(com.itniuma.domain.Book book);

    /**
     * delte a book by id
     *
     * @param id
     * @return
     */
    boolean delete(Integer id);

    /**
     * get a book info by id
     *
     * @param id
     * @return
     */
    Book getById(Integer id);

    /**
     * get all book info
     *
     * @return
     */
    List<Book> getAll();
}
