/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model.imports;

import java.io.BufferedReader;
import java.io.IOException;

import com.regnquiz.model.Venue;
import com.regnquiz.model.repositories.VenueRepository;
import org.hibernate.id.IdentifierGenerationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    public void ImportVenue(MultipartFile filename)
    {
        BufferedReader br = null; // new buffered reader
        try {
            InputStream is = filename.getInputStream(); // put input stream into new object
            br = new BufferedReader(new InputStreamReader(is, "UTF-8")); // put inputstream into buffered reader
            br.readLine(); //Read First Line

            String line = null;
            while ((line = br.readLine()) != null) // while nextline has data
            {
                String[] lineSplit = line.split(","); // CSV split
                //create ne venue
                Venue v = new Venue(lineSplit[0], Integer.parseInt(lineSplit[1]));
                //check to see if venue is in DB
                try{
                    v.setVenueID(venueRepository.findByLocation(v.getBuilding(), v.getFloor(), v.getRoom()));
                }catch (NoSuchElementException e) {
                    System.out.println(e);
                }

                try {
                    //insert/update to DB
                    v = venueRepository.save(v);
                }
                catch (DataIntegrityViolationException | IdentifierGenerationException ex){
                    System.out.println("CSV Error: " + ex);
                }
            }
        }
        catch(IOException e) {
            System.out.println("ERROR IOException: " + e);
        }
   
    }
}
