/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

import com.regnquiz.model.repositories.BookingRepository;
import java.util.Optional;
import java.util.Set;
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
    
    //private String accessCode;
    //private int bookingID;
    private Booking b = new Booking();
    
    
    @Transactional
    public void OpenLecture(int bookingID)
    {
        //bookingID = lectureID;
        //accessCode = bookingRepository.getAccessCode(lectureID);
        Optional<Booking> booking = bookingRepository.findById(bookingID);
        
        b = booking.get();
        
    }
    
    @Transactional
    public String getAccessCode()
    {
        return b.getAttendanceCode();
    }
    
    @Transactional
    public void setAttendance(int studentID)
    {   
        //b.setStudentAttendance(studentID);
        Set<ClassList> c = b.getClassList();
           
           /*
           for(ClassList t: c)
           {System.out.println("2");
               if(t.getStudent().getUserID()==studentID)
                   t.setAttendance();
           }*/
    }
    
    @Transactional
    public void saveLecture()
    {
        bookingRepository.save(b);
    }
    
    @Transactional
    public void setBookingLength()
    {
        b.setBookingLength(2);
    }
    

    
}
