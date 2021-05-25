package com.bjtuhbxy.hb.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bjtuhbxy.hb.entity.User;
import com.bjtuhbxy.hb.result.Result;
import com.bjtuhbxy.hb.result.ResultFactory;
import com.bjtuhbxy.hb.service.UserService;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmailController {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserService userService;

    @CrossOrigin
    @PostMapping("api/sendEmail")
    private Result send(@RequestBody String reqJson){
        JSONObject jsonObject = JSON.parseObject(reqJson);
        String username = jsonObject.getString("user");
        String title = jsonObject.getString("title");
        String text = jsonObject.getString("text");

//        mailSender = new JavaMailSenderImpl();
//        ((JavaMailSenderImpl) mailSender).setHost("smtp.qq.com");
//        ((JavaMailSenderImpl) mailSender).setUsername("1308580920@qq.com");
//        ((JavaMailSenderImpl) mailSender).setPassword("kvdfydwhpqiridai");

        //邮件发送
        SimpleMailMessage message = new SimpleMailMessage();
        // 发件人
        message.setFrom("k74qrlin@163.com");
        // 收件人
        message.setTo("1308580920@qq.com");
        // 邮件标题
        message.setSubject(title);
        // 邮件内容
        message.setText(text);
        // 抄送人
        //message.setCc("k74qrlin@gmail.com");
        //发送
        //mailSender.send(message);

        if("all".equals(username)){
            List<User> users = userService.findAll();
            for (User user : users){
                String email = user.getEmail();
                if(StringUtils.isNotEmpty(email))
                message.setTo(email);
                //mailSender.send(message);
            }
        }else {
            User user = userService.findByUsername(username);
            String email = user.getEmail();
            mailSender.send(message);
        }

        return ResultFactory.buildSuccessResult("send success");
    }
}
