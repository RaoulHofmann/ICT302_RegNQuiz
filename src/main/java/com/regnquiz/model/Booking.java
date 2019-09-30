/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

import java.sql.Time;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
/**
 * Author: Matthew MacLennan
 * Date: 22/9/2019
 * Version: 1.1
 * Comment: Contains booking information
 * Changes: Added classList, updated constructor, added gets+sets+adds
 */
@Entity
public class Booking 
{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer bookingID;
    
    @Temporal(TemporalType.DATE)
    private Date date;
    private Time time;
    private Integer bookingLength;
    @Column(name = "accessCode")
    private String attendanceCode;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("UnitID")
    @JoinColumn(name = "UnitID", referencedColumnName="unitID")
    private Unit unit;
   
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("VenueID")
    @JoinColumn(name = "VenueID", referencedColumnName="venueID")
    private Venue venue;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("lectureID")
    @JoinColumn(name = "lectureID", referencedColumnName="userID")
    private User lecture;

    @OneToMany(mappedBy = "booking")
    private Set<ClassList> classList;
    
    public Booking() 
    {
        date = new Date();
        unit = new Unit();
        venue = new Venue();
        lecture = new User();
    }
    
    public Booking(Integer id, Date date, Integer bookingLen, String attendanceCode, Unit unit, Venue venue, User lecture, Set<ClassList> classList)
    {
        this.bookingID = id;
        this. date = date;
        this.bookingLength = bookingLen;
        this.attendanceCode = attendanceCode;
        this.unit = unit;
        this.venue = venue;
        this.lecture = lecture;
        this.classList = classList;
    }
    
    public Integer getBookingID()
    {
        return bookingID;
    }
    
    public void setBookingID(Integer id)
    {
        this.bookingID = id;
    }
    
    public Date getDate()
    {
        return date;
    }
    
    public void setDate(Date date)
    {
        this.date = date;
    }
    
    public Integer getBookingLength()
    {
        return bookingLength;
    }
    
    public void setBookingLength(Integer len)
    {
        this.bookingLength = len;
    }
    
    public String getAttendanceCode()
    {
        return attendanceCode;
    }
    
    public void setAttendanceCode(String attcode)
    {
        this.attendanceCode = attcode;
    }
    
    public Unit getUnit()
    {
        return unit;
    }
    
    public void setUnit(Unit unit)
    {
        this.unit = unit;
    }
    
    public Venue getVenue()
    {
        return venue;
    }
    
    public void setVenue(Venue venue)
    {
        this.venue = venue;
    }
    
    public User getLecture()
    {
        return lecture;
    }
    
    public void setLecture(User lecture)
    {
        this.lecture = lecture;
    }
   
    public Set<ClassList> getClassList()
    {
        return classList;
    }
    
    public void setClassList(Set<ClassList> classList)
    {
        this.classList = classList;
    }
    
    public void addClass(ClassList c)
    {
        classList.add(c);
    }
    
    public boolean classContains(ClassList c)
    {
        return classList.contains(c);
    }
    
    public Integer classListSize()
    {
        return classList.size();
    }
    
    /*
    public void generateAttendanceCode()
    {
        //TBD
    }
*/
}
