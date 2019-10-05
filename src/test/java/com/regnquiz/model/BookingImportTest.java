/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

import com.regnquiz.model.repositories.BookingRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
/**
 *
 * @author Matthew MacLennan
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookingImportTest 
{
    @Autowired
    private BookingRepository bookingRepo;
    
    @Autowired
    private BookingImport bi;
    
    @Test
    public void TestBooking()
    {
        User lecture = new User(1, "first", "pref", "last");
        Unit unit = new Unit(1, "abc123", "test", 1, 2019, lecture);
        Venue venue = new Venue("1.1.1", 1);
        
        bi.ImportBooking("D:/Booking.csv", unit, venue, lecture);
        
        Iterable<Booking> g = bookingRepo.findAll();
        
        for(Booking b : g)
        {
            display(b);
        }
    }
    
    public void display(Booking b)
    {
        System.out.println("Booking ID: " + b.getBookingID());
        System.out.println("Access Code: " + b.getAttendanceCode());
        System.out.println();
        System.out.println();
    }
}
