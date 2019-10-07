/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model.repositories;

import org.springframework.data.repository.CrudRepository;
import com.regnquiz.model.ClassList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Author: Stuart Hepburn
 * Date: 25/9/2019
 * Version: 1
 */
public interface ClassListRepository  extends CrudRepository<ClassList, Integer>{
    @Query("SELECT new ClassList(c.classListID, c.student.userID, c.booking.bookingID, c.internal, c.attendance) FROM ClassList c  WHERE c.booking.bookingID=:bID")
    ClassList[] getBookingClass(@Param("bID") int bID);

    List<ClassList> findByBooking(int bookingId);
}
