/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model.imports;

import com.regnquiz.model.ClassList;
import com.regnquiz.model.repositories.BookingRepository;
import com.regnquiz.model.repositories.ClassListRepository;
import com.regnquiz.model.repositories.UserRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;
import javax.transaction.Transactional;

import org.hibernate.id.IdentifierGenerationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Matthew MacLennan
 * @comment CSV reader for ClassList class, deposits in database
 */
@Service
public class ClassListImport 
{
    @Autowired
    private ClassListRepository clRepo;
    
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private BookingRepository bookingRepo;
    
    @Transactional
    public void ImportClassList(MultipartFile filename)
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
                
                // deposit data into object
                ClassList cl = new ClassList();
                try {
                    cl.setStudent(userRepo.findById(Integer.parseInt(lineSplit[0])).get());
                }catch (NoSuchElementException e){
                    System.out.println(e);
                }

                try {
                    cl.setBooking(bookingRepo.findById(Integer.parseInt(lineSplit[1])).get());
                }catch (NoSuchElementException e){
                    System.out.println(e);
                }
                if(Integer.parseInt(lineSplit[2]) == 1)
                    cl.setInternal(true);
                else
                    cl.setInternal(false);
                
                try 
                {
                    cl = clRepo.save(cl); // save to database
                }
                catch (DataIntegrityViolationException | IdentifierGenerationException ex)
                {
                    System.out.println("CSV Error: " + ex);
                }
            }
        }
        catch(IOException e)
        {
            System.out.println("ERROR IOException: " + e);
        }
    }
}
