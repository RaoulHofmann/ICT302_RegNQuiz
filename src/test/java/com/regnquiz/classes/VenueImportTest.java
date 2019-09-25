/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.classes;

import com.regnquiz.classes.Venue;
import com.regnquiz.classes.VenueImport;
import com.regnquiz.repositories.VenueRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * 
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class VenueImportTest {
    @Autowired
    private VenueRepository venueRepository;
    
    @Autowired
    private VenueImport vi;
    
    @Test
    public void TestVenue() {
        vi.ImportVenue("F:/Venue.csv");
        //display all in DB
        Iterable<Venue> g = venueRepository.findAll();
        
        for(Venue v : g)
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
