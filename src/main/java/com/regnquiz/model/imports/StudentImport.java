/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model.imports;

import java.io.IOException;

import com.regnquiz.model.User;
import com.regnquiz.model.repositories.UserRepository;
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
 * @comment CSV reader for student lists
 */
@Service
public class StudentImport 
{
    @Autowired
    private UserRepository userRepo;
    
    @Transactional
    public void ImportStudent(String filename)
    {
        Path myPath = Paths.get(filename);
        
        try
        {
            List<String> lines = Files.readAllLines(myPath);
            
            lines.remove(0);
            
            for(String line : lines)
            {
                String[] lineSplit = line.split(",");
                
                // Student Number, Given Names, Last Name
                User student = new User(Integer.parseInt(lineSplit[1]), lineSplit[4], lineSplit[2]);
                
                try 
                {
                    student = userRepo.save(student);
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
