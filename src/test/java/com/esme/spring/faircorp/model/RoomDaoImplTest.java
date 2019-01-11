package com.esme.spring.faircorp.model;

import com.esme.spring.faircorp.model.room.RoomDao;
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
public class RoomDaoImplTest {

    @Autowired
    RoomDao
            roomDao;

    @Test
    public void shouldFindByName() {
        Assertions.assertThat(roomDao.findByName("Room1"))
                .extracting("name")
                .containsExactly("Room1");
    }

    @Test
    public void shouldFindRoomLightsById() {
        Assertions.assertThat(roomDao.findRoomLightsById(-10L))
                .hasSize(2)
                .extracting("id", "status")
                .contains(tuple(-1L, Status.ON),tuple(-2L, Status.OFF));
    }

}
