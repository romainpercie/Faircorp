package com.esme.spring.faircorp.model.Building;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingDao extends JpaRepository<Building, Long>, BuildingCustomDao  {
}
