package com.bjtuhbxy.hb.service;

import com.bjtuhbxy.hb.dao.OperateLogDAO;
import com.bjtuhbxy.hb.entity.OperateLog;
import com.bjtuhbxy.hb.util.MyPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class OperateLogService {

    @Autowired
    OperateLogDAO operateLogDAO;

    public OperateLog save(OperateLog operateLog){
        operateLogDAO.save(operateLog);
        return operateLog;
    }

    public MyPage list(int page, int size){
        MyPage<OperateLog> logs;
        Sort sort = Sort.by(Sort.Direction.ASC, "date");
        Page<OperateLog> logsInDb = operateLogDAO.findAll(PageRequest.of(page, size, sort));
        logs = new MyPage<>(logsInDb);

        return logs;
    }
}
