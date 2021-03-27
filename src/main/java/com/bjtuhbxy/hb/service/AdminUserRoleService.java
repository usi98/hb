package com.bjtuhbxy.hb.service;

import com.bjtuhbxy.hb.dao.AdminUserRoleDAO;
import com.bjtuhbxy.hb.entity.AdminUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminUserRoleService {

    @Autowired
    AdminUserRoleDAO adminUserRoleDAO;


    public List<AdminUserRole> listAllByUid(int uid) {
        return adminUserRoleDAO.findAllByUid(uid);
    }
}
