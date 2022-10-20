package com.itniuma.dao.impl;

import com.itniuma.dao.BookDao;
import org.springframework.stereotype.Repository;

@Repository
public class BookDaoImpl implements BookDao {
    @Override
    public void save() {
        System.out.println("运行book dao的save方法...");
    }

    @Override
    public String selectById(String id) {
        System.out.println("select by id, id = " + id + "...");
        return "result";
    }
}
