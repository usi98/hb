package com.bjtuhbxy.hb.dao;

import com.bjtuhbxy.hb.entity.ViolationLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViolationLogDAO extends JpaRepository<ViolationLog, Integer> {

}