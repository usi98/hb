package com.bjtuhbxy.hb.quartzdemo;

import com.bjtuhbxy.hb.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class jobdemo1 {

    @Autowired
    RedisService redisService;


    //每隔五秒
//    @Scheduled(cron = "0/2 * * * * ? ")
    public void process() {

        System.out.println("向你发脏数据！" + new Date());
        for (int i = 0; i < 3; i++) {
            System.out.println("async" + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    //每隔1秒
    @Scheduled(cron = "0/1 * * * * ? ")
    public void updatePower(){
        int max = 880;
        int min = 300;
        int d = (int)(Math.random()*(max - min)+min);


        redisService.set("power:power_1-101",d);
        System.out.println(d+":"+new Date());
    }

}
