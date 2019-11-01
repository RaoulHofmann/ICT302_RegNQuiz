/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;
import java.util.HashSet;
/**
 * Author: Matthew MacLennan
 * Date: 22/9/2019
 * Version: 1
 * Comment: Contains question data
 */
@Entity
public class Question 
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer questionID;
    private String description; // Description of the question itself
    private Integer time; // Time to answer the question
    private Integer answerID; // Answer of the question

    @JsonBackReference
    @OneToMany(mappedBy = "question")
    private Set<BookingQuestion> bookingQuestion;

    @JsonBackReference
    @OneToMany(mappedBy = "question")
    private Set<MultipleChoice> multipleChoice;
        
    public Question() 
    {
        bookingQuestion = new HashSet<>();
        multipleChoice = new HashSet<>();
    }
    
    public Question(String desc, Integer time, Integer answer)
    {
        this.description = desc;
        this.time = time;
        
        bookingQuestion = new HashSet<>();
        multipleChoice = new HashSet<>();
    }

    public Integer getQuestionID() {
        return questionID;
    }

    public void setQuestionID(Integer questionID) {
        this.questionID = questionID;
    }

    public Integer getQID()
    {
        return questionID;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public void setDescription(String desc)
    {
        this.description = desc;
    }
    
    public Integer getTime()
    {
        return time;
    }
    
    public void setTime(Integer time)
    {
        this.time = time;
    }

    public Integer getAnswer()
    {
        return answerID;
    }
    
    public void setAnswer(Integer answer)
    {
        this.answerID = answer;
    }
    
    public Set<MultipleChoice> getMultipleChoice()
    {
        return multipleChoice;
    }
    
    public Set<BookingQuestion> getBookingQuestions()
    {
        return bookingQuestion;
    }
    
    public void setBookingQuestions(Set<BookingQuestion> bq)
    {
        this.bookingQuestion = bq;
    }
    
    public void addBookingQuestion(BookingQuestion bq)
    {
        bookingQuestion.add(bq);
    }

    public void setMultipleChoice(Set<MultipleChoice> mc)
    {
        this.multipleChoice = mc;
    }
    
    public void addMultipleChoice(MultipleChoice mc)
    {
        multipleChoice.add(mc);
    }
}
