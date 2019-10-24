/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

import com.regnquiz.model.imports.BookingImport;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.regnquiz.model.repositories.BookingRepository;
import com.regnquiz.model.repositories.UnitRepository;
import com.regnquiz.model.repositories.UserRepository;
import com.regnquiz.model.repositories.VenueRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
    
    @Autowired
    private UnitRepository unitRepo;
    
    @Autowired
    private VenueRepository venueRepo;
    
    @Autowired 
    private UserRepository userRepo;
    
    @Test
    public void TestBooking()
    {
        //bi.ImportBooking("D:/Desktop/BookingExample.csv");//, unitRepo.findById(1).get(), venueRepo.findById(2).get(), userRepo.findById(1).get());
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
