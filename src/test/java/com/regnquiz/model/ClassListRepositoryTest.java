/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

import com.regnquiz.model.repositories.ClassListRepository;
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
    
    @Test
    public void TestClassReturn()
    {
        //ClassList[] g = classListRepository.getBookingClass(2);
        Iterable<ClassList> g = classListRepository.findAll();
        for(ClassList v : g)
            display(v);
    }
    
        public void display(ClassList v)
    {
        System.out.println("ID: " + v.getClassListID());
        
        
        System.out.println("StudentID: " + v.getStudent().getUserID());
        //System.out.println("BookingID: " + v.getBooking().getBookingID());
        
        System.out.println("internal: " + v.isInternal());
        System.out.println("attendance: " + v.isAttendance());

        System.out.println();
        System.out.println();
    }
}
