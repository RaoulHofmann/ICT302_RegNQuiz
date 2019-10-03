/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

import com.regnquiz.model.repositories.BookingRepository;
import com.regnquiz.model.repositories.ClassListRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author: Stuart Hepburn
 * Date: 25/9/2019
 * Version: 1
 * Comment: this class will run all the components for the lecture 
 */

@Service
public class LectureRun {
    
    @Autowired
    private BookingRepository bookingRepository;
    
    @Autowired
    private ClassListRepository classListRepository;

    private Booking b = new Booking();
    List<ClassList> cl;
    
    @Transactional
    public void OpenLecture(int bookingID)
    {
        Optional<Booking> booking = bookingRepository.findById(bookingID);
        
        b = booking.get();

        cl = classListRepository.findByBookingBookingID(bookingID);
        /*for(ClassList c : cl)
        {
            System.out.println("########################################" );
            System.out.println("########################################");
            System.out.println("ID: " + c.getClassListID());
            System.out.println("Booking ID: " + c.getBooking().getBookingID());
            System.out.println("User ID: " + c.getStudent().getUserID());
        }*/
    }
    
    @Transactional
    public String getAccessCode()
    {
        return b.getAttendanceCode();
    }
    
    @Transactional
    public boolean setAttendance(int studentID)
    {   
        boolean enrolled = false;
        
        for(ClassList c : cl)
            if(c.getStudent().getUserID()==studentID)
            {
                c.setAttendance();
                classListRepository.save(c);
                enrolled = true;
            }
        
        return enrolled;
    }
    
    @Transactional
    public void saveLecture()
    {
        bookingRepository.save(b);
    }
       

    
}
