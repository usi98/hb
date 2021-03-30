package com.bjtuhbxy.hb.dao;

import com.bjtuhbxy.hb.HbApplication;

import com.bjtuhbxy.hb.entity.Room;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Time;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = HbApplication.class)// 就是你springboot的启动类
public class roomDaoTest {

    @Autowired
    RoomDAO roomDAO;

    @Test
    public void updatePowerTest(){
        int i =roomDAO.updatePowerPaxAll(80);
        System.out.println(i);
    }

    @Test
    public void updatePowerTest2(){
        int i =roomDAO.updatePowerPaxByBidAndRid(810,1,101);
        System.out.println(i);
    }

    @Test
    public void timeTest(){
        System.out.println(new Time(6,0,0));
    }


    @Test
    public void addRoomsTest(){
        Room room = new Room();
        room.setSurplus(20);
        room.setPrice(60);
        room.setStime(new Time(6,0,0));
        room.setEtime(new Time(23,59,59));



        for(int bid = 1;bid<=8;bid++){
            for (int floor = 1;floor <= 6;floor++){
                int frid= floor*100+1;
                for (int rid = frid ;rid<= frid+9;rid++){
                    room.setId(0);
                    room.setRoomId(rid);
                    room.setBuildingId(bid);
                    roomDAO.save(room);
                }
            }
        }
    }

}
