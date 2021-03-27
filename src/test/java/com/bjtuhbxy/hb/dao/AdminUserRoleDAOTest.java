package com.bjtuhbxy.hb.dao;

import com.bjtuhbxy.hb.HbApplication;
import com.bjtuhbxy.hb.entity.AdminUserRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes =  HbApplication.class)
public class AdminUserRoleDAOTest {
    @Autowired
    AdminUserRoleDAO adminUserRoleDAO;

    @Test
    public void addUserRoleMapper(){
        AdminUserRole adminUserRole = new AdminUserRole();
        for(int i=3869;i<=5788;i++){

            System.out.println(i);
            adminUserRole.setId(0);
            adminUserRole.setUid(i);
            adminUserRole.setRid(10);
            adminUserRoleDAO.save(adminUserRole);

        }
    }
}
