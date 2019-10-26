/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

import com.regnquiz.model.repositories.MultipleChoiceRepository;
import com.regnquiz.model.repositories.QuestionRepository;
import com.regnquiz.model.repositories.SemesterRepository;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Author: Stuart Hepburn
 * Date: 11/10/2019
 * Version: 1
 * 
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LectureAdminTest {
    @Autowired
    private LectureAdmin la;
    @Autowired
    private MultipleChoiceRepository mcRepository;
    @Autowired
    private QuestionRepository qRepository;
    @Autowired
    private SemesterRepository semRepo;
    
    
    @Test
    public void RunLectureAdmin()
    {
        la.setUser(2);      
        
        System.out.println("###############################");
        System.out.println("###############################");
        System.out.println("###############################");
        
        List<Unit> ul = la.getUnit();
        for(Unit ui: ul)
            displayUnit(ui);
        
        /*
        List<Booking> bl = la.getBooking();
        
        for(Booking b : bl)
        {
            System.out.println("#### Booking ######");
            display(b);
            List<ClassList> cl = la.getClassList(b.getBookingID());
            System.out.println("#### Class List ######");
            for(ClassList c:cl)
            {
                displayClassList(c);
            }
  
        }
        
        
        //Add a question with answers
        String question = "How old are you?";
        String[] ans = {"3" , "8", "10", "14"};
        
        la.setMCBookingQuestion(2, question, ans, 120, 1);
        
        la.setTFBookingQuestion(2, "Are you male?", 30, true);
        
        List<BookingQuestion> bc = la.getBookingQuestion(2);
        for(BookingQuestion b:bc)
            displayQuestions(b);
       */
    }
    
        public static void display(Booking b)
    {
        System.out.println("Booking ID: " + b.getBookingID());
        System.out.println("Date: " + b.getDate());
        System.out.println("Booking Length: " + b.getBookingLength());
        System.out.println("Unit ID: " + b.getUnit().getUnitID());
        System.out.println("Venue ID: " + b.getVenue().getVenueID());
        System.out.println("Lecture ID: " + b.getLecture().getUserID());
        System.out.println();
        System.out.println();
    }
        
    public void displayClassList(ClassList v)
    {
        System.out.println("ID: " + v.getClassListID());

        System.out.println("StudentID: " + v.getStudent().getUserID());
        System.out.println("BookingID: " + v.getBooking().getBookingID());
        System.out.println("internal: " + v.isInternal());
        System.out.println("attendance: " + v.isAttendance());
        System.out.println();
        System.out.println();
    }
        
    public void displayUser(User u)
    {
        System.out.println("User ID: " + u.getUserID());
        System.out.println("Given Name: " + u.getGivenName());
        System.out.println("Last Name: " + u.getLastName());
        System.out.println("Preferred Name: " + u.getPrefName());
        System.out.println();
        System.out.println();
    }
    
    public void displayQuestions(BookingQuestion b)
    {
        Question q = qRepository.findById(b.getBookingQuestionKey().getQuestionID()).get();

        System.out.println("Question: " + q.getDescription());
        System.out.println("Time: " + q.getTime());
        
        List<MultipleChoice> mc = mcRepository.findByQuestion_QuestionID(q.getQID());
        
        for(MultipleChoice m:mc)
            System.out.println("choice: " + m.getDescription());
        
        System.out.println("Answer: " + mcRepository.findById(q.getAnswer()).get().getDescription());
        System.out.println();
        System.out.println();
    }
    
    public void displayUnit(Unit u)
    {
        
        System.out.println("Code: " + u.getUnitCode());
        System.out.println("Unit Name: " + u.getUnitName());
        System.out.println("Semester: " + u.getSemester().getDescription());
        System.out.println("Year: " + u.getYear());
        System.out.println("Lecture: " + u.getLecture().getGivenName());
        System.out.println();
    }
    
}
