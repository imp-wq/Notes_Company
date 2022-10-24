package com.itniuma.controller;

import com.itniuma.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.swing.text.Style;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/commonParams")
    @ResponseBody
    public String commmonParams(@RequestParam("name") String username, String password) {
        // 普通参数，直接以形参的形式得到。
        System.out.println("username: " + username + "\tpassword: " + password);
        return "{'msg':'hello ," + username + "'}";
    }

    @RequestMapping("/pojoParams")
    @ResponseBody
    public String pojoParams(User user) {
        System.out.println("user: " + user);
        return "{'msg':'hello, " + user.getUserame() + "'}";
    }

    @RequestMapping("/pojoInPojoParams")
    @ResponseBody
    public String pojoInPojoParams(User user) {
        System.out.println("user: " + user);
        return "{'msg':'hello, " + user.getUserame() + "'}";
    }

    @RequestMapping("/arrayParams")
    @ResponseBody
    public String arrayParams(String[] likes) {
        System.out.println("likes: " + Arrays.toString(likes));
        return "{'msg':'your liks is }" + Arrays.toString(likes);
    }

    @RequestMapping("/listParams")
    @ResponseBody
    public String listParams(@RequestParam List<String> likes) {
        System.out.println("likes: " + likes);
        return "{'msg':'your liks is " + likes + " }";
    }

    @RequestMapping("/jsonParams")
    @ResponseBody
    public String jsonParams(@RequestBody List<String> likes) {
        System.out.println(likes);
        return "{'msg':'your liks is " + likes + " }";
    }

    @RequestMapping("/jsonPojoParams")
    @ResponseBody
    public String jsonPojoParams(@RequestBody User user) {
        System.out.println(user);
        return "{'msg':'hello, " + user.getUserame() + "'}";
    }
}
