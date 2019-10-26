/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

/**
 *
 * @author Matthew MacLennan
 * @date 13/10
 * @version 1
 * @comment test class for BookingQuestion's key
 */
public class BookingQuestionKeyTest 
{
    public static void main (String args[])
    {
        BookingQuestionKey a = new BookingQuestionKey();
        display(a);
        
        a.setBookingID(1);
        a.setQuestionID(2);
        display(a);
        
        BookingQuestionKey b = new BookingQuestionKey(3, 4);
        display(b);
        
        b.setBookingID(5);
        b.setQuestionID(6);
        display(b);
    }
    
    public static void display(BookingQuestionKey bqk)
    {
        System.out.println("Booking ID: " + bqk.getBookingID());
        System.out.println("Question ID: " + bqk.getQuestionID());
        System.out.println();
        System.out.println();
    }
}
