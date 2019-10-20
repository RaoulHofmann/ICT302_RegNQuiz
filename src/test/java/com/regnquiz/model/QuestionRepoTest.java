/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

import com.regnquiz.model.repositories.QuestionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Matthew MacLennan
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionRepoTest 
{
    @Autowired
    private QuestionRepository questionRepo;
    
    @Test
    public void TestQuestion()
    {
        Question question = new Question();
        question.setDescription("test");
        question.setTime(90);
        question.setAnswer(1);
        questionRepo.save(question);
        
        display(questionRepo.findById(1).get());
    }   
    
    public static void display(Question q)
    {
        System.out.println("Question ID: " + q.getQID());
        System.out.println("Description: " + q.getDescription());
        System.out.println("Time: " + q.getTime());
        System.out.println("Answer ID: " + q.getAnswer());
        System.out.println();
        System.out.println();
    }
}
