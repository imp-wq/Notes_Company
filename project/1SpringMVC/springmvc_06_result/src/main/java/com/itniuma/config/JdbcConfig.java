package com.itniuma.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

public class JdbcConfig {
    private String driver;
    private String url;
    private String username;
    private String password;

    @Value("${jdbc.driver}")
    public void setDriver(String driver) {
        this.driver = driver;
    }

    @Value("${jdbc.url}")
    public void setUrl(String url) {
        this.url = url;
    }

    @Value("${jdbc.username}")
    public void setUsername(String username) {
        this.username = username;
    }

    @Value("${jdbc.password}")
    public void setPassword(String password) {
        this.password = password;
    }

    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();

        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }
}
