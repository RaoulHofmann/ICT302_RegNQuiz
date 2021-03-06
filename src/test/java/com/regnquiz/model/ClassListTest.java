/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

import java.util.Date;

/**
 * Author: Matthew MacLennan
 * Date: 23/9/2019
 * Version: 1
 * Comment: Unit test for ClassList
 */

public class ClassListTest 
{
    public static void main(String args[])
    {
        ClassList a = new ClassList();
        display(a);
        
        //a.setClassListID(1234);
        a.setStudent(new User(4321, "a", "a", "a"));
        a.setBooking(new Booking(5678, new Date(), 1, "a", new Unit(), new Venue(), new User()));
        a.setInternal(true);
        a.setAttendance();
        display(a);
        
        ClassList b = new ClassList(3456, new User(9012, "a", "a", "a"), new Booking(2109, new Date(), 1, "a", new Unit(), new Venue(), new User()), false, false);
        display(b);

        b.setStudent(new User(6543, "a", "a", "a"));
        b.setBooking(new Booking(7890, new Date(), 1, "a", new Unit(), new Venue(), new User()));
        b.setInternal(true);
        b.setAttendance();
        display(b);
        
        /*
        ClassList c = new ClassList(3456, 1234, 4321, false, false);
        display(c);

        c.setStudent(new User(6543, "a", "a", "a"));
        c.setBooking(new Booking(7890, new Date(), 1, "a", new Unit(), new Venue(), new User()));
        c.setInternal(true);
        c.setAttendance();
        display(c);
        */
    }
    
    public static void display(ClassList c)
    {
        System.out.println("ClassListID: " + c.getClassListID());
        System.out.println("Student ID: " + c.getStudent().getUserID());
        System.out.println("Booking ID: " + c.getBooking().getBookingID());
        System.out.println("Internal: " + c.isInternal());
        System.out.println("Attendance: " + c.isAttendance());
        System.out.println();
        System.out.println();
    }
}
