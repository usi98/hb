package com.bjtuhbxy.hb.controller;

import com.alibaba.fastjson.JSON;
import com.bjtuhbxy.hb.entity.Room;
import com.bjtuhbxy.hb.redis.RedisService;
import com.bjtuhbxy.hb.serial.ComMeter;
import com.bjtuhbxy.hb.serial.CommUtil;
import com.bjtuhbxy.hb.serial.Operation;
import com.bjtuhbxy.hb.service.RoomService;
import org.apache.shiro.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
public class RandomController {
    Logger logger = LoggerFactory.getLogger(RandomController.class);

    @Autowired
    RedisService redisService;

    @Autowired
    RoomService roomService;

    @Autowired
    CommUtil commUtil;

    @Autowired
    ComMeter comMeter;

    @CrossOrigin
    @GetMapping(value = "api/getpowerInfo/{bid}/{rid}")
    public Integer getRandom(@PathVariable("bid") Integer bid, @PathVariable("rid") Integer rid){
        Room room = roomService.getRoomInfo(bid,rid);
        String address = room.getMeterAddress();
        String line = Operation.read(address, "02030000");
        String r = commUtil.send(line);
        logger.info("响应功率数据：{}",r);

        if ("FEFEFEFE".equals(StringUtils.clean(r).substring(0, 8))) {
            r = r.substring(8);
            String A5_0 = r.substring(2, 14);
        }
        String kwp = r.substring(28,34);
        kwp = Operation.HtoL(Operation.red33H(kwp));
        for (int i  = 0;i<6;i++){
            if (kwp.substring(0,1).equals("0")){
                kwp = kwp.substring(1);
            }
        }
        Integer p = Integer.parseInt(kwp);
        return p/100;
    }
}
