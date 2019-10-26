/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.regnquiz.model.repositories.MultipleChoiceRepository;
import com.regnquiz.model.repositories.StudentAnswerRepository;
import com.regnquiz.model.MultipleChoice;
import com.regnquiz.model.repositories.QuestionRepository;

/**
 *
 * @author Matthew MacLennan
 */
import org.junit.Test;
@RunWith(SpringRunner.class)
@SpringBootTest
public class MultipleChoiceRepoTest 
{
    @Autowired
    MultipleChoiceRepository mcRepo;
    
    @Autowired
    QuestionRepository qRepo;
    
    @Autowired
    StudentAnswerRepository saRepo;
    
    @Test
    public void testMC()
    {
        MultipleChoice mc = new MultipleChoice();
        
        mc.setQuestion(qRepo.findById(1).get());
        mc.addStudentAnswer(saRepo.findByStudent_userID(11).get(0));
        mc.setDescription("test");
        mc.setAnswerID(50);
        
        mcRepo.save(mc);
        
        display(mcRepo.findByAnswerID(10));
        
        Iterable<MultipleChoice> all = mcRepo.findAll();
        for(MultipleChoice temp : all)
            display(temp);
    }
    
    public static void display(MultipleChoice mc)
    {
        System.out.println("Answer ID: " + mc.getAnswerID());
        System.out.println("Description: " + mc.getDescription());
        System.out.println("Question ID: " + mc.getQuestion().getQID());
        System.out.println();
        System.out.println();
    }
}
