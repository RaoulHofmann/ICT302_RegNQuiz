package com.regnquiz.model;

//import org.springframework.data.rest.core.annotation.RestResource;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;
//import java.util.Set;


/**
 * Author: Stuart Hepburn
 * Date: 21/9/2019
 * Version: 1
 * Comment:  This class contains details about the venue
 */

@Entity // This tells Hibernate to make a table out of this class
public class Venue {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int venueID;
    private int building;
    private int floor;
    private int room;
    private int capacity;

    @JsonBackReference
    @OneToMany(mappedBy = "venue")
    private Set<Booking> bookings;
    
    Venue()
    {
    }
    
    Venue(int building, int floor, int room, int capacity)
    {
        this.building = building;
        this.floor = floor;
        this.room = room;
        this.capacity = capacity;        
    }
    
    Venue(String location, int capacity)
    {
        int locSplit[] = locToInt(location) ;
        this.building = locSplit[0];
        this.floor = locSplit[1];
        this.room = locSplit[2];
        this.capacity = capacity;        
    }
    
    public int getVenueID()
    {
        return venueID;
    }
    
    public int getBuilding()
    {
        return building;
    }
    
    public int getFloor()
    {
        return floor;
    }
    
    public int getRoom()
    {
        return room;
    }
    
    public int getCapacity()
    {
        return capacity;
    }
    
    public void setVenueID(int venueID)
    {
        this.venueID = venueID;
    }
    
    public void setBuilding(int building)
    {
        this.building = building;
    }
    
    public void setFloor(int floor)
    {
        this.floor = floor;
    }
    
    public void setRoom(int room)
    {
        this.room = room;
    }
    
    public void setLocation(String location)
    {
        int[] loc = locToInt(location);
        building = loc[0];
        floor = loc[1];
        room = loc[2];
    }
    
    public void setCapacity(int capacity)
    {
        this.capacity = capacity;
    }
    
    private int[] locToInt(String location)
    {
        String[] locSplit = location.split("\\.");
        int loc[] = new int[locSplit.length];
                
        for(int i=0; i< locSplit.length; i++)
            loc[i] = Integer.parseInt(locSplit[i]);
        
        return loc;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    @Override
    public String toString()
    {
        return building + "." + floor + "." + room;
    }
    
}
