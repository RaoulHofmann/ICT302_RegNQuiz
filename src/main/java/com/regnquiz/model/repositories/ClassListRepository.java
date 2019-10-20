/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model.repositories;

import com.regnquiz.model.ClassList;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Author: Stuart Hepburn
 * Date: 25/9/2019
 * Version: 1
 */
public interface ClassListRepository  extends CrudRepository<ClassList, Integer>{
    @Query("SELECT new ClassList(c.classListID, c.student.userID, c.booking.bookingID, c.internal, c.attendance) FROM ClassList c  WHERE c.booking.bookingID=:bID")
    ClassList[] getBookingClass(@Param("bID") Integer bID);
    
    List<ClassList> findByBookingBookingID(Integer bookingID);
    
}
