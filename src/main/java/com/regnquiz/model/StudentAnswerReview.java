/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import org.hibernate.annotations.Immutable;

import java.sql.Time;
import java.util.Date;
import javax.persistence.*;

/**
 * Author: Stuart Hepburn
 * Date: 12/10/2019
 * Version: 1
 * Comment: Retrieves the data from the questions
 */
@Entity 
@Immutable
@Table(name="vstudentanswers")
public class StudentAnswerReview {
    @Id    
    private int correctAnswerID;
    
    private int userID;
    
    private int unitID;
    private String unitCode;
    private String unitName;
    @Temporal(TemporalType.DATE)
    private Date bookingDate;
    private Time bookingTime;
    @CsvBindByName(column = "Question")
    @CsvBindByPosition(position = 0)
    private String question;
    @CsvBindByName(column = "CorrectAnswer")
    @CsvBindByPosition(position = 1)
    private String correctAnswer;

    @CsvBindByName(column = "AnswerGiven")
    @CsvBindByPosition(position = 2)
    private int studentAnswer;
    @CsvBindByName(column = "Answered")
    @CsvBindByPosition(position = 0)
    private String answered;
    
    public StudentAnswerReview()
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
