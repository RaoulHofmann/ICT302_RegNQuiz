/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mysql.demo;

/**
 *
 * @author Matthew MacLennan
 */
public class ClassList 
{
    private Integer classListID;
    private User student;
    private Booking booking;
    private boolean internal;
    private boolean attendance;
    
    public ClassList() {}
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
