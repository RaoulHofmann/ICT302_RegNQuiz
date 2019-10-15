/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

import com.regnquiz.model.repositories.BookingQuestionRepository;
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

    @Autowired
    private BookingQuestionRepository bookingQuestionRepository;

    private Booking b = null;
    private int attendanceCounter = 0;
    private int activeQuestion = -1;
    List<BookingQuestion> bq = null;
    List<ClassList> cl;

    @Transactional
    public void OpenLecture(int bookingID)
    {
        b = bookingRepository.findById(bookingID).get();

        cl = classListRepository.findByBookingBookingID(bookingID);

        bq = bookingQuestionRepository.findByBooking_BookingID(bookingID);
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
        System.out.println("DAFUQ");

        for(ClassList c : cl)
            if(c.getStudent().getUserID()==studentID)
            {
                c.setAttendance();
                classListRepository.save(c);
                enrolled = true;
                this.attendanceCounter++;
            }
        
        return enrolled;
    }

    @Transactional
    public int getAttendanceCount(){
        return this.attendanceCounter;
    }
    
    @Transactional
    public void saveLecture()
    {
        bookingRepository.save(b);
    }

    @Transactional
    public Booking getBooking(){ return this.b; }

    @Transactional
    public int isActive(){
        if(!this.b.equals(null)){
            return 1;
        }else{
            return 0;
        }
    }

     @Transactional
    public Question startQuestion(){
        activeQuestion = 0;

        return bq.get(activeQuestion).getQuestion();
    }

    @Transactional
    public Question nextQuestion(){
        activeQuestion++;

        return bq.get(activeQuestion).getQuestion();
    }
}
