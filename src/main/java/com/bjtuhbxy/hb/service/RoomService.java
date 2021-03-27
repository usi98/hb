package com.bjtuhbxy.hb.service;

import com.bjtuhbxy.hb.dao.RoomDAO;
import com.bjtuhbxy.hb.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
    @Autowired
    RoomDAO roomDAO;

    @Autowired
    StudentService studentService;

    @Autowired
    UserService userService;

    public Room getRoomAndStudentsInfo(int bid, int rid){
        Room room = roomDAO.findByBuildingIdAndAndRoomId(bid,rid);
        room.setUsers(userService.getListByBidAndRid(bid,rid));
        return room;
    }

    public Room getRoomInfo(int bid, int rid){
        Room room = roomDAO.findByBuildingIdAndAndRoomId(bid,rid);
        return room;
    }
}
