/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Author: Matthew MacLennan
 * Date: 24/9/2019
 * Version: 1
 * Comment: Key for BookingQuestion class
 */
public class BookingQuestionKey implements Serializable
{
    @Column(name = "bookingID")
    private Integer BookingID;
    
    @Column(name = "questionID")
    private Integer QuestionID;
    
    public BookingQuestionKey()
    {
        
    }
    
    public BookingQuestionKey(int bookingID, int questionID)
    {
        this.BookingID = bookingID;
        this.QuestionID = questionID;
    }
    
    public int getBookingID()
    {
        return this.BookingID;
    }
    
    public void setBookingID(int bookingID)
    {
        this.BookingID = bookingID;
    }
    
    public int getQuestionID()
    {
        return QuestionID;
    }
    
    public void setQuestionID(int questionID)
    {
        this.QuestionID = questionID;
    }
}
