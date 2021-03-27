package com.bjtuhbxy.hb.dao;

import com.bjtuhbxy.hb.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomDAO extends JpaRepository<Room, Integer> {
    Room findByBuildingIdAndAndRoomId(int BuildingId, int RoomId);
}
