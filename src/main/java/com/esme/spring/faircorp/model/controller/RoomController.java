package com.esme.spring.faircorp.model.controller;

import com.esme.spring.faircorp.model.building.BuildingDao;
import com.esme.spring.faircorp.model.light.Light;
import com.esme.spring.faircorp.model.light.LightDao;
import com.esme.spring.faircorp.model.light.LightDto;
import com.esme.spring.faircorp.model.room.Room;
import com.esme.spring.faircorp.model.room.RoomDao;
import com.esme.spring.faircorp.model.room.RoomDto;
import com.esme.spring.faircorp.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController  // (1)
@CrossOrigin
@RequestMapping("/api/rooms") // (2)
@Transactional // (3)
public class RoomController {

    @Autowired
    private final LightDao lightDao;// (4)
    @Autowired
    private final RoomDao roomDao;
    @Autowired
    private final BuildingDao buildingDao;

    RoomController(LightDao lightDao, RoomDao roomDao, BuildingDao buildingDao){
        this.lightDao = lightDao;
        this.roomDao = roomDao;
        this.buildingDao = buildingDao;
    }


    @GetMapping // (5)
    public List<RoomDto> findAll() {
        return roomDao.findAll()
                .stream()
                .map(RoomDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{room_id}")
    public RoomDto findById(@PathVariable Long room_id) {
        return roomDao.findById(room_id).map(room -> new RoomDto(room)).orElse(null);
    }

    @PutMapping(path = "/{room_id}/switchLight")
    public List<LightDto> switchAllStatus(@PathVariable Long room_id) {
        List<Light> lights = roomDao.findRoomLightsById(room_id).stream().collect(Collectors.toList());

        for (int i = 0; i<lights.size(); i++){
            Light light = lights.get(i);
            light.setStatus(light.getStatus() == Status.ON ? Status.OFF: Status.ON);
        }
        return lights.stream()
                .map(LightDto::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    public RoomDto create(@RequestBody RoomDto dto) {
        Room room = null;
        if (dto.getId() != null) {
            room = roomDao.findById(dto.getId()).orElse(null);
        }

        if (room == null) {
            room = roomDao.save(new Room(dto.getName(), dto.getFloor(), buildingDao.getOne(dto.getBuildingId())));
        } else {
            room.setName(dto.getName());
            room.setFloor(dto.getFloor());
            roomDao.save(room);
        }
        return new RoomDto(room);
    }

    @DeleteMapping(path = "/{room_id}")
    public void delete(@PathVariable Long room_id) {
        List<Light> lights = roomDao.findRoomLightsById(room_id);
        for (int i = 0; i<lights.size(); i++){
            lightDao.deleteById(lights.get(i).getId());
        }
        roomDao.deleteById(room_id);
    }
}
