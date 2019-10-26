package com.regnquiz.model.forms;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class VenueReportForm {
    private Integer building;
    private Integer floor;
    private Integer room;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    public Date getDate() {
        return date;
    }

    public Integer getBuilding() {
        return building;
    }

    public Integer getFloor() {
        return floor;
    }

    public Integer getRoom() {
        return room;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public void setBuilding(Integer building) {
        this.building = building;
    }
}
