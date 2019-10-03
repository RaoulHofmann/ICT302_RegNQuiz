/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model.repositories;

import org.hibernate.Hibernate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import com.regnquiz.model.Booking;

import java.awt.print.Book;
import java.sql.Time;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Author: Stuart Hepburn
 * Date: 25/9/2019
 * Version: 1
 * 
 */
public interface BookingRepository extends CrudRepository<Booking, Integer> {
    //@Query("SELECT case when count(b.bookingID) = 0 then 0 else b.bookingID end FROM Booking b WHERE b.date= :date AND b.time=:time AND b.unitID=:unitID AND venueID=:venueID")
   // int findByBooking(@Param("date") Date date, @Param("time") Time time, @Param("unitID") int unitID, @Param("venueID") int venueID);
    
    //@Query("SELECT accessCode FROM Booking b WHERE b.bookingID = : bID")
    //String getAccessCode(@Param("bID") int bookingID);

    @Query("SELECT b FROM Booking b WHERE b.lecture.userID = :uID")
    List<Booking> findByUserID(@Param("uID") int userID);
}
