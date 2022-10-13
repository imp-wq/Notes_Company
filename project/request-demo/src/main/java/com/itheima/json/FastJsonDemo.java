package com.itheima.json;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.User;

public class FastJsonDemo {
    public static void main(String[] args) {
        User user = new User();
        user.setId(1);
        user.setUsername("zhangsan");
        user.setPassword("1234");

        // 1.java对象转化为json
        String jsonString = JSON.toJSONString(user);
        System.out.println(jsonString);

        // 2.JSON字符串转java对象
        User user1 = JSON.parseObject("{\"id\":1,\"password\":\"1234\",\"username\":\"zhangsan\"}", User.class);
        System.out.println(user1);
    }
}
