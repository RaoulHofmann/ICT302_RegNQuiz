/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

import com.regnquiz.model.repositories.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

/**
 * Author: Stuart Hepburn and Raoul Hofmann
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

    private Booking b = null; // Start with no booking, needs to be selected
    private int attendanceCounter = 0;
    private int activeQuestion = -1; // Default no question active
    private boolean quizFinished = false;
    private boolean timeout = false;
    private int answerCounter = 0;
    List<BookingQuestion> bq = null;
    List<ClassList> cl;
    List<MultipleChoice> mc;
    List<StudentAnswer> sa = new ArrayList<>();
    HashMap<String, HashMap<String, Integer>> questionAnswerCounter;

    // Select booking
    @Transactional
    public void OpenLecture(int bookingID)
    {
        b = bookingRepository.findById(bookingID).get();

        cl = classListRepository.findByBookingBookingID(bookingID);

        bq = bookingQuestionRepository.findByBooking_BookingID(bookingID);

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

        for(int i = 0; i<cl.size(); i++){
            if(cl.get(i).getStudent().getUserID() == studentID){
                cl.get(i).setAttendance();
                enrolled = true;
                this.attendanceCounter++;
            }
        }
        
        return enrolled;
    }

    @Transactional
    public int getAttendanceCount(){
        return this.attendanceCounter;
    }
    
    // Save any updates made to the booking, questions or classlist
    @Transactional
    public void saveLecture()
    {
        bookingRepository.save(b);
        bookingQuestionRepository.saveAll(bq);
        classListRepository.saveAll(cl);
        for(int i=0; i<sa.size(); i++){
            try{
                studentAnswerRepository.insertStudentAnswer(sa.get(i).getId().getAnswerID(), sa.get(i).getId().getUserID());
            }catch(DataIntegrityViolationException e){
                System.out.println(e);
            }
        }
    }

    @Transactional
    public Booking getBooking(){ return this.b; }

    // is the booking active
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
        studentAnswer.setId(new StudentAnswerKey(studentID, answerID));
        sa.add(studentAnswer);
    }

    @Transactional
    public List<StudentAnswer> getStudentAnswer(){
        return this.sa;
    }

    @Transactional
    public void startQuestion(){
        activeQuestion = 0;
        mc = multipleChoiceRepository.findByQuestion_QuestionID(bq.get(activeQuestion).getQuestion().getQID());
        questionAnswerCounter = new HashMap<>();
        HashMap<String, Integer> answerMap = new HashMap<>();
        for (int i=0; i<mc.size(); i++){
            answerMap.put(mc.get(i).getDescription(), 0);
        }
        questionAnswerCounter.put(bq.get(activeQuestion).getQuestion().getDescription(),answerMap);

    }

    @Transactional
    public void nextQuestion(){
        timeout = false;
        answerCounter = 0;
        activeQuestion += 1;
        mc = multipleChoiceRepository.findByQuestion_QuestionID(bq.get(activeQuestion).getQuestion().getQID());
        HashMap<String, Integer> answerMap = new HashMap<>();
        for (int i=0; i<mc.size(); i++){
            answerMap.put(mc.get(i).getDescription(), 0);
        }
        questionAnswerCounter.put(bq.get(activeQuestion).getQuestion().getDescription(),answerMap);
    }

    @Transactional
    public int getActiveQuestion(){
        if(this.activeQuestion == bq.size()){
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

    // Return how many questions have been answered
    @Transactional
    public HashMap<String, HashMap<String, Integer>> getQuestionAnswerCounter(){
        return this.questionAnswerCounter;
    }

    @Transactional
    public void setQuestionAnswerCounter(int aID){
        answerCounter++;
        HashMap<String, Integer> answerMap = questionAnswerCounter.get(bq.get(activeQuestion).getQuestion().getDescription());
        MultipleChoice multipleChoice = multipleChoiceRepository.findByAnswerID(aID);
        answerMap.put(multipleChoice.getDescription(), answerMap.get(multipleChoice.getDescription())+1);
        questionAnswerCounter.put(bq.get(activeQuestion).getQuestion().getDescription(), answerMap);
    }

    @Transactional
    public Integer getQuestionTimer(){
        return bq.get(activeQuestion).getQuestion().getTime();
    }

    @Transactional
    public void setTimeout(){
        this.timeout = true;
    }

    @Transactional
    public int getAnswerCounter(){
        return answerCounter;
    }

    @Transactional
    public boolean getTimeout(){
        return this.timeout;
    }
    
    @Transactional
    public void autoAttendance()
    {
        //System.out.println("############################# " );
        //System.out.println("############################# " );
        //System.out.println("############################# " );
        
        for(ClassList c: cl)
        {
            //Random rand = new Random();
            //if(c.getStudent().getUserID()!=4){
                if(c.isAttendance()==false)
                {
                    setAttendance(c.getStudent().getUserID());
                    //System.out.println("############################# " + c.getStudent().getUserID());

                }
            //}
            
        } 
    }
    
    @Transactional
    public void autoStudentAnswer()
    {
        //System.out.println("############################# " );
       // System.out.println("############################# " );
        //System.out.println("############################# " );
        Set<MultipleChoice> ms = bq.get(activeQuestion).getQuestion().getMultipleChoice();
        int cnt = ms.size();
        int answer;
        Random rand = new Random();
        
        for(ClassList c: cl)
        {
           answer = rand.nextInt(cnt);
           int i = 1;
           //if(c.getStudent().getUserID()!=4){
            for(MultipleChoice m:ms)
            {
                if(i==answer)
                {
                    setStudentAnswer(m.getAnswerID(),c.getStudent().getUserID());
                    setQuestionAnswerCounter(m.getAnswerID());
                }
                i++; 
            }
          //}
        }
        
       // studentAnswerRepository.saveAll(sa);
 
    }
    
}
