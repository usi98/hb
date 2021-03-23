package com.bjtuhbxy.hb.dao;

import com.bjtuhbxy.hb.pojo.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomDAO extends JpaRepository<Room, Integer> {
    Room findByBuildingIdAndAndRoomId(int BuildingId, int RoomId);
}
