package com.bjtuhbxy.hb.dao;

import com.bjtuhbxy.hb.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface RoomDAO extends JpaRepository<Room, Integer> {
    Room findByBuildingIdAndAndRoomId(int BuildingId, int RoomId);

    @Modifying
    @Transactional
    @Query("update Room as r set r.powerMax=?1 ")
    int updatePowerPaxAll(int powerMax);

    @Modifying
    @Transactional
    @Query("update Room as r set r.powerMax=?1 where r.buildingId=?2 and r.roomId=?3")
    int updatePowerPaxByBidAndRid(int powerMax, int bid, int rid);
}
