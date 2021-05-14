package com.bjtuhbxy.hb.service;

import com.bjtuhbxy.hb.dao.ParamsDAO;
import com.bjtuhbxy.hb.entity.Params;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParamsService {
    @Autowired
    ParamsDAO paramsDAO;

    public Params getparamByName(String name){
        return paramsDAO.getParamsByName(name);
    }

    public Params save(Params params){
        return paramsDAO.save(params);
    }
}
