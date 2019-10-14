/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

import org.hibernate.annotations.Immutable;

import java.sql.Time;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Author: Stuart Hepburn
 * Date: 12/10/2019
 * Version: 1
 * Comment: Retrieves the data from the questions
 */
@Entity 
@Immutable
@Table(name="vstudentanswers")
public class QuestionReview {
    @Id    
    private int correctAnswerID;
    
    private int userID;
    
    private int unitID;
    private String unitCode;
    private String unitName;
    private Date bookingDate;
    private Time bookingTime;
    private String question;
    private String correctAnswer;
    
    private int studentAnswer;
    private String answered;
    
    public QuestionReview()
    {
        
    }
    
    public int getUserID()
    {
        return userID;
    }
    
    public int getUnitID()
    {
        return unitID;
    }
    
    public int getCorrectAnswerID()
    {
        return this.correctAnswerID;
    }
    
    public int getStudentAnswer()
    {
        return this.studentAnswer;
    }
    
    public String getUnitCode()
    {
        return unitCode;
    }
    
    public String getUnitName()
    {
        return unitName;
    }
    
    public Date getBookingDate()
    {
        return bookingDate;
    }
    
    public Time getBookingTime()
    {
        return bookingTime;
    }
    
    public String getQuestion()
    {
        return question;
    }
    
    public String getAnswer()
    {
        return correctAnswer;
    }
    
    public String getStudentResult()
    {
        return answered;
    }
}
