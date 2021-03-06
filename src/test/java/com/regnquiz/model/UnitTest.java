/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

import java.util.Set;
import java.util.HashSet;

/**
 * Author: Matthew MacLennan
 * Date: 22/9/2019
 * Version: 1
 * Comment: Unit test for Unit
 */
public class UnitTest 
{
    public static void main(String args[])
    {
        Unit a = new Unit();
        display(a);
        
        a.setUnitID(1234);
        a.setUnitCode("test1");
        a.setUnitName("test2");
        a.setSemester(new Semester(4321, "desc"));
        a.setYear(5678);
        a.setLecture(new User(8765, "name1", "name2", "name3"));
        
        Set<Booking> book = new HashSet<>();
        book.add(new Booking());
        
        a.setBookings(book);
        display(a);
        
        Unit b = new Unit(9012, "test3", "test4", new Semester(2109, "desc2"), 3456, new User(6543, "name4", "name5", "name6"));
        display(b);
        
        b.setUnitID(7890);
        b.setUnitCode("test5");
        b.setUnitName("test6");
        b.setSemester(new Semester(987, "desc3"));
        b.setYear(1357);
        b.setLecture(new User(7531, "name7", "name8", "name9"));
        b.setBookings(book);
        display(b);
        
        b.addBooking(new Booking());
        display(b);
    }

    public static void display(Unit u)
    {
        System.out.println("Unit ID: " + u.getUnitID());
        System.out.println("Unit Code: " + u.getUnitCode());
        System.out.println("Unit Name: " + u.getUnitName());
        System.out.println("Semester: " + u.getSemester().getSemID());
        System.out.println("Year: " + u.getYear());
        System.out.println("Lecture: " + u.getLecture().getUserID());
        System.out.println("Bookings Size: " + u.getBookings().size());
        System.out.println();
        System.out.println();
    }
}
