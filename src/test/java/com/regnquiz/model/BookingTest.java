/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

import java.util.Date;

/**
 * Author: Matthew MacLennan
 * Date: 22/9/2019
 * Version: 1
 * Comment: Unit test for Booking
 */
public class BookingTest 
{
    public static void main(String args[])
    {
        Booking a = new Booking();
        display(a);
        
        a.setBookingID(1234);
        a.setDate(new Date(2019, 9, 22));
        a.setBookingLength(90);
        a.setAttendanceCode("test1");
        a.setUnit(new Unit(4321, "a", "a", new Semester(), 0, new User()));
        a.setVenue(new Venue(5678,1,1,1));
        a.setLecture(new User(8765, "a", "a", "a"));
        display(a);
        
        Booking b = new Booking(9012, new Date(2019, 9, 23), 80, "test2", new Unit(2109, "a", "a", new Semester(), 0, new User()), new Venue(1, 1, 1, 1), new User(3456, "a", "a", "a"));
        display(b);
        
        b.setBookingID(6543);
        b.setDate(new Date(2019, 9, 24));
        b.setBookingLength(70);
        b.setAttendanceCode("test3");
        b.setUnit(new Unit(7890, "a", "a", new Semester(), 0, new User()));
        b.setVenue(new Venue(2, 2, 2, 2));
        b.setLecture(new User(987, "a", "a", "a"));
        display(b);
    }
    
    public static void display(Booking b)
    {
        System.out.println("Booking ID: " + b.getBookingID());
        System.out.println("Date: " + b.getDate());
        System.out.println("Booking Length: " + b.getBookingLength());
        System.out.println("Unit ID: " + b.getUnit().getUnitID());
        System.out.println("Venue ID: " + b.getVenue().getVenueID());
        System.out.println("Lecture ID: " + b.getLecture().getUserID());
        System.out.println();
        System.out.println();
    }
}
