package com.itheima.web;

import com.itheima.pojo.User;
import com.itheima.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "registerServlet", value = "/registerServlet")
public class registerServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("login servlet被调用");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // 获取用户输入的验证码
        String checkCode = request.getParameter("checkCode");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        HttpSession session = request.getSession();
        String checkCodeGen = (String) session.getAttribute("checkCodeGen");
        System.out.println(checkCodeGen);
        response.setContentType("text/html;charset=utf-8");

        // 判断验证码是否正确
        if (!checkCodeGen.equalsIgnoreCase(checkCode)) {
            // 错误，不允许注册
            response.getWriter().write("验证码错误！~");
            return;
        }

        // 调用service进行注册
        boolean flag = userService.register(user);

        if (flag) {
            response.getWriter().write("注册成功！~");
        } else {
            response.getWriter().write("用户名重复，注册！~");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
