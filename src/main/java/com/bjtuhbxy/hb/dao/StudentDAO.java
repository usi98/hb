package com.bjtuhbxy.hb.dao;

import com.bjtuhbxy.hb.pojo.Room;
import com.bjtuhbxy.hb.pojo.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentDAO extends JpaRepository<Student, Integer> {

    //通过楼号和房间号 查询唯一 宿舍
    Student findByBuildingIdAndAndRoomId(int bid , int rid);
    List<Student> findStudentsByBuildingIdAndAndRoomId(int bid, int rid);
}
