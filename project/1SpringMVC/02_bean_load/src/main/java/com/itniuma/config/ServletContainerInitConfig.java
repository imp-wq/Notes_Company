package com.itniuma.config;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

public class ServletContainerInitConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringMvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}

//
// public class ServletContainerInitConfig extends AbstractDispatcherServletInitializer {
//     //加载SpringMVC容器配置
//     @Override
//     protected WebApplicationContext createServletApplicationContext() {
//         // 加载Spring MVC容器。
//         AnnotationConfigWebApplicationContext container = new AnnotationConfigWebApplicationContext();
//         // 加载SpringMVC配置类
//         container.register(SpringMvcConfig.class);
//         return container;
//     }
//
//     //设置哪些请求归属SpringMVC处理
//     @Override
//     protected String[] getServletMappings() {
//         // 设置所有请求归Spring mvc处理
//         return new String[]{"/"};
//     }
//
//     //加载Spring容器配置
//     //代码逻辑与加载spring mvc相同，指定的配置类不同。
//     @Override
//     protected WebApplicationContext createRootApplicationContext() {
//         AnnotationConfigWebApplicationContext container = new AnnotationConfigWebApplicationContext();
//         container.register(SpringConfig.class);
//         return container;
//     }
// }
