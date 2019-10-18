/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

import com.regnquiz.model.repositories.UserRepository;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 *
 * @author Matthew MacLennan
 */
@Service
public class StaffImport 
{
    @Autowired
    private UserRepository userRepo;
    
    @Transactional
    public void ImportStaff(String filename)
    {
        Path myPath = Paths.get(filename);    
        try
        {
            List<String> lines = Files.readAllLines(myPath);
            
            lines.remove(0);
            
            for(String line : lines)
            {
                String[] lineSplit = line.split(",");
                
                // Staff Number, Given Names, Last Name
                User staff = new User(Integer.parseInt(lineSplit[0]), lineSplit[1], lineSplit[2]);
                
                try 
                {
                    staff = userRepo.save(staff);
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