package com.bjtuhbxy.hb.service;

import com.bjtuhbxy.hb.dao.RechargeLogDAO;
import com.bjtuhbxy.hb.entity.RechargeLog;
import com.bjtuhbxy.hb.util.MyPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class RechargeLogService {
    @Autowired
    RechargeLogDAO rechargeLogDAO;

    public RechargeLog save(RechargeLog rechargeLog){
        return rechargeLogDAO.save(rechargeLog);
    }

    public MyPage list(int page, int size){
        MyPage<RechargeLog> rechargeLogs;
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Page<RechargeLog> logPage = rechargeLogDAO.findAll(PageRequest.of(page,size,sort));
        rechargeLogs = new MyPage<>(logPage);
        return rechargeLogs;
    }
}
