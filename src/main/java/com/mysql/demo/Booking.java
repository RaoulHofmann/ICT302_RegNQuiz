/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mysql.demo;

import javax.persistence.*;
import java.util.Set;
import java.util.Date;
/**
 * Author: Matthew MacLennan
 * Date: 22/9/2019
 * Version: 1
 * Comment: Contains booking information
 */
@Entity
public class Booking 
{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer bookingID;
    
    @Temporal(TemporalType.DATE)
    private Date date;
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
    @MapsId("UserID")
    @JoinColumn(name = "UserID", referencedColumnName="userID")
    private User lecture;
    
    public Booking() 
    {
        date = new Date();
        unit = new Unit();
        venue = new Venue();
        lecture = new User();
    }
    
    public Booking(Integer id, Date date, Integer bookingLen, String attendanceCode, Unit unit, Venue venue, User lecture)
    {
        this.bookingID = id;
        this. date = date;
        this.bookingLength = bookingLen;
        this.attendanceCode = attendanceCode;
        this.unit = unit;
        this.venue = venue;
        this.lecture = lecture;
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
    
    /*
    public void generateAttendanceCode()
    {
        //TBD
    }
*/
}
