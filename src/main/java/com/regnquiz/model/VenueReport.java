/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

import java.sql.Time;
import java.util.Date;
/**
 *
 * @author Matthew MacLennan
 */
public class VenueReport 
{
    private Integer building;
    private Integer floor;
    private Integer room;
    private Integer capacity;
    private Integer attendance;
    private Date date;
    private Time time;
    
    public VenueReport() {}
    public VenueReport(Integer building, Integer floor, Integer room, Integer cap, Integer att, Date date, Time time)
    {
        this.building = building;
        this.floor = floor;
        this.room = room;
        this.capacity = cap;
        this.attendance = att;
        this.date = date;
        this.time = time;
    }
    
    public Integer getBuilding()
    {
        return building;
    }
    
    public Integer getFloor()
    {
        return floor;
    }
    
    public Integer getRoom()
    {
        return room;
    }
    
    public Integer getCapacity()
    {
        return capacity;
    }
    
    public Integer getAttendance()
    {
        return attendance;
    }
    
    public Date getDate()
    {
        return date;
    }
    
    public Time getTime()
    {
        return time;
    }
    
    public void setBuilding(Integer building)
    {
        this.building = building;
    }
    
    public void setFloor(Integer floor)
    {
        this.floor = floor;
    }
    
    public void setRoom(Integer room)
    {
        this.room = room;
    }
    
    public void setCapacity(Integer cap)
    {
        this.capacity = cap;
    }
    
    public void setAttendace(Integer att)
    {
        this.attendance = att;
    }
    
    public void setDate(Date date)
    {
        this.date = date;
    }
    
    public void setTime(Time time)
    {
        this.time = time;
    }
}
