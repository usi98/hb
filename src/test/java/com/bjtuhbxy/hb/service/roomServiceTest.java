package com.bjtuhbxy.hb.service;

import com.alibaba.fastjson.JSON;
import com.bjtuhbxy.hb.HbApplication;
import com.bjtuhbxy.hb.entity.Room;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HbApplication.class)
public class roomServiceTest {

    @Autowired
    RoomService roomService;

    @Test
    public void getRoomAndStudents(){
        Room room = roomService.getRoomAndStudentsInfo(4,502);

        String json = JSON.toJSONString(room);
        System.out.println("ss");
        System.out.println(json);
    }

    @Test
    public void updatePowerTest(){
        roomService.updatePower(450,1,101);
    }
}
