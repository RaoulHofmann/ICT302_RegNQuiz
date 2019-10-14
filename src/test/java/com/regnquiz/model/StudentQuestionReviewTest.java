/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

import com.regnquiz.model.repositories.QuestionReviewRepository;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Author: Stuart Hepburn
 * Date: 12/10/2019
 * Version: 1
 * 
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentQuestionReviewTest {
    @Autowired
    private StudentQuestionReview sr;
    
    
    @Test
    public void TestAnswers()
    {
        System.out.println("#############################");
        System.out.println("#############################");
        System.out.println("#############################");
        Iterable<QuestionReview> qi = sr.getQuestions();
        for(QuestionReview q: qi)
            display(q);
        
        System.out.println("#############################");
        System.out.println("#############################");
        System.out.println("#############################");
        List<QuestionReview> qi2 = sr.getQuestions(6);
        for(QuestionReview q: qi2)
            display(q);
        
        System.out.println("#############################");
        System.out.println("#############################");
        System.out.println("#############################");
        List<QuestionReview> qi3 = sr.getQuestions(6, 1);
        for(QuestionReview q: qi3)
            display(q);
        
        System.out.println("#############################");
        System.out.println("#############################");
        System.out.println("#############################");
        List<QuestionReview> qi4 = sr.getQuestions(6, 2);
        for(QuestionReview q: qi4)
            display(q);
        
    }
    
    public void display(QuestionReview q)
    {
        System.out.println("UserID: " + q.getUserID());
        System.out.println("Unit ID: " + q.getUnitID());
        System.out.println("Unit Code: " + q.getUnitCode());
        System.out.println("Unit Name: " + q.getUnitName());
        System.out.println("Booking Date: " + q.getBookingDate());
        System.out.println("BookingTime: " + q.getBookingTime());
        System.out.println("Question: " + q.getQuestion());
        System.out.println("Correct Answer: " + q.getAnswer());
        System.out.println("Student Answered:" + q.getStudentResult());
        System.out.println(q.getCorrectAnswerID() + " " + q.getStudentAnswer());
        System.out.println();
        System.out.println();
    }
}
