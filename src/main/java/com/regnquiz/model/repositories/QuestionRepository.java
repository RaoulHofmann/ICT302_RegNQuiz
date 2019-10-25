/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model.repositories;

import com.regnquiz.model.Question;
import com.regnquiz.model.BookingQuestion;
import com.regnquiz.model.MultipleChoice;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.Set;
/**
 * Author: Matthew MacLennan
 * Date: 28/9/2019
 * Version: 1
 * Comment: Repository for Question class
 */
public interface QuestionRepository extends CrudRepository<Question, Integer>
{
    //@Query("SELECT case when count(q.QID) = 0 then 0 else q.QID end FROM Question q WHERE q.description = :description AND q.time = :time AND q.bookingQuestion = :bookingQuestion AND q.multipleChoice = :multipleChoice")
   // Question getQuestion(@Param("description") String description, @Param("time") Integer time, @Param("bookingQuestion") Set<BookingQuestion> bookingQuestion, @Param("multipleChoice") Set<MultipleChoice> multipleChoice);

    @Modifying
    @Query(value= "INSERT INTO Question (description, time) VALUES (:description, :time)",nativeQuery = true)
    int insertQuestionDescriptionTime(@Param("description") String description, @Param("time") int time);

    @Modifying
    @Query(value= "INSERT INTO Question (answerID) VALUES (:answerID)",nativeQuery = true)
    int insertQuestionAnswer(@Param("answerID") int answerID);

    @Modifying
    @Query(value= "UPDATE Question SET answerID = :answerID WHERE questionID = :questionID",nativeQuery = true)
    int updateQuestionAnswer(@Param("answerID") int answer, @Param("questionID") int questionID);
}
