package com.bjtuhbxy.hb.service;

import com.bjtuhbxy.hb.HbApplication;
import com.bjtuhbxy.hb.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HbApplication.class)// 就是你springboot的启动类
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void getuser(){
        User user = userService.get("admin","123");
        System.out.println(user);
    }

    @Test
    public void add(){
        User user = new User();
        user.setUsername("ddd");
        user.setPassword("ddd");
        userService.add(user);
        System.out.println(user);
    }
    @Test
    public void findByUsername(){
        String username = "admin1";
        User user = userService.findByUsername(username);
        System.out.println(user);
    }
}
