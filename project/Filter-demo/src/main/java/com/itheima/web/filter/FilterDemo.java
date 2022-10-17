package com.itheima.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class FilterDemo implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        // 放行登录和注册相关的资源
        // 定义相关资源的白名单，html/css等静态资源，相关servlet
        String[] urls = {"/login.html", "/imgs/", "/css/", "/loginServlet", "/register.html", "registerServlet", "checkCodeServlet"};
        String url = req.getRequestURL().toString();
        for (String u : urls) {
            // 判断访问的url，是否在白名单中
            if (url.contains(u)) {
                // 放行
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }

        HttpSession session = req.getSession();
        Object user = session.getAttribute("user");
        if (user != null) {
            // 登录成功，放行
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            // 登录失败，使用请求转发，跳转至登录界面
            req.setAttribute("login_msg", "您尚未登录！");
            req.getRequestDispatcher("/login.html").forward(servletRequest, servletResponse);
        }

    }

    @Override
    public void destroy() {

    }
}
