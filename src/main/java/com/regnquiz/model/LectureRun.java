/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

import com.regnquiz.model.repositories.*;

import java.util.ArrayList;
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

    @Autowired
    private MultipleChoiceRepository multipleChoiceRepository;

    @Autowired
    private StudentAnswerRepository studentAnswerRepository;

    @Autowired
    private UserRepository userRepository;

    private Booking b = null;
    private int attendanceCounter = 0;
    private int activeQuestion = -1;
    private boolean quizFinished = false;
    List<BookingQuestion> bq = null;
    List<ClassList> cl = null;
    List<MultipleChoice> mc = null;
    List<StudentAnswer> sa = new ArrayList<StudentAnswer>();

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
        classListRepository.saveAll(cl);
        bookingQuestionRepository.saveAll(bq);
        studentAnswerRepository.saveAll(sa);
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
    public void setStudentAnswer(int answerID, int studentID){
        StudentAnswer studentAnswer = new StudentAnswer();
        studentAnswer.setStudent(userRepository.findById(studentID).get());
        studentAnswer.setStudentAnswer(multipleChoiceRepository.findByAnswerID(answerID));
        this.sa.add(studentAnswer);
    }

    @Transactional
    public void startQuestion(){
        activeQuestion = 0;
        mc = multipleChoiceRepository.findByQuestion_QuestionID(bq.get(0).getQuestion().getQID());
    }

    @Transactional
    public void nextQuestion(){
        activeQuestion += 1;
        mc = multipleChoiceRepository.findByQuestion_QuestionID(bq.get(activeQuestion).getQuestion().getQID());
    }

    @Transactional
    public int getActiveQuestion(){
        if(this.activeQuestion == bq.size()-1){
            this.quizFinished = true;
        }
        return this.activeQuestion;
    }

    @Transactional
    public boolean getQuizFinished() {
        return this.quizFinished;
    }

    @Transactional
    public List<ClassList> getClassList(){
        return this.cl;
    }

    @Transactional
    public ClassList getStudent(int studentID){
        ClassList student = null;
        for(int i=0; i<cl.size(); i++){
            if(cl.get(i).getStudent().getUserID() == studentID){
                student = cl.get(i);
            }
        }
        return student;
    }

    @Transactional
    public List<BookingQuestion> getBookingQuestions(){
         return this.bq;
    }

    @Transactional
    public List<MultipleChoice> getMultipleChoice(){
        return this.mc;
    }
}
