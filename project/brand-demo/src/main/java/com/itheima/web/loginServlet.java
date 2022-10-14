package com.itheima.web;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.User;
import com.itheima.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "loginServlet", value = "/loginServlet")
public class loginServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("login servlet被调用");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // 复选框默认为on
        String remember = request.getParameter("remember");

        System.out.println(username + password);
        // 调用service进行查询
        User user = userService.login(username, password);

        // 登录是否成功
        if (user != null) {
            // 登录成功
            System.out.println("登录成功");
            // 记住用户功能
            // 防止null pointer exception
            if ("on".equals(remember)) {
                Cookie c_username = new Cookie("username", username);
                Cookie c_password = new Cookie("password", password);

                // 通过maxAge方法设置Cookie的存活时间
                final int EXPIRE_TIME = 60 * 60 * 24 * 7;
                c_username.setMaxAge(EXPIRE_TIME);
                c_password.setMaxAge(EXPIRE_TIME);

                response.addCookie(c_username);
                response.addCookie(c_password);
            }

            // response.sendRedirect(request.getContextPath()+"");

            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("欢迎您, " + user.getUsername() + " ~");
        } else {
            // 登录失败
            System.out.println("登录失败");
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("用户名或密码不正确");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
