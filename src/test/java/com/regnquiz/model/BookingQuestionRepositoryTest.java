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
        Iterable<BookingQuestion> example = bookingQuestionRepo.findAll();
        
        for(BookingQuestion b : example)
        {
            display(b);
        }
    }
    
    public static void display(BookingQuestion q)
    {
        int bid, qid;
        bid = q.getBooking().getBookingID();
        qid = q.getQuestion().getQID();
        System.out.println("Booking ID: " + bid);
        System.out.println("Question ID: " + qid);
        System.out.println();
        System.out.println();
    }
    
}
