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
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

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
    public void ImportClassList(String filename)
    {
        Path myPath = Paths.get(filename); // set path and open file
        
        try
        {
            List<String> lines = Files.readAllLines(myPath); // read the lines
            lines.remove(0); // remove fist line
            for(String line: lines) 
            {
                String[] lineSplit = line.split(","); // split csv
                
                // deposit data into object
                ClassList cl = new ClassList();
                cl.setStudent(userRepo.findById(Integer.parseInt(lineSplit[0])).get());
                cl.setBooking(bookingRepo.findById(Integer.parseInt(lineSplit[1])).get());
                if(Integer.parseInt(lineSplit[2]) == 1)
                    cl.setInternal(true);
                else
                    cl.setInternal(false);
                
                try 
                {
                    cl = clRepo.save(cl); // save to database
                }
                catch (DataIntegrityViolationException ex)
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
