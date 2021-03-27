package com.bjtuhbxy.hb.dao;

import com.bjtuhbxy.hb.entity.AdminUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminUserRoleDAO extends JpaRepository<AdminUserRole,Integer> {
    public List<AdminUserRole> findByUid(int uid);

    List<AdminUserRole> findAllByUid(int uid);
    void deleteAllByUid(int uid);
}
