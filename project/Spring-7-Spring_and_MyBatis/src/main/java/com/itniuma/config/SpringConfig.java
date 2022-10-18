package com.itniuma.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.itniuma")
@Import({JdbcConfig.class, MybatisConfig.class})
@PropertySource("jdbc.properties")
public class SpringConfig {

}
