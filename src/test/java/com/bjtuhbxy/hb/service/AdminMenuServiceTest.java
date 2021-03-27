package com.bjtuhbxy.hb.service;

import com.bjtuhbxy.hb.HbApplication;
import com.bjtuhbxy.hb.entity.AdminRoleMenu;
import com.bjtuhbxy.hb.entity.AdminUserRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HbApplication.class)
public class AdminMenuServiceTest {

    @Autowired
    AdminUserRoleService adminUserRoleService;
    @Autowired
    AdminRoleMenuService adminRoleMenuService;

    @Test
    public void getRidsTest(){
        List<Integer> rids = adminUserRoleService.listAllByUid(3)
                .stream().map(AdminUserRole::getRid).collect(Collectors.toList());



        List<Integer> mids = adminRoleMenuService.findAllByRid(Arrays.asList(2,3))
                .stream().map(AdminRoleMenu::getMid).collect(Collectors.toList());

        for (Integer mid : mids) {
            System.out.println(mid);
        }
    }
}
