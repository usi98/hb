package com.bjtuhbxy.hb.schedule;

import com.bjtuhbxy.hb.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Job {

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
    public void updatePower1(){
        int max = 880;
        int min = 300;
        int d = (int)(Math.random()*(max - min)+min);
        redisService.set("power:power_1-101",d);
    }
    //每隔1秒
    @Scheduled(cron = "0/1 * * * * ? ")
    public void updatePower2(){
        int max = 800;
        int min = 700;
        int d = (int)(Math.random()*(max - min)+min);
        redisService.set("power:power_2-101",d);
    }
    //每隔1秒
    @Scheduled(cron = "0/1 * * * * ? ")
    public void updatePower3(){
        int max = 50;
        int min = 0;
        int d = (int)(Math.random()*(max - min)+min);
        redisService.set("power:power_3-101",d);
    }
    //每隔1秒
    @Scheduled(cron = "0/1 * * * * ? ")
    public void updatePower4(){
        int max = 220;
        int min = 110;
        int d = (int)(Math.random()*(max - min)+min);
        redisService.set("power:power_4-101",d);
    }
    //每隔1秒
    @Scheduled(cron = "0/1 * * * * ? ")
    public void updatePower5(){
        int max = 800;
        int min = 0;
        int d = (int)(Math.random()*(max - min)+min);
        redisService.set("power:power_5-101",d);
    }
    //每隔1秒
    @Scheduled(cron = "0/1 * * * * ? ")
    public void updatePower6(){
        int max = 500;
        int min = 100;
        int d = (int)(Math.random()*(max - min)+min);
        redisService.set("power:power_6-101",d);
    }
    //每隔1秒
    @Scheduled(cron = "0/1 * * * * ? ")
    public void updatePower7(){
        int max = 880;
        int min = 850;
        int d = (int)(Math.random()*(max - min)+min);
        redisService.set("power:power_7-101",d);
    }
    //每隔1秒
    @Scheduled(cron = "0/1 * * * * ? ")
    public void updatePower8(){
        int max = 200;
        int min = 180;
        int d = (int)(Math.random()*(max - min)+min);
        redisService.set("power:power_8-101",d);
    }
}
