package com.esme.spring.faircorp.model.Building;

import com.esme.spring.faircorp.model.Light.Light;
import com.esme.spring.faircorp.model.Room.Room;

import java.util.List;

public interface BuildingCustomDao {

    Building findByName(String name);
    List<Light> findBuildingLightsById(Long building_id);
    List<Room> findBuildingRoomsById(Long building_id);
}
