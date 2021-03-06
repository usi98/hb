package com.bjtuhbxy.hb.controller;

import com.bjtuhbxy.hb.entity.OperateLog;
import com.bjtuhbxy.hb.entity.User;
import com.bjtuhbxy.hb.result.Result;
import com.bjtuhbxy.hb.result.ResultFactory;
import com.bjtuhbxy.hb.service.OperateLogService;
import com.bjtuhbxy.hb.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class LoginController {

    Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserService userService;

    @Autowired
    OperateLogService operateLogService;

    @CrossOrigin
    @PostMapping(value = "api/login")
    @ResponseBody
    public Result login(@RequestBody User requestUser) {
    // 对 html 标签进行转义，防止 XSS 攻击
        String username = requestUser.getUsername();
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, requestUser.getPassword());
        //输出用户名和密码 1暂时写在这里以便调试程序
        logger.info("登录");
        System.out.println("用户名:"+username);
        System.out.println("密码:"+requestUser.getPassword());

        //是否记住我
        //usernamePasswordToken.setRememberMe(true);
        usernamePasswordToken.setRememberMe(false);

            try {
                subject.login(usernamePasswordToken);

                OperateLog operateLog = new OperateLog();
                operateLog.setUsername(username);
                operateLog.setDescription("登录成功");
                Date date = new Date(System.currentTimeMillis());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                operateLog.setDate(sdf.format(date));
                operateLogService.save(operateLog);

                return ResultFactory.buildSuccessResult(username);
            } catch (AuthenticationException e) {
                return ResultFactory.buildFailResult("登录失败");
            }

    }

    @CrossOrigin
    @ResponseBody
    @GetMapping("api/logout")
    public Result logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        String message = "成功登出";
        return ResultFactory.buildSuccessResult(message);
    }

    @CrossOrigin
    @ResponseBody
    @GetMapping(value = "api/authentication")
    public String authentication(){

//        String username = SecurityUtils.getSubject().getPrincipal().toString();
        if(StringUtils.isNotEmpty("username")){
            //登录认证校验
            return "身份认证成功";
        }
        return "身份认证失败";

    }

}
