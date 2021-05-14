package com.bjtuhbxy.hb.schedule;

import com.bjtuhbxy.hb.entity.Params;
import com.bjtuhbxy.hb.entity.Room;
import com.bjtuhbxy.hb.redis.RedisService;
import com.bjtuhbxy.hb.serial.CommUtil;
import com.bjtuhbxy.hb.serial.Operation;
import com.bjtuhbxy.hb.service.ParamsService;
import com.bjtuhbxy.hb.service.RoomService;
import org.apache.shiro.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class Job {

    @Autowired
    RedisService redisService;

    @Autowired
    RoomService roomService;

    @Autowired
    ParamsService paramsService;

    @Autowired
    CommUtil commUtil;

//    @Scheduled(cron = "0 0 0/1 * * ?")
//    public void updateInfo(){
//        this.list = roomService.findAll();
//    }

    //每隔10分钟
    @Scheduled(cron = "0 0/10 * * * ? ")
    public void powerstart(){
        Params paramst = paramsService.getparamByName("starttime");
        Params paramet = paramsService.getparamByName("endtime");

        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("HHmm");

        String sHH = paramst.getValue1();
        String smm =  paramet.getValue1();
        String eHH = paramst.getValue1();
        String emm =  paramet.getValue1();
        List<Room> list;
        if(sdf.format(date).substring(0,2).equals(sHH) && sdf.format(date).substring(2,4).equals(smm)){
            list = roomService.getRoomsByEnable(0);
            for (Room room:list) {
                if (room.getEnable()==0){
                    String line = Operation.trip(room.getMeterAddress(), "000000","02","C1C2C3C4", "1B");
                    String r = commUtil.send(line);
                    if("FEFEFEFE".equals(StringUtils.clean(r).substring(0,8))){
                        r = r.substring(8);
                        String A5_0 = r.substring(2,14);
                        String C = r.substring(16,18);
                        if(room.getMeterAddress().equals(A5_0) && "9C".equals(C)) {
                            room.setEnable(1);
                            roomService.save(room);
                        }
                    }
                }
            }
        }else if(sdf.format(date).substring(0,2).equals(eHH) && sdf.format(date).substring(2,4).equals(emm)) {
            list = roomService.getRoomsByEnable(1);
            for (Room room : list) {
                if (room.getEnable() == 1) {
                    String line = Operation.trip(room.getMeterAddress(), "000000","02","C1C2C3C4", "1A");
                    String r = commUtil.send(line);
                    if ("FEFEFEFE".equals(StringUtils.clean(r).substring(0, 8))) {
                        r = r.substring(8);
                        String A5_0 = r.substring(2, 14);
                        String C = r.substring(16, 18);
                        if (room.getMeterAddress().equals(A5_0) && "9C".equals(C)) {
                            room.setEnable(0);
                            roomService.save(room);
                        }
                    }
                }
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
