package com.esme.spring.faircorp.model.building;

import com.esme.spring.faircorp.model.light.Light;
import com.esme.spring.faircorp.model.room.Room;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class BuildingDaoImpl implements BuildingCustomDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Building findByName(String nom) {
        String jpql = "select b from Building b where b.name = :name";
        return em.createQuery(jpql, Building.class)
                .setParameter("name",nom)
                .getSingleResult();
    }

    @Override
    public List<Light> findBuildingLightsById(Long building_id) {
        String jpql = "select lt from Light lt where lt.room.building.id = :id";
        return em.createQuery(jpql, Light.class)
                .setParameter("id",building_id)
                .getResultList();
    }

    @Override
    public List<Room> findBuildingRoomsById(Long building_id){
        String jpql = "select r from Room r where r.building.id = :id";
        return em.createQuery(jpql, Room.class)
                .setParameter("id",building_id)
                .getResultList();
    }

}
