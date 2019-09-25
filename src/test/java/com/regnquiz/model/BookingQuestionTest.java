/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

import java.util.Date;
/**
 *
 * @author Matthew MacLennan
 */
public class BookingQuestionTest 
{
    public static void main(String args[])
    {
        BookingQuestion a = new BookingQuestion();
        display(a);
        
        a.setBooking(new Booking(1234, new Date(), 4321, "test1", new Unit(), new Venue(), new User()));
        a.setQuestion(new Question("test2", 5678, 8765));
        display(a);
        
        BookingQuestion b = new BookingQuestion(new Booking(9012, new Date(), 2109, "test3", new Unit(), new Venue(), new User()), new Question("test4", 3456, 6543));
        display(b);
        
        b.setBooking(new Booking(7890, new Date(), 987, "test5", new Unit(), new Venue(), new User()));
        b.setQuestion(new Question("test6", 2345, 5432));
        display(b);
    }
    
    public static void display(BookingQuestion b)
    {
        System.out.println("Booking ID: " + b.getBooking().getBookingID());
        System.out.println("Question ID: " + b.getQuestion().getQID());
        System.out.println();
        System.out.println();
    }
}
