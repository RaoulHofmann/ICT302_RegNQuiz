/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;
import java.util.HashSet;

/**
 * Author: Matthew MacLennan
 * Date: 22/9/2019
 * Version: 1
 * Comment: Contains unit information
 */
@Entity
public class Unit 
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer unitID;
    
    @Column(name = "code")
    private String unitCode;
    private String unitName;
    private Integer year;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "semesterID", referencedColumnName="semesterID")
    private Semester semester;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "UserID", referencedColumnName="userID")
    private User lecture;

    @JsonBackReference
    @OneToMany(mappedBy = "unit")
    private Set<Booking> bookings;
    
    public Unit() 
    {
        semester = new Semester();
        lecture = new User();
        
        bookings = new HashSet<>();
    }
    public Unit(Integer id, String unitCode, String unitName, Semester sem, Integer year, User lect)
    {
        this.unitID = id;
        this.unitCode = unitCode;
        this.unitName = unitName;
        this.semester = sem;
        this.year = year;
        this.lecture = lect;
        
        bookings = new HashSet<>();
    }
    
    public Unit(Integer id, String UnitCode, String unitName, Integer sem, Integer year, User lect)
    {
        this.unitID = id;
        this.unitCode = unitCode;
        this.unitName = unitName;
        this.semester = new Semester(sem);
        this.year = year;
        this.lecture = lect;
        
        bookings = new HashSet<>();
    }
    
    public Integer getUnitID()
    {
        return unitID;
    }
    
    public void setUnitID(Integer id)
    {
        this.unitID = id;
    }
    
    public String getUnitCode()
    {
        return unitCode;
    }
    
    public void setUnitCode(String code)
    {
        this.unitCode = code;
    }
    
    public String getUnitName()
    {
        return unitName;
    }
    
    public void setUnitName(String name)
    {
        this.unitName = name;
    }
    
    public Semester getSemester()
    {
        return semester;
    }
    
    public void setSemester(Semester sem)
    {
        this.semester = sem;
    }
    
    public Integer getYear()
    {
        return year;
    }
    
    public void setYear(Integer year)
    {
        this.year = year;
    }
    
    public User getLecture()
    {
        return lecture;
    }
    
    public void setLecture(User lect)
    {
        this.lecture = lect;
    }
    
    public Set<Booking> getBookings()
    {
        return bookings;
    }
    
    public void setBookings(Set<Booking> b)
    {
        bookings = b;
    }
    
    public void addBooking(Booking b)
    {
        bookings.add(b);
    }
}
