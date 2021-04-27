package com.bjtuhbxy.hb.service;

import com.bjtuhbxy.hb.dao.RoomDAO;
import com.bjtuhbxy.hb.entity.Room;
import com.bjtuhbxy.hb.util.MyPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RoomService {
    Logger logger = LoggerFactory.getLogger(RoomService.class);

    @Autowired
    RoomDAO roomDAO;

    @Autowired
    UserService userService;

    public Room save(Room room){
        if (room != null){
            return roomDAO.save(room);
        }
        return null;
    }

    public Room getRoomAndStudentsInfo(int bid, int rid){
        Room room = roomDAO.findByBuildingIdAndAndRoomId(bid,rid);
        room.setUsers(userService.getListByBidAndRid(bid,rid));
        return room;
    }

    public Room getRoomInfo(int bid, int rid){
        Room room = roomDAO.findByBuildingIdAndAndRoomId(bid,rid);
        return room;
    }

    public Integer updatePowerMaxAll(int max){
        int num =  roomDAO.updatePowerPaxAll(max);
        logger.info("更新全部{}宿舍 最大功率为{}W",num,max);
        return num;
    }

    public int updatePower(int max, int bid, int rid){
        int num =  roomDAO.updatePowerPaxByBidAndRid(max,bid,rid);
        logger.info("更新{}-{}宿舍 最大功率为{}W",bid,rid,max);
        return num;
    }

    public MyPage list(int page, int size){
        MyPage<Room> rooms;
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Page<Room> roomsInDb = roomDAO.findAll(PageRequest.of(page, size, sort));
        rooms = new MyPage<>(roomsInDb);
        return rooms;
    }

    public MyPage listWithSInfo(int page, int size){
        MyPage<Room> rooms;
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Page<Room> roomsInDb = roomDAO.findAll(PageRequest.of(page, size, sort));

        roomsInDb.getContent();
        for (Room r: roomsInDb.getContent()) {
            r.setUsers(userService.getListByBidAndRid(r.getBuildingId(),r.getRoomId()));
        }
        rooms = new MyPage<>(roomsInDb);
        return rooms;
    }
}