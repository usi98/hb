package com.bjtuhbxy.hb.controller.search;

import com.alibaba.fastjson.JSON;
import com.bjtuhbxy.hb.entity.Room;
import com.bjtuhbxy.hb.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SurplusController {
    @Autowired
    RoomService roomService;

    Logger logger = LoggerFactory.getLogger(SurplusController.class);
    //余额查询
    @CrossOrigin
    @ResponseBody
    @PostMapping("/api/surplus")
    public String surplus(@RequestBody Room room) throws Exception {
        int bid = room.getBuildingId();
        int rid= room.getRoomId();

        logger.info("surplus info {},{}",bid,rid);
        Room room1 = roomService.getRoomAndStudentsInfo(bid, rid);
        String json = JSON.toJSONString(room1);
        List<Room> rooms = new ArrayList<>();
        rooms.add(room1);
        logger.info(rooms.toString());
        return JSON.toJSONString(rooms);
    }
}
