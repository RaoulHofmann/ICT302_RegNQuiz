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

import org.hibernate.id.IdentifierGenerationException;
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
import java.util.NoSuchElementException;
import java.util.Set;
import java.sql.Time;

/**
 *
 * @author Matthew MacLennan and Raoul Hofmann
 * @comment CSV reader for booking class, deposits in database
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
    public void ImportBooking(MultipartFile filename)
    {
        BufferedReader br = null; // new buffered reader
        try {
            InputStream is = filename.getInputStream(); // put input stream into new object
            br = new BufferedReader(new InputStreamReader(is, "UTF-8")); // put inputstream into buffered reader
            br.readLine(); //Read First Line

            String line = null;
            while ((line = br.readLine()) != null) // while nextline has data
            {
                String[] lineSplit = line.split(","); // CSV split

                // Deposit split lines into booking
                Booking b = new Booking(); 
                String[] dateSplit = lineSplit[0].split("/");
                b.setDate(new Date(Integer.parseInt(dateSplit[2]), Integer.parseInt(dateSplit[1]), Integer.parseInt(dateSplit[0])));
                b.setBookingLength(Integer.parseInt(lineSplit[1]));
                b.setTime(Time.valueOf(lineSplit[2]));
                try {
                    b.setUnit(unitRepo.findById(Integer.parseInt(lineSplit[3])).get()); // Attempt to find unit by id to prevent duplicate data in the database
                }catch (NoSuchElementException e){
                    System.out.println(e);
                }

                try {
                    b.setVenue(venueRepo.findById(Integer.parseInt(lineSplit[4])).get());
                }catch (NoSuchElementException e){
                    System.out.println(e);
                }

                try {
                    b.setLecture(userRepo.findById(Integer.parseInt(lineSplit[5])).get());
                }catch (NoSuchElementException e){
                    System.out.println(e);
                }

                b.setAttendanceCode(lineSplit[6]);
               
                // save booking
                try
                {
                    b = bookingRepo.save(b);
                }
                catch(DataIntegrityViolationException | IdentifierGenerationException ex)
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