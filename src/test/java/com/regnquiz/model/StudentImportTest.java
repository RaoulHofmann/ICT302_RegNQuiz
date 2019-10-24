/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

import com.regnquiz.model.imports.StudentImport;
import com.regnquiz.model.repositories.UserRepository;
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
public class StudentImportTest 
{
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private StudentImport si;
    
    @Test
    public void TestStudent()
    {
        si.ImportStudent("D:/Desktop/CORS0213.csv");
        Iterable<User> students = userRepo.findAll();
        
        for (User u : students)
        {
            display(u);
        }
    }
    
    public void display(User u)
    {
        System.out.println("Student Number: " + u.getUserID());
        System.out.println("Given Name: " + u.getGivenName());
        System.out.println("Last Name: " + u.getLastName());
        System.out.println();
        System.out.println();
    }
}
