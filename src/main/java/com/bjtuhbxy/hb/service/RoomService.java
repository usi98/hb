package com.bjtuhbxy.hb.service;

import com.bjtuhbxy.hb.dao.RoomDAO;
import com.bjtuhbxy.hb.pojo.Room;
import com.bjtuhbxy.hb.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    @Autowired
    RoomDAO roomDAO;

    @Autowired
    StudentService studentService;

    public Room getRoomAndStudentsInfo(int bid, int rid){
        Room room = roomDAO.findByBuildingIdAndAndRoomId(bid,rid);
        room.setStudents(studentService.getStudents(bid,rid));
        return room;
    }

    public Room getRoomInfo(int bid, int rid){
        Room room = roomDAO.findByBuildingIdAndAndRoomId(bid,rid);
        return room;
    }
}
