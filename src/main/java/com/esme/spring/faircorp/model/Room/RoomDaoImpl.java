package com.esme.spring.faircorp.model.Room;

import com.esme.spring.faircorp.model.Light.Light;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Component
public class RoomDaoImpl implements RoomCustomDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Room findByName(String nom) {
        String jpql = "select r from Room r where r.name = :name";
        return em.createQuery(jpql, Room.class)
                .setParameter("name",nom)
                .getSingleResult();
    }

    @Override
    public List<Light> findRoomLightsById(Long room_id) {
        String jpql = "select lt from Light lt where lt.room.id = :id";
        return em.createQuery(jpql, Light.class)
                .setParameter("id",room_id)
                .getResultList();
    }
}
