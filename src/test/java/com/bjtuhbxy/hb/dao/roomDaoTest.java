package com.bjtuhbxy.hb.dao;

import com.bjtuhbxy.hb.HbApplication;

import com.bjtuhbxy.hb.pojo.Room;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = HbApplication.class)// 就是你springboot的启动类
public class roomDaoTest {

    @Autowired
    RoomDAO roomDAO;




    @Test
    public void aurTest(){
        Room room = new Room();
        room.setSurplus(20);

        int buildid=1;
        for (int k =7 ;k<=8;k++) {
            for (int i = buildid; i <= 6; i++) {
                int roomid = i * 100 + 1;
                for (int j = roomid; j <= roomid + 5; j++) {
                    room.setId(0);
                    room.setRoomId(j);
                    room.setBuildingId(k);
                    //roomDAO.save(room);
                }
            }
        }
    }

}
