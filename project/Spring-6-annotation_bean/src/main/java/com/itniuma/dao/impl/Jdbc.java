package com.itniuma.dao.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

@Repository("jdbc")
@PropertySource("jdbc.properties")
public class Jdbc {
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.url}")
    private String url;

    public void save() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "Jdbc{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", driver='" + driver + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
