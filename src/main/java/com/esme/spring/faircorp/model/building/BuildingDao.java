package com.esme.spring.faircorp.model.building;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingDao extends JpaRepository<Building, Long>, BuildingCustomDao  {
}
