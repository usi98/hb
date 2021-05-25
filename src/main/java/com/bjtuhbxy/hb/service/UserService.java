package com.bjtuhbxy.hb.service;

import com.bjtuhbxy.hb.dao.UserDAO;
import com.bjtuhbxy.hb.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;



    public List<User> findAll() {
        return userDAO.findAll();
    }

    public boolean isExist(String username) {
        User user = getByName(username);
        return null!=user;
    }

    public User getById(Integer Id) {
        return userDAO.getOne(Id);
    }

    public User getByName(String username) {
        return userDAO.findByUsername(username);
    }
    public User getByUserName(String username) {
        return userDAO.findByUsername(username);
    }
    public User findByUsername(String username) {
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

    public void save(User user) {
        userDAO.save(user);
    }


}
