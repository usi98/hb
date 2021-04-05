package com.bjtuhbxy.hb.controller;

import com.bjtuhbxy.hb.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class RandomController {
    @Autowired
    RedisService redisService;



    @CrossOrigin
    @GetMapping(value = "api/rand")
    public Integer getRandom(){


        return (Integer) redisService.get("power:power_1-101");
    }
}
