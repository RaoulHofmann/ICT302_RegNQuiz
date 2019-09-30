/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

import javax.persistence.*;

/**
 * Author: Matthew MacLennan
 * Date: 22/9/2019
 * Version: 1
 * Comment: Contains question information for a booking
 */
@Entity
public class BookingQuestion 
{
    @EmbeddedId
    BookingQuestionKey id;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("BookingID")
    @JoinColumn(name = "BookingID", referencedColumnName="bookingID")
    private Booking booking;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("QuestionID")
    @JoinColumn(name = "QuestionID", referencedColumnName="questionID")
    private Question question;
    
    public BookingQuestion()
    {
        booking = new Booking();
        question = new Question();
    }
    
    public BookingQuestion(Booking booking, Question question)
    {
        this.booking = booking;
        this.question = question;
    }
    
    public Booking getBooking()
    {
        return booking;
    }
    
    public void setBooking(Booking booking)
    {
        this.booking = booking;
    }
    
    public Question getQuestion()
    {
        return question;
    }
    
    public void setQuestion(Question question)
    {
        this.question = question;
    }
}