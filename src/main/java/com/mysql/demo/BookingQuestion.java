/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mysql.demo;

import javax.persistence.*;
import java.util.Set;
/**
 * Author: Matthew MacLennan
 * Date: 22/9/2019
 * Version: 1
 * Comment: Contains question information for a booking
 */
@Entity
public class BookingQuestion 
{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Booking booking;
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
