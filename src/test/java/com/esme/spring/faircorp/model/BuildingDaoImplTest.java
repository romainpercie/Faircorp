package com.esme.spring.faircorp.model;

import com.esme.spring.faircorp.model.Building.BuildingDao;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.tuple;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan

public class BuildingDaoImplTest {

    @Autowired
    BuildingDao
            buildingDao;

    @Test
    public void shouldFindByName() {
        Assertions.assertThat(buildingDao.findByName("Building1"))
                .extracting("name")
                .containsExactly("Building1");
    }

    @Test
    public void shouldFindBuildingLightsById() {
        Assertions.assertThat(buildingDao.findBuildingLightsById(-1L))
                .hasSize(2)
                .extracting("id", "status")
                .contains(tuple(-1L, Status.ON),tuple(-2L, Status.OFF));
    }

    @Test
    public void shouldFindBuildingRoomsById() {
        Assertions.assertThat(buildingDao.findBuildingRoomsById(-1L))
                .hasSize(1)
                .extracting("id", "name")
                .contains(tuple(-10L, "Room1"));
    }
}
