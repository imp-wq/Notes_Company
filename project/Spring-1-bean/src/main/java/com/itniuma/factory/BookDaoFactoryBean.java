package com.itniuma.factory;

import com.itniuma.dao.BookDao;
import com.itniuma.dao.impl.BookDaoImpl;
import org.springframework.beans.factory.FactoryBean;

public class BookDaoFactoryBean implements FactoryBean<BookDao> {
    // 使用该方法创建对象
    @Override
    public BookDao getObject() throws Exception {
        System.out.println("get bean by factory-bean class");
        return new BookDaoImpl();
    }

    // 使用该方法指定对象类型
    @Override
    public Class<?> getObjectType() {
        return BookDao.class;
    }
}
