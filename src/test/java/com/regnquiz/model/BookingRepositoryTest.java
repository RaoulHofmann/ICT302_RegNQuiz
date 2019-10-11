/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

import com.regnquiz.model.repositories.BookingRepository;
import com.regnquiz.model.repositories.SemesterRepository;
import com.regnquiz.model.repositories.UnitRepository;
import com.regnquiz.model.repositories.UserRepository;
import com.regnquiz.model.repositories.VenueRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Author: Stuart Hepburn
 * Date: 2/10/2019
 * Version: 1
 * 
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookingRepositoryTest {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private VenueRepository venueRepository;
    @Autowired
    private UnitRepository unitRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SemesterRepository semesterRepository;
    
    @Test
    public void TestBooking()
    {
        Booking a = new Booking();
        display(a);
        User u = new User(8766, "a", "b", "b");
        userRepository.save(u);
        Semester s = new Semester(0, "T1");
        semesterRepository.save(s);
        Unit un = new Unit(4321, "a", "a", s, 0, u);
        unitRepository.save(un);
        Venue v = new Venue(134,1,1,1);
        venueRepository.save(v);
        try{
            a.setBookingID(1234);
            a.setDate(new SimpleDateFormat("yyyy/MM/dd").parse("2019/01/01"));
            a.setBookingLength(90);
            a.setAttendanceCode("test1");
            a.setTime(750);
            a.setAttendanceCode("uY65T");
            //a.setUnit(new Unit(4321, "a", "a", new Semester(), 0, new User()));
            //a.setUnit(new Unit(4321, "a", "a", new Semester(0, "T1"), 0, u));
            a.setUnit(un);
            //a.setVenue(new Venue(5678,1,1,1));
            a.setVenue(v);
            //a.setLecture(new User(8765, "a", "a", "a"));
            a.setLecture(u);
            display(a);
            bookingRepository.save(a);
        }
        catch(ParseException e)
        {
            
        }
        
        Iterable<Booking> g = bookingRepository.findAll();
        
        for(Booking r : g)
            display(r);
        
        
        
        
        
        //Booking b = new Booking(9012, new Date(2019, 9, 23), 80, "test2", new Unit(2109, "a", "a", new Semester(), 0, new User()), new Venue(1, 1, 1, 1), new User(3456, "a", "a", "a"));
        //display(b);
        //bookingRepository.save(b);
        
        /*
        b.setBookingID(6543);
        b.setDate(new Date(2019, 9, 24));
        b.setBookingLength(70);
        b.setAttendanceCode("test3");
        b.setUnit(new Unit(7890, "a", "a", new Semester(), 0, new User()));
        b.setVenue(new Venue(2, 2, 2, 2));
        b.setLecture(new User(987, "a", "a", "a"));
        display(b);
        bookingRepository.save(b);*/

        try{
            Booking c = new Booking();
            Optional<Booking> booking = bookingRepository.findById(45);
            c = booking.get();
            display(c);
        }
        catch(NoSuchElementException e)
        {
            
        }
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
