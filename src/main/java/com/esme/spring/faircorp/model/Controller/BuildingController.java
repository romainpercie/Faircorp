package com.esme.spring.faircorp.model.Controller;

import com.esme.spring.faircorp.model.Building.Building;
import com.esme.spring.faircorp.model.Building.BuildingDao;
import com.esme.spring.faircorp.model.Building.BuildingDto;
import com.esme.spring.faircorp.model.Light.Light;
import com.esme.spring.faircorp.model.Light.LightDao;
import com.esme.spring.faircorp.model.Light.LightDto;
import com.esme.spring.faircorp.model.Room.Room;
import com.esme.spring.faircorp.model.Room.RoomDao;
import com.esme.spring.faircorp.model.Room.RoomDto;
import com.esme.spring.faircorp.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController  // (1)
@CrossOrigin
@RequestMapping("/api/buildings") // (2)
@Transactional // (3)
public class BuildingController {

    @Autowired
    private final LightDao lightDao;// (4)
    @Autowired
    private final RoomDao roomDao;
    @Autowired
    private final BuildingDao buildingDao;

    BuildingController(LightDao lightDao, RoomDao roomDao, BuildingDao buildingDao){
        this.lightDao = lightDao;
        this.roomDao = roomDao;
        this.buildingDao = buildingDao;
    }


    @GetMapping // (5)
    public List<BuildingDto> findAll() {
        return buildingDao.findAll()
                .stream()
                .map(BuildingDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{building_id}")
    public BuildingDto findById(@PathVariable Long building_id) {
        return buildingDao.findById(building_id).map(building -> new BuildingDto(building)).orElse(null);
    }

    @PostMapping
    public BuildingDto create(@RequestBody BuildingDto dto) {
        Building building = null;
        if (dto.getId() != null) {
            building = buildingDao.findById(dto.getId()).orElse(null);
        }

        if (building == null) {
            building = buildingDao.save(new Building(dto.getName()));
        } else {
            building.setName(dto.getName());
            buildingDao.save(building);
        }
        return new BuildingDto(building);
    }

    @DeleteMapping(path = "/{building_id}")
    public void delete(@PathVariable Long building_id) {
        List<Light> lights = buildingDao.findBuildingLightsById(building_id);
        for (int i = 0; i<lights.size(); i++){
            lightDao.deleteById(lights.get(i).getId());
        }
        List<Room> rooms = buildingDao.findBuildingRoomsById(building_id);
        for (int i = 0; i<rooms.size(); i++){
            roomDao.deleteById(rooms.get(i).getId());
        }
        buildingDao.deleteById(building_id);
    }
}
