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

        System.out.println(username + password);
        // 调用service进行查询
        User user = userService.login(username, password);

        // 登录是否成功
        if (user != null) {
            // 登录成功
            System.out.println("登录成功");
            response.sendRedirect(request.getContextPath()+"");
        } else {
            // 登录失败
            System.out.println("登录失败");

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
