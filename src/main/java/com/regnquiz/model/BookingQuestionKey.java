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
    private Integer questionID;
    
    public BookingQuestionKey()
    {}
    
    public BookingQuestionKey(Integer bid, Integer qid)
    {
        this.bookingID = bid;
        this.questionID = qid;
    }
    
    public Integer getBookingID()
    {
        return bookingID;
    }
    
    public void setBookingID(Integer bid)
    {
        this.bookingID = bid;
    }
    
    public Integer getQuestionID()
    {
        return questionID;
    }
    
    public void setQuestionID(Integer qid)
    {
        this.questionID = qid;
    }
}
