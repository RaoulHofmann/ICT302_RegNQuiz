/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mysql.demo;

import javax.persistence.*;
import java.util.Set;
/**
 * Author: Matthew MacLennan
 * Date: 22/9/2019
 * Version: 1
 * Comment: Contains information about each class and student in it
 */
@Entity
public class ClassList 
{
    @Id
    private Integer classListID;
    private User student;
    private Booking booking;
    private boolean internal;
    private boolean attendance;
    
    public ClassList() 
    {
        student = new User();
        booking = new Booking();
    }
    
    public ClassList(Integer id, User student, Booking booking, boolean internal, boolean attendance)
    {
        this.classListID = id;
        this.student = student;
        this.booking = booking;
        this.internal = internal;
        this.attendance = attendance;
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
    
    public void setAttendance(boolean attendance)
    {
        this.attendance = attendance;
    }
}
