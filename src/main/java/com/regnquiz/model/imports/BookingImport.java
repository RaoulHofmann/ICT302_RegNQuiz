/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model.imports;

import com.regnquiz.model.Booking;
import com.regnquiz.model.repositories.BookingRepository;
import com.regnquiz.model.repositories.UnitRepository;
import com.regnquiz.model.repositories.VenueRepository;
import com.regnquiz.model.repositories.UserRepository;

import java.io.BufferedReader;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.Set;
import java.sql.Time;

/**
 *
 * @author Matthew MacLennan
 */
@Service
public class BookingImport 
{
    @Autowired
    private BookingRepository bookingRepo;
    
    @Autowired
    private UnitRepository unitRepo;
    
    @Autowired
    private VenueRepository venueRepo;
    
    @Autowired 
    private UserRepository userRepo;
    
    @Transactional
    //public void ImportBooking(String filename)//, Unit unit, Venue venue, User lecture)
    public void ImportBooking(MultipartFile filename)
    {
        //Path myPath = Paths.get(filename);
        
        //try
        //{
            //List<String> lines = Files.readAllLines(myPath);
            //lines.remove(0);

        BufferedReader br = null;
        try {
            InputStream is = filename.getInputStream();
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            br.readLine(); //Read First Line

            //for(String line: lines)
            String line = null;
            while ((line = br.readLine()) != null)
            {
                String[] lineSplit = line.split(",");

                Booking b = new Booking(); // missing constructors from git -- fix later
                String[] dateSplit = lineSplit[0].split("/");
                b.setDate(new Date(Integer.parseInt(dateSplit[2]), Integer.parseInt(dateSplit[1]), Integer.parseInt(dateSplit[0])));
                b.setBookingLength(Integer.parseInt(lineSplit[1]));
                b.setTime(Time.valueOf(lineSplit[2]));
                b.setUnit(unitRepo.findById(Integer.parseInt(lineSplit[3])).get());
                b.setVenue(venueRepo.findById(Integer.parseInt(lineSplit[4])).get());
                b.setLecture(userRepo.findById(Integer.parseInt(lineSplit[5])).get());
                b.setAttendanceCode(lineSplit[6]);
                
                //b.setUnit(unit);
                //b.setVenue(venue);
                //b.setLecture(lecture);
                
                /*
                b.setDate(new Date (lineSplit[0]));
                b.setBookingLength(Integer.parseInt(lineSplit[1]));
                //b.setBookingID(bookingRepo.findByBooking(b.getDate(), b.getTime(), b.getUnit().getUnitID(), b.getVenue().getVenueID()));
                //b.setAttendanceCode(bookingRepo.setAccessCode(b.getBookingID()));
                b.setUnit(unit);
                b.setVenue(venue);
                b.setLecture(lecture);
                */
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