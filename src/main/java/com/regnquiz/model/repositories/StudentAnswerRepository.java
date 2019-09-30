/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model.repositories;

import com.regnquiz.model.StudentAnswer;
import com.regnquiz.model.StudentAnswerKey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.CrudRepository;

/**
 * Author: Matthew MacLennan
 * Date: 28/9/2019
 * Version: 1
 * Comment: Contains StudentAnswer repository function
 */
public interface StudentAnswerRepository extends CrudRepository<StudentAnswer, Integer>
{
    @Query("SELECT new StudentAnswer(c.student, c.answer) FROM StudentAnswer s WHERE c.id = :id")
    StudentAnswer getStudentAnswer(@Param("id") StudentAnswerKey id);
}
