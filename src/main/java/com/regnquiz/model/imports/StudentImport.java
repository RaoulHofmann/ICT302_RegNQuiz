/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model.imports;

import java.io.BufferedReader;
import java.io.IOException;

import com.regnquiz.model.User;
import com.regnquiz.model.repositories.UserRepository;
import org.hibernate.id.IdentifierGenerationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    public void ImportStudent(MultipartFile filename)
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
                
                // Student Number, Given Names, Last Name
                User student = new User(Integer.parseInt(lineSplit[1]), lineSplit[4], lineSplit[2]);
                
                try 
                {
                    student = userRepo.save(student);
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
