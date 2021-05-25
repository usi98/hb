package com.bjtuhbxy.hb.dao;

import com.bjtuhbxy.hb.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface RoomDAO extends JpaRepository<Room, Integer> , JpaSpecificationExecutor<Room> {


    Room findByBuildingIdAndAndRoomId(int BuildingId, int RoomId);


    int countByEnable(Integer enable);

    @Modifying
    @Transactional
    @Query("update Room as r set r.powerMax=?1 ")
    int updatePowerPaxAll(int powerMax);

    @Modifying
    @Transactional
    @Query("update Room as r set r.powerMax=?1 where r.buildingId=?2 and r.roomId=?3")
    int updatePowerPaxByBidAndRid(int powerMax, int bid, int rid);

    List<Room> findAllByEnable(int enable);
}
