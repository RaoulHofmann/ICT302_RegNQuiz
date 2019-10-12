/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model.repositories;

import com.regnquiz.model.QuestionReview;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 * Author: Stuart Hepburn
 * Date: 12/10/2019
 * Version: 1
 * 
 */
public interface QuestionReviewRepository extends CrudRepository<QuestionReview, Integer> {
    List<QuestionReview> findByUserID(int userID);
    List<QuestionReview> findByUserIDAndUnitID(int userID, int unitID);
}
