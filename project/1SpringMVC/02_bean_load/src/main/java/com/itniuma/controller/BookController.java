package com.itniuma.controller;

import com.itniuma.domain.Book;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// 使用resful开发

// @Controller
// @ResponseBody
@RestController
@RequestMapping("/book")
public class BookController {

    // @RequestMapping(method = RequestMethod.POST)
    @GetMapping
    @PostMapping
    public String save(Book book) {
        System.out.println("book save...");
        return "save success!";
    }

    // @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        System.out.println("delete" + id);
        return "delete" + id;
    }
}
