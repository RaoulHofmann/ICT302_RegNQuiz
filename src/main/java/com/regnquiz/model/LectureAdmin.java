/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

import com.regnquiz.model.repositories.BookingRepository;
import com.regnquiz.model.repositories.BookingQuestionRepository;
import com.regnquiz.model.repositories.ClassListRepository;
import com.regnquiz.model.repositories.UserRepository;
import com.regnquiz.model.repositories.QuestionRepository;
import com.regnquiz.model.repositories.MultipleChoiceRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author: Stuart Hepburn
 * Date: 11/10/2019
 * Version: 1
 * Comment: this class will run all the add components for the lecture user 
 */

@Service
public class LectureAdmin {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClassListRepository clRepository;
    @Autowired
    private BookingQuestionRepository bqRepo;
    @Autowired
    private QuestionRepository questionRepo;
    @Autowired
    private MultipleChoiceRepository mcRepo;
   
    
    private User u = new User();
    private List<Booking> b;
    
    @Transactional
    public void setUser(Integer userID)
    {
        Optional<User> g = userRepository.findById(userID);
        u = g.get();
    }
    
    @Transactional
    public User getUser()
    {
        return u;
    }  
    
    @Transactional
    public List<Booking> getBooking()
    {
        b =  bookingRepository.findByLecture(u);
        return b;
    }
    
    @Transactional
    public List<ClassList> getClassList(int bookingID)
    {
        return clRepository.findByBookingBookingID(bookingID);
    }
    
    @Transactional
    public List<BookingQuestion> getBookingQuestion(int bookingID)
    {
        return bqRepo.findByBooking_BookingID(bookingID);
    }
    
    @Transactional
    public boolean setMCBookingQuestion(int bookingID, String question, String[] chooses, int time, int answer)
    {
        Question q = new Question();
        q.setDescription(question);
        q.setTime(time);
        q = questionRepo.save(q);

        int i = 0;
        
        for(String s:chooses)
        {
            MultipleChoice mc = new MultipleChoice(q, s);
            mc = mcRepo.save(mc);
            if(i==answer)
                q.setAnswer(mc.getMCID());
            
            i++;
        }
         
        q = questionRepo.save(q);
        
        BookingQuestion bc = new BookingQuestion();
        bc.setBookingQuestionKey(new BookingQuestionKey(bookingID, q.getQID()));
        bc.setBooking(bookingRepository.findById(bookingID).get());
        bc.setQuestion(q);
        bqRepo.save(bc);
        
        return true;
    }
    
    //set up a true false question
    @Transactional
    public boolean setTFBookingQuestion(int bookingID, String question, int time, boolean answer)
    {
        Question q = new Question();
        q.setDescription(question);
        q.setTime(time);
        q = questionRepo.save(q);

        MultipleChoice mct = new MultipleChoice(q, "True");
        mct = mcRepo.save(mct);
        
        if(answer==true)
            q.setAnswer(mct.getMCID());
        
        MultipleChoice mcf = new MultipleChoice(q, "False");
        mcf = mcRepo.save(mcf);
        
        if(answer==false)
            q.setAnswer(mcf.getMCID());
 
        q = questionRepo.save(q);
        
        BookingQuestion bc = new BookingQuestion();
        bc.setBookingQuestionKey(new BookingQuestionKey(bookingID, q.getQID()));
        bc.setBooking(bookingRepository.findById(bookingID).get());
        bc.setQuestion(q);
        bqRepo.save(bc);
        
        return true;
    }
}
