package com.bjtuhbxy.hb.service;

import com.bjtuhbxy.hb.dao.ViolationLogDAO;
import com.bjtuhbxy.hb.entity.ViolationLog;
import com.bjtuhbxy.hb.util.MyPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ViolationLogService {

    @Autowired
    ViolationLogDAO violationLogDAO;

    public ViolationLog save(ViolationLog violationLog){
        violationLogDAO.save(violationLog);
        return violationLog;
    }

    public MyPage list(int page, int size){
        MyPage<ViolationLog> logs;
        Sort sort = Sort.by(Sort.Direction.ASC, "updatetime");
        Page<ViolationLog> logsInDb = violationLogDAO.findAll(PageRequest.of(page, size, sort));
        logs = new MyPage<>(logsInDb);

        return logs;
    }
}
