/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

import com.regnquiz.model.repositories.BookingRepository;
import com.regnquiz.model.repositories.BookingQuestionRepository;
import com.regnquiz.model.repositories.QuestionRepository;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * Author: Stuart Hepburn
 * Date: 11/10/2019
 * Version: 1
 * 
 * Author: Matthew MacLennan
 * Date: 20/10/2019
 * Version 1.1
 * 
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookingQuestionRepositoryTest {
    
    @Autowired
    private BookingQuestionRepository bookingQuestionRepo;
    
    @Autowired
    private BookingRepository bookingRepo;
    
    @Autowired
    private QuestionRepository questionRepo;
    
    @Test
    public void TestQuestion()
    {
        // If these IDs dont exist in DB this test wont work
        Booking booking = bookingRepo.findById(2).get();
        Question question = questionRepo.findById(1).get();

        BookingQuestion bq = new BookingQuestion(booking, question);
        display(bq);
        
        bookingQuestionRepo.save(bq);

        List<BookingQuestion> recall = bookingQuestionRepo.findByBooking_BookingID(2);

        for (int i = 0; i < recall.size(); i++)
        {
            display(recall.get(i));
        }
        
        // Retrieve from db
        List<BookingQuestion> example = bookingQuestionRepo.findByBooking_BookingID(3);
        
        for(int i = 0; i < example.size(); i++)
        {
            display(example.get(i));
        }
    }
    
    public static void display(BookingQuestion q)
    {
        System.out.println("Booking ID: " + q.getBooking().getBookingID());
        System.out.println("Question ID: " + q.getQuestion().getQID());
        System.out.println();
        System.out.println();
    }
    
}
