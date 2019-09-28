/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

import com.regnquiz.model.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * 
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    
    @Test
    public void InsertData()
    {
        User u = new User(785895, "test5", "Test", "Test");
        u = userRepository.save(u);
        
        Iterable<User> g = userRepository.findAll();
        
        for(User r : g)
            display(r);
    }
    
    public static void display(User u)
    {
        System.out.println("User ID: " + u.getUserID());
        System.out.println("Given Name: " + u.getGivenName());
        System.out.println("Last Name: " + u.getLastName());
        System.out.println("Preferred Name: " + u.getPrefName());

        System.out.println();
        System.out.println();
    }
}
