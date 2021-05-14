package com.bjtuhbxy.hb.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bjtuhbxy.hb.entity.Room;
import com.bjtuhbxy.hb.result.Result;
import com.bjtuhbxy.hb.result.ResultFactory;
import com.bjtuhbxy.hb.serial.ComMeter;
import com.bjtuhbxy.hb.serial.CommUtil;
import com.bjtuhbxy.hb.serial.Operation;
import com.bjtuhbxy.hb.service.MeterService;
import com.bjtuhbxy.hb.service.ParamsService;
import com.bjtuhbxy.hb.service.RoomService;
import org.apache.shiro.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class MeterController {
    Logger logger = LoggerFactory.getLogger(MeterController.class);

    @Autowired
    CommUtil commUtil;

    @Autowired
    ComMeter comMeter;

    @Autowired
    RoomService roomService;

    @Autowired
    ParamsService paramsService;

    @CrossOrigin
    @GetMapping("/api/AllClose")
    public Result AllClose() {
        List<Room> roomList = roomService.getRoomsByEnable(0);
        for (Room r: roomList) {
            r.setEnable(1);
            roomService.save(r);
        }
        return ResultFactory.buildSuccessResult(200);
    }

    @CrossOrigin
    @GetMapping("/api/AllTrip")
    public Result AllTrip() {
        List<Room> roomList = roomService.getRoomsByEnable(1);
        for (Room r: roomList) {
            r.setEnable(0);
            roomService.save(r);
        }
        return ResultFactory.buildSuccessResult(200);
    }

    @CrossOrigin
    @PostMapping("/api/Close")
    public Result Close(@RequestBody String reqJson) {
        JSONObject jsonObject= JSON.parseObject(reqJson);
        Integer buildingId = jsonObject.getInteger("buildingId");
        Integer roomId = jsonObject.getInteger("roomId");
        Room room = roomService.getRoomInfo(buildingId, roomId);

        String line = Operation.trip(room.getMeterAddress(),"000000","02", "C1C2C3C4","1B");

        String infostr = room.getMeterInfo();
        System.out.println(infostr);
        Map<String, String>  infomap = (Map<String, String>) JSON.parse(infostr);
        comMeter.setdata(infomap);
        String r = commUtil.send(line);

        if ("FEFEFEFE".equals(StringUtils.clean(r).substring(0, 8))) {
            r = r.substring(8);
            String A5_0 = r.substring(2, 14);
            String C = r.substring(16, 18);
            if ("9C".equals(C)) {
                room.setEnable(1);
                roomService.save(room);
            }
        }
        return ResultFactory.buildSuccessResult(200);
    }

    @CrossOrigin
    @PostMapping("/api/Trip")
    public Result Trip(@RequestBody String reqJson) {
        JSONObject jsonObject= JSON.parseObject(reqJson);
        Integer buildingId = jsonObject.getInteger("buildingId");
        Integer roomId = jsonObject.getInteger("roomId");
        Room room = roomService.getRoomInfo(buildingId, roomId);

        String line = Operation.trip(room.getMeterAddress(),"000000","02", "C1C2C3C4","1A");

        String infostr = room.getMeterInfo();
        System.out.println(infostr);
        Map<String, String>  infomap = (Map<String, String>) JSON.parse(infostr);
        comMeter.setdata(infomap);
        String r = commUtil.send(line);

        if ("FEFEFEFE".equals(StringUtils.clean(r).substring(0, 8))) {
            r = r.substring(8);
            String A5_0 = r.substring(2, 14);
            String C = r.substring(16, 18);

            if ("9C".equals(C)) {
                room.setEnable(0);
                roomService.save(room);
            }
        }
        return ResultFactory.buildSuccessResult(200);
    }
}
