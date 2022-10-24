package com.itniuma.controller;

import com.itniuma.domain.Account;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.print.Book;
import java.sql.Array;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/save")
    @ResponseBody
    public String save() {
        return "{'msg':'book save...'}";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete() {
        return "{'msg':'book delete...'}";
    }

    @RequestMapping("/dateParams")
    @ResponseBody
    public String dateParams(Date date,
                             @DateTimeFormat(pattern = "yyyy-MM-dd") Date date1) {
        System.out.println(date);
        System.out.println(date1);
        return "{'msg':'date: " + date +
                "\tdate1: " + date1 + "'}";
    }

    @RequestMapping("/page")
    public String page() {
        return "index.jsp";
    }

    @RequestMapping("/pojoJson")
    @ResponseBody
    public Account pojoJson() {
        Account account = new Account();
        account.setName("zhangsan");
        account.setId(123123);
        return account;
    }

    @RequestMapping("/pojoList")
    @ResponseBody
    public List<Account> pojoList() {
        Account account1 = new Account();
        account1.setName("zhangsan");
        account1.setId(1);
        Account account2 = new Account();
        account2.setName("lisi");
        account2.setId(2);
        return Arrays.asList(account1, account2);
    }

    //    rest风格
    @RequestMapping(value = "/users/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String saveUsers(@PathVariable Integer id) {
        System.out.println(id);
        return "{'msg':' " + id + " book save...'}";
    }

}