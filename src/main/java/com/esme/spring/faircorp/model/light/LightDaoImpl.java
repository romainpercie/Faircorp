package com.esme.spring.faircorp.model.light;

import com.esme.spring.faircorp.model.Status;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Component
public class LightDaoImpl implements LightCustomDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Light> findOnLights() {
        String jpql = "select lt from Light lt where lt.status = :value";
        return em.createQuery(jpql, Light.class)
                .setParameter("value", Status.ON)
                .getResultList();
    }

}
