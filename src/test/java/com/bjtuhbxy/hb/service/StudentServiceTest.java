package com.bjtuhbxy.hb.service;

import com.bjtuhbxy.hb.HbApplication;
import com.bjtuhbxy.hb.pojo.Room;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HbApplication.class)
public class StudentServiceTest {
    @Autowired
    StudentService studentService;
    @Test
    public void StudentServiceTest(){
        Room room = studentService.getRoom(4,305);
        System.out.println(room);
    }
}
