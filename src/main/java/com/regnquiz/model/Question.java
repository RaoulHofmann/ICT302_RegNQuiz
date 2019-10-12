/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

import javax.persistence.*;
import java.util.Set;
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
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer questionID;
    private String description;
    private Integer time;
    private Integer answerID;
    
    @OneToMany(mappedBy = "question")
    private Set<BookingQuestion> bookingQuestion;
    
    @OneToMany(mappedBy = "question")
    private Set<MultipleChoice> multipleChoice;
        
    public Question() {}
    public Question(String desc, Integer time, Integer answer)
    {
        this.description = desc;
        this.time = time;
        //this.answer = answer;
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
    
    public void setBookingQuestions(Set<BookingQuestion> bookingQuestion)
    {
        this.bookingQuestion = bookingQuestion;
    }
    
    public void addBookingQuestion(BookingQuestion bq)
    {
        bookingQuestion.add(bq);
    }
    
    public Set<MultipleChoice> getMultipleChoice()
    {
        return multipleChoice;
    }
    
    public void setMultipleChoice(Set<MultipleChoice> multipleChoice)
    {
        this.multipleChoice = multipleChoice;
    }
    
    public void MultipleChoice(MultipleChoice mc)
    {
        multipleChoice.add(mc);
    }
}
