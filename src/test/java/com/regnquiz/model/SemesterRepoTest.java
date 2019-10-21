/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.regnquiz.model.repositories.SemesterRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Matthew MacLennan
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SemesterRepoTest 
{
    @Autowired
    private SemesterRepository semRepo;
    
    @Test
    public void testSem()
    {
        Semester sem = new Semester();
        sem.setDescription("test");
        sem.setSemID(2);
        
        semRepo.save(sem);
        
        display(semRepo.findById(9).get());
        
        Iterable<Semester> allSems = semRepo.findAll();
        for(Semester s : allSems)
            display(s);
    }
    
    public static void display(Semester s)
    {
        System.out.println("Sem ID: " + s.getSemID());
        System.out.println("Description: " + s.getDescription());
        System.out.println();
        System.out.println();
    }
}
