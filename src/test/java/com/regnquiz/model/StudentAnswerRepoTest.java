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
import com.regnquiz.model.repositories.StudentAnswerRepository;
import com.regnquiz.model.User;
import com.regnquiz.model.repositories.MultipleChoiceRepository;
import com.regnquiz.model.repositories.UserRepository;
import com.regnquiz.model.MultipleChoice;
import org.junit.Test;
import java.util.List;
/**
 *
 * @author Matthew MacLennan
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentAnswerRepoTest 
{
    @Autowired
    private StudentAnswerRepository saRepo;
    
    @Autowired
    private MultipleChoiceRepository mcRepo;
    
    @Autowired
    private UserRepository userRepo;
    
    @Test
    public void testStudentAnswer()
    {
        StudentAnswer sa = new StudentAnswer(userRepo.findById(11).get(), mcRepo.findById(1).get());
        saRepo.save(sa);
        
        display(saRepo.findById(sa.getId()).get());
        
        
        List<StudentAnswer> test = saRepo.findByStudent_userID(11);
        for(int i = 0; i < test.size(); i++)
            display(test.get(i));
       
        Iterable<StudentAnswer> allSA = saRepo.findAll();
        for(StudentAnswer temp : allSA)
            display(temp);
    }
    
    public static void display(StudentAnswer sa)
    {
        System.out.println("Answer ID: " + sa.getStudentAnswer().getMCID());
        System.out.println("Student ID: " + sa.getStudent().getUserID());
        System.out.println();
        System.out.println();
    }
    
}