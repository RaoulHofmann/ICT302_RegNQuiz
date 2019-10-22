/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.regnquiz.model.repositories.BookingRepository;
import com.regnquiz.model.repositories.UserRepository;
import java.util.Optional;
import javax.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Author: Matthew MacLennan
 * Date: 22/9/2019
 * Version: 1
 * Comment: Contains information about each class and student in it
 */
//@EnableJpaRepositories(basePackages="com.regnquiz.model.repositories")
@Entity
public class ClassList 
{
    //@Autowired
    //private UserRepository userRepository;
   // @Autowired
    //private BookingRepository bookingRepository;
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer classListID;
    private boolean internal;
    private boolean attendance;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "userID", referencedColumnName="userID")
    private User student;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "bookingID", referencedColumnName="bookingID")
    private Booking booking;
    
    public ClassList() 
    {
        student = new User();
        booking = new Booking();
        attendance = false;
        
    }
    
    public ClassList(Integer id, User student, Booking booking, boolean internal, boolean attendance)
    {
        this.classListID = id;
        this.student = student;
        this.booking = booking;
        this.internal = internal;
        this.attendance = attendance;
        
    }
    
    
    //This class is used with the findById repository call
    public ClassList(Integer id, Integer student, Integer booking, boolean internal, boolean attendance)
    {
        System.out.println(id + " " + student + " " + booking + " " + internal + " " + attendance);
        this.classListID = id;
        //Optional<User> u = userRepository.findById(student);
        //this.student = u.get();
        //Optional<Booking> b = bookingRepository.findById(booking);
        //this.booking = b.get();
        //UserRepository ur;
        //Optional<User> u = ur.findById(student);
        
        
        this.internal = internal;
        //this.attendance = attendance;
        
    }
    
    public Integer getClassListID()
    {
        return classListID;
    }
    
    public void setClassListID(Integer id)
    {
        this.classListID = id;
    }
    
    public User getStudent()
    {
        return student;
    }
    
    public void setStudent(User student)
    {
        this.student = student;
    }
    
    public Booking getBooking()
    {
        return booking;
    }
    
    public void setBooking(Booking booking)
    {
        this.booking = booking;
    }
    
    public boolean isInternal()
    {
        return internal;
    }
    
    public void setInternal(boolean internal)
    {
        this.internal = internal;
    }
    
    public boolean isAttendance()
    {
        return attendance;
    }
    
    public void setAttendance()
    {
        this.attendance = true;
    }
}
