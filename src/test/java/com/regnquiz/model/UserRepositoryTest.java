/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

import com.regnquiz.model.repositories.TypeRepository;
import com.regnquiz.model.repositories.UserRepository;
import com.regnquiz.model.repositories.UserTypeRepository;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Author: Stuart Hepburn
 * Date: 3/10/2019
 * Version: 1
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private TypeRepository typeRepository;
    
    @Autowired
    private UserTypeRepository userTypeRepository;
    

    
    @Test
    public void InsertData()
    {
        //The different types of users
        Optional<Type> ost = typeRepository.findById(3);
        Type studentType = ost.get();
        Optional<Type> olt = typeRepository.findById(2);
        Type lectureType = olt.get();
        Optional<Type> oat = typeRepository.findById(1);
        Type adminType = oat.get();
        
        //Create Super user
        User a = new User();
        a.setGivenName("Super");
        a.setLastName("User"); 
        //a.setPrefName("");
        a.setUserID(1);
        userRepository.save(a);
             
        UserType utA = new UserType();
        utA.setPassword("ict302");
        utA.setUserType(new UserIdent(1,1));
        utA.setUser(a);        
        utA.setType(adminType);
        userTypeRepository.save(utA);
        
        display(a);
        
        //create a normal admin
        User b = new User();
        b.setGivenName("John");
        b.setLastName("Anderson"); 
        b.setPrefName("AJ");
        b.setUserID(10001000);
        userRepository.save(b);
             
        UserType utB = new UserType();
        utB.setPassword("ict302");
        utB.setUserType(new UserIdent(1,10001000));
        utB.setUser(b);        
        utB.setType(adminType);
        userTypeRepository.save(utB);
        
        display(b);
        
        //create a staff
        User c = new User();
        c.setGivenName("Jack");
        c.setLastName("Smith"); 
        c.setPrefName("Jack");
        c.setUserID(22222222);
        userRepository.save(c);
             
        UserType utC = new UserType();
        utC.setPassword("ict302");
        utC.setUserType(new UserIdent(2,c.getUserID()));
        utC.setUser(c);        
        utC.setType(lectureType);
        userTypeRepository.save(utC);
        
        display(c);
        
        //create a student
        User d = new User();
        d.setGivenName("William");
        d.setLastName("Jones"); 
        d.setPrefName("Bill");
        d.setUserID(33333333);
        userRepository.save(d);
             
        UserType utD = new UserType();
        utD.setPassword("ict302");
        utD.setUserType(new UserIdent(studentType.getTypeID(),d.getUserID()));
        utD.setUser(d);        
        utD.setType(studentType);
        userTypeRepository.save(utD);
        
        display(d);
        
        //add second type To C        
        UserType utE = new UserType();
        utE.setPassword("ict302");
        utE.setUserType(new UserIdent(1,c.getUserID()));
        utE.setUser(c);        
        utE.setType(adminType);
        userTypeRepository.save(utE);
        
        display(c);
        
        
        

        Iterable<UserType> x = userTypeRepository.findAll();
        
        for(UserType r:x)
            displayAll(r);
        
        display(userRepository.findById(11).get());
    }
    
    public void display(User u)
    {
        System.out.println("User ID: " + u.getUserID());
        System.out.println("Given Name: " + u.getGivenName());
        System.out.println("Last Name: " + u.getLastName());
        System.out.println("Preferred Name: " + u.getPrefName());
        System.out.println();
        System.out.println();
    }
    
    //@Transactional(propagation=Propagation.REQUIRED)//   PROPAGATION_REQUIRES_NEW)
    public void displayAll(UserType ut)
    {
        User u = userRepository.findById(ut.getUser().getUserID()).get();
        Type t = typeRepository.findById(ut.getType().getTypeID()).get();
        System.out.println("User ID: " + u.getUserID());
        System.out.println("Given Name: " + u.getGivenName());
        System.out.println("Last Name: " + u.getLastName());
        System.out.println("Preferred Name: " + u.getPrefName());
        System.out.println("Password: " + ut.getPassword());
        System.out.println("Type: " + t.getDescription());

        System.out.println();
        System.out.println();
    }
}
