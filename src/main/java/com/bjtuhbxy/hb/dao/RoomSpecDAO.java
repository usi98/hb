package com.bjtuhbxy.hb.dao;

import com.bjtuhbxy.hb.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface RoomSpecDAO extends JpaSpecificationExecutor<Room> {

}
