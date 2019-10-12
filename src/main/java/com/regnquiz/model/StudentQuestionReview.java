/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

import com.regnquiz.model.repositories.QuestionReviewRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author: Stuart Hepburn
 * Date: 12/10/2019
 * Version: 1
 * 
 */
@Service
public class StudentQuestionReview {
    @Autowired
    private QuestionReviewRepository qrRepo;
    
    @Transactional
    public Iterable<QuestionReview> getQuestions()
    {
        return qrRepo.findAll();
    }
    
    @Transactional
    public List<QuestionReview> getQuestions(int userID)
    {
        return qrRepo.findByUserID(userID);
    }
    
    @Transactional
    public List<QuestionReview> getQuestions(int userID, int unitID)
    {
        return qrRepo.findByUserIDAndUnitID(userID, unitID);
    }
}
