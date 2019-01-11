package com.esme.spring.faircorp.model.Room;

import com.esme.spring.faircorp.model.Light.Light;

import java.util.List;
import java.util.Optional;

public interface RoomCustomDao {
    Room findByName(String name);
    List<Light> findRoomLightsById(Long room_id);
}
