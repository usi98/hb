package com.bjtuhbxy.hb.dao;

import com.bjtuhbxy.hb.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDAO extends JpaRepository<User,Integer> {
    User findByUsername(String username);
    User findByBuildingIdAndRoomId(int bid, int rid);
    List<User> findUsersByBuildingIdAndRoomId(int bid, int rid);
    User getByUsernameAndPassword(String username,String password);
}
