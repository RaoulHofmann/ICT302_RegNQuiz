/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model.repositories;

import com.regnquiz.model.MultipleChoice;
import com.regnquiz.model.StudentAnswer;
import com.regnquiz.model.StudentAnswerKey;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Author: Matthew MacLennan
 * Date: 28/9/2019
 * Version: 1
 * Comment: Contains StudentAnswer repository function
 */
public interface StudentAnswerRepository extends CrudRepository<StudentAnswer, StudentAnswerKey>
{
    //@Query("SELECT new StudentAnswer(c.student, c.answer) FROM StudentAnswer s WHERE c.id = :id")
    //StudentAnswer getStudentAnswer(@Param("id") StudentAnswerKey id);

    @Modifying
    @Query(value= "INSERT INTO StudentAnswer (answerID, userID) VALUES (:answerID, :userID)",nativeQuery = true)
    int insertStudentAnswer(@Param("answerID") int answerID, @Param("userID") int userID);
    
    List<StudentAnswer>findByStudent_userID(Integer userID);
}
