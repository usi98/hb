package com.bjtuhbxy.hb.dao;

import com.bjtuhbxy.hb.entity.Params;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParamsDAO extends JpaRepository<Params,Integer> {
    Params getParamsByName(String name);
}
