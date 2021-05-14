package com.bjtuhbxy.hb.service;

import com.bjtuhbxy.hb.dao.MeterDAO;
import com.bjtuhbxy.hb.entity.Meter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeterService {
    @Autowired
    MeterDAO meterDAO;

    public String getInfoByaddr(String addr){
        Meter meter = meterDAO.findByAddress(addr);
        return meter.getInfo();
    }

    public Meter save(Meter meter){
        return meterDAO.save(meter);
    }
}
