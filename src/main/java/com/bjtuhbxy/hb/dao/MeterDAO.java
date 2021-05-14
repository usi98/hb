package com.bjtuhbxy.hb.dao;

import com.bjtuhbxy.hb.entity.Meter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeterDAO extends JpaRepository<Meter, Integer> {
    Meter findByAddress(String adress);
}
