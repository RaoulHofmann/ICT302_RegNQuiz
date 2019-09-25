/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.classes;

import com.regnquiz.classes.Venue;
import com.regnquiz.repositories.VenueRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * Author: Stuart Hepburn
 * Date: 22/9/2019
 * 
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class VenueRepositoryTest {
    //@Autowired
    //private TestEntityManager entityManager;
    
    @Autowired
    private VenueRepository venueRepository;
    
    @Test
    public void TestVenue() {
        
        Venue a = new Venue();
        
        a.setBuilding(100);
        a.setFloor(2);
        a.setRoom(23);
        a.setCapacity(30);

        saveData(a);      

        Venue b = new Venue(90, 1, 10, 40);
        saveData(b);
       
        Venue c = new Venue("221.4.100", 89);
        saveData(c);
        
        //Updating the capacity of A
        Venue d = new Venue(100, 2, 23, 150);
        saveData(d);
        
        //double entry with c
        Venue e = new Venue(221, 4, 100, 89);
        saveData(e);
  
        
        //display all in DB
        Iterable<Venue> g = venueRepository.findAll();
        
        for(Venue v : g)
            display(v);
        
        
    }

    public void saveData(Venue v)
    {
        //Checks to see if the venue is already in DB
        v.setVenueID(venueRepository.findByLocation(v.getBuilding(), v.getFloor(), v.getRoom()));
        
        try {
            v = venueRepository.save(v);
        }catch(DataIntegrityViolationException ex){
            System.out.println("Error: " + ex);
        }
        
        display(v);
    }
    
    public void display(Venue v)
    {
        System.out.println("Building: " + v.getBuilding());
        System.out.println("Floor: " + v.getFloor());
        System.out.println("Room: " + v.getRoom());
        System.out.println("Capacity: " + v.getCapacity());
        System.out.println("Venue ID: " + v.getVenueID());
        System.out.println("to String: " +  v.toString());
        System.out.println();
        System.out.println();
    }
}
