/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.regnquiz.model.repositories.TypeRepository;

/**
 *
 * @author Matthew MacLennan
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TypeRepositoryTest 
{
    @Autowired
    private TypeRepository typeRepo;
    
    @Test
    public void testType()
    {
        display(typeRepo.findById(1).get());
        
        Iterable<Type> allTypes = typeRepo.findAll();
        for (Type t : allTypes)
            display(t);
    }
    
    public static void display(Type t)
    {
        System.out.println("Type ID: " + t.getTypeID());
        System.out.println("Description: "+ t.getDescription());
        System.out.println();
        System.out.println();
    }
}
