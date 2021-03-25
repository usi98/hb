package com.bjtuhbxy.hb.service;

import com.bjtuhbxy.hb.dao.UserDAO;
import com.bjtuhbxy.hb.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

    public boolean isExist(String username) {
        User user = getByName(username);
        return null!=user;
    }

    public User getByName(String username) {
        return userDAO.findByUsername(username);
    }
    public User getByUserName(String username) {
        return userDAO.findByUsername(username);
    }

    //通过楼号和房间号查询所有学生
    public List<User> getListByBidAndRid(int bid, int rid) {
        return userDAO.findUsersByBuildingIdAndRoomId(bid, rid);
    }


    public User get(String username, String password){
        return userDAO.getByUsernameAndPassword(username, password);
    }

    public void add(User user) {
        userDAO.save(user);
    }

    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }
}
