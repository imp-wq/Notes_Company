package com.itniuma.controller;

import com.itniuma.domain.Book;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/user")
public class UserController {

    @RequestMapping(method = RequestMethod.POST)
    public String save(Book book) {
        System.out.println("book save...");
        return "save success!";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Integer id) {
        System.out.println("delete" + id);
        return "delete" + id;
    }
}
