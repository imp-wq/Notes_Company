package com.itniuma.config;

import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
// @ComponentScan({"com.itniuma.service","com.itniuma.dao"})
@ComponentScan(value = "com.itniuma", excludeFilters = @ComponentScan.Filter(
        type = FilterType.ANNOTATION,
        classes = Controller.class
))
@Import({JdbcConfig.class, MybatisConfig.class})
@PropertySource("classpath:jdbc.properties")
@EnableTransactionManagement
public class SpringConfig {
}
