/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model.repositories;

import com.regnquiz.model.StudentAnswerReview;

import java.util.Date;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 * Author: Stuart Hepburn
 * Date: 12/10/2019
 * Version: 1
 * 
 */
public interface StudentAnswerReviewRepository extends CrudRepository<StudentAnswerReview, Integer> {
    List<StudentAnswerReview> findByUserID(int userID);
    List<StudentAnswerReview> findByUserIDAndUnitID(int userID, int unitID);
    List<StudentAnswerReview> findByUserIDAndUnitIDAndBookingDate(int userID, int unitID, Date date);
}
