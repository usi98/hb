package com.bjtuhbxy.hb.dao;

import com.bjtuhbxy.hb.HbApplication;
import com.bjtuhbxy.hb.entity.AdminUserRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HbApplication.class)// 就是你springboot的启动类
public class AdminUserRoleTest {

    @Autowired
    AdminUserRoleDAO adminUserRoleDAO;

    @Test
    public void aurTest(){
        List<AdminUserRole> userRole = adminUserRoleDAO.findByUid(24);
        System.out.println(userRole);
        List<AdminUserRole> userRole1 = adminUserRoleDAO.findAllByUid(24);
        System.out.println(userRole1);
    }
}
