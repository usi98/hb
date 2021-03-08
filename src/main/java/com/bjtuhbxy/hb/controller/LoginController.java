package com.bjtuhbxy.hb.controller;

import com.bjtuhbxy.hb.pojo.User;
import com.bjtuhbxy.hb.result.Result;
import com.bjtuhbxy.hb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

@Controller
public class LoginController {
    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping(value = "api/login")
    @ResponseBody
    public Result login(@RequestBody User requestUser) {
    // 对 html 标签进行转义，防止 XSS 攻击
        String username = requestUser.getUsername();
        User user = userService.get(username, requestUser.getPassword());
        System.out.println(username);
        System.out.println(requestUser.getPassword());
        System.out.println(user);
        if (null==user) {
            String message = "账号密码错误";
            System.out.println("test");
            return new Result(400);
        } else {
            return new Result(200);
        }
    }
}

