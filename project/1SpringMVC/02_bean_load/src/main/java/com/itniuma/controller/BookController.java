package com.itniuma.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/book")
public class BookController {

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
}
