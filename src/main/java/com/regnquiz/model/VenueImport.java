/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

import java.io.IOException;

import com.regnquiz.model.repositories.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 * Author: Stuart Hepburn
 * Date: 24/9/2019
 * Version: 1
 */

@Service
public class VenueImport {
    @Autowired
    private VenueRepository venueRepository;
       
    @Transactional
    public void ImportVenue(String filename)  
    {
        Path myPath = Paths.get(filename);
         
        try{
            //Read the entire file
            List <String> lines = Files.readAllLines(myPath);
           
            //Remove headers
            lines.remove(0);
            
            //loop through each line
            for(String line : lines)
            {
                //split line with the delimiter
                String[] lineSplit = line.split(",");
                //create ne venue
                Venue v = new Venue(lineSplit[0], Integer.parseInt(lineSplit[1]));
                //check to see if venue is in DB 
                v.setVenueID(venueRepository.findByLocation(v.getBuilding(), v.getFloor(), v.getRoom()));

                try {
                    //insert/update to DB
                    v = venueRepository.save(v);
                }
                catch(DataIntegrityViolationException ex){
                    System.out.println("CSV Error: " + ex);
                }
            }
        }
        catch(IOException e) {
            System.out.println("ERROR IOException: " + e);
        }
   
    }
}
