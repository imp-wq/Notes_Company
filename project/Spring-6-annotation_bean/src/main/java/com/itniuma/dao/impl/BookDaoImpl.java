package com.itniuma.dao.impl;

import com.itniuma.dao.BookDao;
import org.springframework.stereotype.Repository;
import org.springframework.*;
import javax.annotation.*;

import java.util.*;

@Repository
public class BookDaoImpl implements BookDao {
    private int[] arr;
    private List<String> myList;
    private Set<String> mySet;
    private Map<String, String> myMap;
    private Properties myProperties;

    public void setArr(int[] arr) {
        this.arr = arr;
    }

    public void setMyList(List<String> myList) {
        this.myList = myList;
    }

    public void setMySet(Set<String> mySet) {
        this.mySet = mySet;
    }

    public void setMyMap(Map<String, String> myMap) {
        this.myMap = myMap;
    }

    public void setMyProperties(Properties properties) {
        this.myProperties = properties;
    }

    @PostConstruct
    public void init() {
        System.out.println("init method is running...");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("destroy method is running...");
    }

    public void save() {
        System.out.println("run save method...");
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "BookDaoImpl{" +
                "arr=" + Arrays.toString(arr) +
                ", myList=" + myList +
                ", mySet=" + mySet +
                ", myMap=" + myMap +
                ", myProperties=" + myProperties +
                '}';
    }
}
