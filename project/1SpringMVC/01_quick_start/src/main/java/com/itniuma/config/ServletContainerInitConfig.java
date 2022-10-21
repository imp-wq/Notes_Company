package com.itniuma.config;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

public class ServletContainerInitConfig extends AbstractDispatcherServletInitializer {
    //加载SpringMVC容器配置
    @Override
    protected WebApplicationContext createServletApplicationContext() {
        // 加载Spring MVC容器。
        AnnotationConfigWebApplicationContext container = new AnnotationConfigWebApplicationContext();
        // 加载SpringMVC配置类
        container.register(SpringMvcConfig.class);
        return container;
    }

    //设置哪些请求归属SpringMVC处理
    @Override
    protected String[] getServletMappings() {
        // 设置所有请求归Spring mvc处理
        return new String[]{"/"};
    }

    //加载Spring容器配置
    @Override
    protected WebApplicationContext createRootApplicationContext() {
        // 暂时不用管
        return null;
    }
}
