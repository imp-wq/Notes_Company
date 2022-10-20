package com.itniuma.dao;

public interface BookDao {
    void save();

    String selectById(String id);
}
