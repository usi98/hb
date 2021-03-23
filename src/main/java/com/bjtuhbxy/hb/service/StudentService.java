package com.bjtuhbxy.hb.service;

import com.bjtuhbxy.hb.dao.RoomDAO;
import com.bjtuhbxy.hb.dao.StudentDAO;
import com.bjtuhbxy.hb.pojo.Room;
import com.bjtuhbxy.hb.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    RoomDAO roomDAO;

    @Autowired
    StudentDAO studentDAO;

    public Room getRoom(int bid, int rid){
        Room room = roomDAO.findByBuildingIdAndAndRoomId(bid,rid);
        return room;
    }

    public List<Student> getStudents(int bid, int rid){
        List<Student> students = studentDAO.findStudentsByBuildingIdAndAndRoomId(bid,rid);
        return students;
    }
}
