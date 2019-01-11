package com.esme.spring.faircorp.model.Light;

import com.esme.spring.faircorp.model.Room.Room;
import com.esme.spring.faircorp.model.Status;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Table(name="light")
public class Light {

    // (2)
    @Id
    @GeneratedValue
    private Long id;

    // (3)
    @Column(nullable = false)
    private Integer level;

    // (4)
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    private Room room;

    public Light() {
    }

    @Autowired
    public Light(Integer level, Status status, Room room) {
        this.level = level;
        this.status = status;
        this.room = room;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
