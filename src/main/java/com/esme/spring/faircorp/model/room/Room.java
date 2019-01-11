package com.esme.spring.faircorp.model.room;

import com.esme.spring.faircorp.model.building.Building;
import com.esme.spring.faircorp.model.light.Light;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name= "room" )
public class Room {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int floor;

    @OneToMany(mappedBy = "room")
    private Set<Light> lights;

    @ManyToOne
    private Building building;

    public Room(){

    }

    @Autowired
    public Room(String name, int floor, Building building){
        this.name = name;
        this.floor = floor;
        this.building = building;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public Set<Light> getLights() {
        return lights;
    }

    public void setLights(Set<Light> lights) {
        this.lights = lights;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
}
