package com.bjtuhbxy.hb.controller.search;

import com.alibaba.fastjson.JSON;
import com.bjtuhbxy.hb.result.Result;
import com.bjtuhbxy.hb.result.ResultFactory;
import com.bjtuhbxy.hb.service.RechargeLogService;
import com.bjtuhbxy.hb.service.RoomService;
import com.bjtuhbxy.hb.util.MyPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomInfoController {
    Logger logger = LoggerFactory.getLogger(RoomInfoController.class);

    @Autowired
    RoomService roomService;

    @CrossOrigin
    @GetMapping("/api/roomInfo/{size}/{page}")
    public Result listRooms(@PathVariable("size") int size, @PathVariable("page") int page) {


        MyPage myPage = roomService.listWithSInfo(page - 1, size);
        logger.info("Rooms myPageJSON:{}", JSON.toJSONString(myPage));
        logger.info("Rooms RESULTJSON:{}", ResultFactory.buildSuccessResult(myPage).getResult());

        return ResultFactory.buildSuccessResult(myPage);
    }
}
