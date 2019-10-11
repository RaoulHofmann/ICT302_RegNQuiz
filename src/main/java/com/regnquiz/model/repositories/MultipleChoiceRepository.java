/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model.repositories;

import com.regnquiz.model.MultipleChoice;
import com.regnquiz.model.Question;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.Set;

/**
 * Author: Matthew MacLennan
 * Date: 28/9/2019
 * Version: 1
 * Comment: Contains MultipleChoice repository function
 */
public interface MultipleChoiceRepository extends CrudRepository<MultipleChoice, Integer>
{
    //@Query("SELECT case when count(m.answerID) = 0 then 0 else m.answerID end FROM MultipleChoice m WHERE m.description = :description AND m.question = :question")
    //Set<MultipleChoice> getMC(@Param("description") String description, @Param("question") Question question);
    List<MultipleChoice>findByQuestion_QuestionID(Integer questionID);
}
