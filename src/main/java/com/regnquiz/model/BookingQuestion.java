/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("BookingID")
    @JoinColumn(name = "BookingID", referencedColumnName="bookingID")
    private Booking booking; // Booking the question belongs to 

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("QuestionID")
    @JoinColumn(name = "QuestionID", referencedColumnName="questionID")
    private Question question; // The question itself
    
    public BookingQuestion()
    {
        booking = new Booking();
        question = new Question();
        id = new BookingQuestionKey(booking.getBookingID(), question.getQID()); // Ensure the key is upto date with the data in the object
    }
    
    public BookingQuestion(Booking booking, Question question)
    {
        this.booking = booking;
        this.question = question;
        id = new BookingQuestionKey(booking.getBookingID(), question.getQID());
    }
    
    public Booking getBooking()
    {
        return booking;
    }
    
    public void setBooking(Booking booking)
    {
        this.booking = booking;
        this.id.setBookingID(this.booking.getBookingID());
    }
    
    public Question getQuestion()
    {
        return question;
    }
    
    public void setQuestion(Question question)
    {
        this.question = question;
        this.id.setQuestionID(this.question.getQID());
    }
    
    public void setBookingQuestionKey(BookingQuestionKey ID)
    {
        this.id = ID;
    }
    
    public BookingQuestionKey getBookingQuestionKey()
    {
        return id;
    }
}
