package com.esme.spring.faircorp.model.building;

import com.esme.spring.faircorp.model.light.Light;
import com.esme.spring.faircorp.model.room.Room;

import java.util.List;

public interface BuildingCustomDao {

    Building findByName(String name);
    List<Light> findBuildingLightsById(Long building_id);
    List<Room> findBuildingRoomsById(Long building_id);
}
