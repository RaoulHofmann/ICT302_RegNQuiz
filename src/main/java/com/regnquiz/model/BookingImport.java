/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

import com.regnquiz.model.repositories.BookingRepository;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author Matthew MacLennan
 */
@Service
public class BookingImport 
{
    @Autowired
    private BookingRepository bookingRepo;
    
    @Transactional
    public void ImportBooking(String filename, Unit unit, Venue venue, User lecture)
    {
        Path myPath = Paths.get(filename);
        
        try
        {
            List<String> lines = Files.readAllLines(myPath);
            lines.remove(0);
            for(String line: lines)
            {
                String[] lineSplit = line.split(",");
                
                Booking b = new Booking(); // missing constructors from git -- fix later
                b.setDate(new Date (lineSplit[0]));
                b.setBookingLength(Integer.parseInt(lineSplit[1]));
                //b.setBookingID(bookingRepo.findByBooking(b.getDate(), b.getTime(), b.getUnit().getUnitID(), b.getVenue().getVenueID()));
                //b.setAttendanceCode(bookingRepo.setAccessCode(b.getBookingID()));
                b.setUnit(unit);
                b.setVenue(venue);
                b.setLecture(lecture);
                
                try
                {
                    b = bookingRepo.save(b);
                }
                catch(DataIntegrityViolationException ex)
                {
                    System.out.println("CSV Error: " + ex);
                }
            }
        }
        catch(IOException e)
        {
            System.out.println("ERROR IOException: " + e);
        }
    }
}