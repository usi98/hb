package com.bjtuhbxy.hb.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bjtuhbxy.hb.entity.User;
import com.bjtuhbxy.hb.result.Result;
import com.bjtuhbxy.hb.result.ResultFactory;
import com.bjtuhbxy.hb.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

@Controller
public class RegisterController {
    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping("api/register")
    @ResponseBody
    public Result register(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        username = HtmlUtils.htmlEscape(username);
        user.setUsername(username);

        boolean exist = userService.isExist(username);
        if (exist) {
            String message = "用户名已被使用";
            return ResultFactory.buildFailResult(message);
        }

        // 生成盐,默认长度 16 位
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        // 设置 hash 算法迭代次数
        int times = 2;
        // 得到 hash 后的密码
        String encodedPassword = new SimpleHash("md5", password, salt, times).toString();
        // 存储用户信息，包括 salt 与 hash 后的密码
        user.setSalt(salt);
        user.setPassword(encodedPassword);
        userService.add(user);

        return ResultFactory.buildSuccessResult(user);
    }

    @CrossOrigin
    @PostMapping("api/updatePassword")
    @ResponseBody
    public Result updatePassword(@RequestBody String reqJson) {
        String message = "success";

        JSONObject jsonObject = JSON.parseObject(reqJson);
        //修改密码
        String username = jsonObject.getString("username");
        String oldpassword = jsonObject.getString("oldpassword");
        String newpassword = jsonObject.getString("newpassword");
        String newpasswordtwo = jsonObject.getString("newpasswordtwo");
        User user = userService.getByUserName(username);
        int times = 2;
        // 得到 hash 后的密码
        String encodedPassword = new SimpleHash("md5", oldpassword, user.getSalt(), times).toString();

        if(!user.getPassword().equals(encodedPassword)){
            message = "原密码填写错误";
            return ResultFactory.buildSuccessResult(message);
        }

        if(!newpasswordtwo.equals(newpasswordtwo)){
            message = "两次密码不一致";
            return ResultFactory.buildSuccessResult(message);
        }
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        encodedPassword = new SimpleHash("md5", newpassword, salt, times).toString();
        user.setSalt(salt);
        user.setPassword(encodedPassword);

        userService.save(user);
        return ResultFactory.buildSuccessResult(message);
    }
}
