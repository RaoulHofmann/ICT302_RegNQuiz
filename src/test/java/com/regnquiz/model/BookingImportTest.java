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
        
    }
    
    public void display(Booking b)
    {
        
    }
}
