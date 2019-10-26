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
import com.regnquiz.model.repositories.UserTypeRepository;
import com.regnquiz.model.repositories.TypeRepository;
import com.regnquiz.model.repositories.UserRepository;
import java.util.List;
/**
 *
 * @author Matthew MacLennan
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTypeRepositoryTest 
{
    @Autowired
    private UserTypeRepository utRepo;
    
    @Autowired
    private TypeRepository typeRepo;
    
    @Autowired 
    private UserRepository userRepo;
            
    @Test
    public void testUserType()
    {
        User user = userRepo.findById(11).get();
        System.out.println("User ID: " + user.getUserID());
        Type type = typeRepo.findById(3).get();
        System.out.println("Type ID: " + type.getTypeID());
        UserType ut = new UserType(type, user, "password");
        
        display(ut);
        //System.out.println("********************************************************************************************************************************************** SAVING");
        utRepo.save(ut);
        //System.out.println("********************************************************************************************************************************************** SAVED");

        
        List<UserType> temp = utRepo.findByUser_UserID(11);
        for(int i = 0; i < temp.size(); i++)
            display(temp.get(i));
        
        Iterable<UserType> allUTs =utRepo.findAll();
        for(UserType usertype : allUTs)
            display(usertype);
    }
    
    public static void display(UserType ut)
    {
        System.out.println("User ID: " + ut.getUser().getUserID());
        System.out.println("Type ID: " + ut.getType().getTypeID());
        System.out.println("Password: " + ut.getPassword());
        System.out.println();
        System.out.println();
    }
}
