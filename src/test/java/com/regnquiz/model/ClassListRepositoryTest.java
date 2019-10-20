/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

import com.regnquiz.model.repositories.ClassListRepository;
import com.regnquiz.model.repositories.UserRepository;
import com.regnquiz.model.repositories.BookingRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * 
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ClassListRepositoryTest {
    @Autowired
    private ClassListRepository classListRepository;
    

    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private BookingRepository bookingRepo;

    @Test
    public void TestClassReturn()
    {
        ClassList cl = new ClassList();
        cl.setStudent(userRepo.findById(11).get()); // Only works if ID exists in db
        System.out.println("userid: " + cl.getStudent().getUserID());
        cl.setBooking(bookingRepo.findById(2).get());
        System.out.println("bookingid: " + cl.getBooking().getBookingID());
        cl.setInternal(true);
        cl.setAttendance();
        cl.setClassListID(10);
         
        classListRepository.save(cl);

        display(classListRepository.findById(329).get());
        //display(classListRepository.findById(Integer.SIZE).get());
        
        //ClassList[] g = classListRepository.getBookingClass(2);
        Iterable<ClassList> g = classListRepository.findAll();
        for(ClassList v : g)
            display(v);
    }
    
        public void display(ClassList v)
    {
        System.out.println("ID: " + v.getClassListID());
        
        
        System.out.println("StudentID: " + v.getStudent().getUserID());
        System.out.println("BookingID: " + v.getBooking().getBookingID());
        
        System.out.println("internal: " + v.isInternal());
        System.out.println("attendance: " + v.isAttendance());

        System.out.println();
        System.out.println();
    }
}
