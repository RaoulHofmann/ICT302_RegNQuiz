package com.regnquiz.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

@Embeddable
public class VenueReviewKey implements Serializable {

    @Column(name="building")
    private int building;
    private int floor;
    private int room;
    private Date date;

    public int getBuilding() {
        return building;
    }

    public int getFloor() {
        return floor;
    }

    public int getRoom() {
        return room;
    }

    public Date getDate() {
        return date;
    }

    public void setBuilding(int building) {
        this.building = building;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
