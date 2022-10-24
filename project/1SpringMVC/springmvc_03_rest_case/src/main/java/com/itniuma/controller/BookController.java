package com.itniuma.controller;

import com.itniuma.domain.Book;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @PostMapping
    public String save(@RequestBody Book book) {
        System.out.println(book);
        return "save success!" + book;
    }

    @GetMapping
    public List<Book> getAll() {
        Book book1 = new Book();
        book1.setName("西游记");
        book1.setId(1);

        Book book2 = new Book();
        book2.setName("水浒传");
        book2.setId(2);

        return Arrays.asList(book1, book2);
    }
}
