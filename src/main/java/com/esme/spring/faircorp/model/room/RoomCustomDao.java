package com.esme.spring.faircorp.model.room;

import com.esme.spring.faircorp.model.light.Light;

import java.util.List;

public interface RoomCustomDao {
    Room findByName(String name);
    List<Light> findRoomLightsById(Long room_id);
}
