/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model.repositories;

import com.regnquiz.model.BookingQuestion;
import com.regnquiz.model.Booking;
import com.regnquiz.model.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.CrudRepository;
import java.util.Set;
import com.regnquiz.model.Unit;

/**
 * Author: Matthew MacLennan
 * Date: 28/9/2019
 * Version: 1
 * Comment: Contains BookingQuestion repository function
 */
public interface BookingQuestionRepository extends CrudRepository<BookingQuestion, Integer>
{
    @Query("SELECT new BookingQuestion(b.booking, b.question) FROM BookingQuestion b WHERE b.id = id")
    Set<BookingQuestion> getBookingQuestions(@Param("booking") Booking booking, @Param("question") Question question);
}
