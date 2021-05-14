package com.bjtuhbxy.hb.service;

import com.bjtuhbxy.hb.dao.RoomDAO;
import com.bjtuhbxy.hb.dao.RoomSpecDAO;
import com.bjtuhbxy.hb.entity.Room;
import com.bjtuhbxy.hb.util.MyPage;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
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

    public List<Room> findAll(){
        return roomDAO.findAll();
    }

    public List<Room> getRoomsByEnable(int e){
        return roomDAO.findAllByEnable(e);
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

    public MyPage listWithSInfo(int page, int size, String state, String bid){
        if("2".equals(state) && "0".equals(bid)){
            return listWithSInfo(page, size);
        }else {
            MyPage<Room> rooms;
            Sort sort = Sort.by(Sort.Direction.ASC, "id");
            PageRequest pageRequest = PageRequest.of(page, size, sort);
            //条件查询
            Specification<Room> specification = new Specification<Room>() {
                @Override
                public Predicate toPredicate(Root<Room> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                    List<Predicate> predicates = new ArrayList<>(); //所有的断言
                    if (!"2".equals(state)) { //添加断言
                        Predicate p1 = cb.equal(root.get("enable").as(String.class),state);
                        predicates.add(p1);
                    }
                    if (!"0".equals(bid)) {
                        Predicate p2 = cb.equal(root.get("buildingId").as(String.class),bid );
                        predicates.add(p2);
                    }
                    return cb.and(predicates.toArray(new Predicate[0]));
                }
            };

            roomDAO.findAll(specification,pageRequest);

            Page<Room> roomsInDb = roomDAO.findAll( specification ,pageRequest);
            roomsInDb.getContent();
            for (Room r: roomsInDb.getContent()) {
                r.setUsers(userService.getListByBidAndRid(r.getBuildingId(),r.getRoomId()));
            }
            rooms = new MyPage<>(roomsInDb);
            return rooms;
        }
    }
}