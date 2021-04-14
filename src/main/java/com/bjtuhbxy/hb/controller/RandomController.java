package com.bjtuhbxy.hb.controller;

import com.bjtuhbxy.hb.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class RandomController {
    @Autowired
    RedisService redisService;



    @CrossOrigin
    @GetMapping(value = "api/rand/{bid}/{rid}")
    public Integer getRandom(@PathVariable("bid") Integer bid, @PathVariable("rid") Integer rid){


        bid %=8;
        String bidstr =bid.toString();
        String key = "power:power_"+bidstr+"-101";

        Integer value = (Integer) redisService.get(key);
        return value;
    }
}
