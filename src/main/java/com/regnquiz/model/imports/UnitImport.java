/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model.imports;

import com.regnquiz.model.Semester;
import com.regnquiz.model.Unit;
import com.regnquiz.model.repositories.UnitRepository;
import com.regnquiz.model.repositories.UserRepository;

import java.io.BufferedReader;
import java.io.IOException;

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
 *
 * @author Matthew MacLennan
 * @comment CSV reader for Unit class, deposits in database
 */

@Service
public class UnitImport 
{
    @Autowired
    private UnitRepository unitRepo;
    
    @Autowired
    private UserRepository userRepo;
    
    @Transactional
    public void ImportUnit(MultipartFile filename)
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
                // id, unitcode, unitname, sem, year, lecture
                //Unit u = new Unit(Integer.parseInt(lineSplit[0]), lineSplit[1], lineSplit[2], Integer.parseInt(lineSplit[3]), Integer.parseInt(lineSplit[4]), lecture);
                Unit u = new Unit();
                u.setUnitCode(lineSplit[0]);
                u.setUnitName(lineSplit[1]);
                u.setYear(Integer.parseInt(lineSplit[2]));
                u.setSemester(new Semester(lineSplit[3]));
                try {
                    u.setLecture(userRepo.findById(Integer.parseInt(lineSplit[4])).get());
                }catch (NoSuchElementException e){
                    System.out.println(e);
                }


                try
                {
                    u = unitRepo.save(u);
                }
                catch (DataIntegrityViolationException | IdentifierGenerationException ex)
                {
                    System.out.println("CSV Error: " + ex);
                }
            }
        }
        catch(IOException e)
        {
            System.out.println("Error IOException: " + e);
        }
    }
}
