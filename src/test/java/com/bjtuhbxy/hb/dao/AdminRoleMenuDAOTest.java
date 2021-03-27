package com.bjtuhbxy.hb.dao;

import com.bjtuhbxy.hb.HbApplication;
import com.bjtuhbxy.hb.entity.AdminRoleMenu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HbApplication.class)// 就是你springboot的启动类
public class AdminRoleMenuDAOTest {
    @Autowired
    AdminRoleMenuDAO adminRoleMenuDAO;

    @Test
    public void roleMenuTest(){
        List<AdminRoleMenu> rms  =  adminRoleMenuDAO.findAllByRid(1);
        rms.forEach(x->{
            System.out.println(x.getId());
        });

    }
}
