package com.itniuma.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public class MybatisConfig {
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

        // 设置typealias，设置MyBatis对应的java类，以及用于给mybatis对应的java类起别名的。
        sqlSessionFactoryBean.setTypeAliasesPackage("com.itniuma.domain");
        // 以形参形式传入dataSource，自动装配，注入jdbc dataSource对象，即driver, url, username, password。
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }

    // 创建Mapper对应的类。
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        // 设置mapper接口所在的包
        mapperScannerConfigurer.setBasePackage("com.itniuma.dao");
        return mapperScannerConfigurer;
    }
}
