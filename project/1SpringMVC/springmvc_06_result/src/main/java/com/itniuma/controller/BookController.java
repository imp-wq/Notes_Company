package com.itniuma.controller;

import com.itniuma.domain.Book;
import com.itniuma.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private BookService bookService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public Result save(@RequestBody Book book) {
        boolean flag = bookService.save(book);
        return new Result(flag ? Code.SAVE_OK.getCode() : Code.SAVE_ERR.getCode(), flag);
    }

    @PutMapping
    public Result update(@RequestBody Book book) {
        boolean flag = bookService.update(book);
        return new Result(flag ? Code.UPDATE_OK.getCode() : Code.UPDATE_ERR.getCode(), flag);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        boolean flag = bookService.delete(id);
        return new Result(flag ? Code.DELETE_OK.getCode() : Code.DELETE_ERR.getCode(), flag);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) throws Exception {
        if (id == 1) {
            throw new Exception();
        }

        Book book = bookService.getById(id);
        Integer code = book != null ? Code.GET_OK.getCode() : Code.GET_ERR.getCode();
        String message = book != null ? "查询成功" : "查询失败";
        return new Result(code, book, message);
    }

    @GetMapping
    public Result getAll() {
        List<Book> bookList = bookService.getAll();
        Integer code = bookList != null ? Code.GET_OK.getCode() : Code.GET_ERR.getCode();
        String message = bookList != null ? "查询成功" : "查询失败";
        return new Result(code, bookList, message);
    }
}
