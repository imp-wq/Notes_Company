package com.itheima.web;

import com.itheima.util.CheckCodeUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CheckCodeServlet", value = "/CheckCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletOutputStream outputStream = response.getOutputStream();
        // 程序生成的验证码，存入session，便于注册sevlet用于比较
        String checkCode = CheckCodeUtil.outputVerifyImage(100, 50, outputStream, 4);

        HttpSession session = request.getSession();
        session.setAttribute("checkCodeGen", checkCode);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
