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
public class UnitImport 
{
    @Autowired
    private UnitRepository unitRepo;
    
    @Autowired
    private UserRepository userRepo;
    
    @Transactional
    public void ImportUnit(String filename)
    {
        Path myPath = Paths.get(filename);
        
        try
        {
            List<String> lines = Files.readAllLines(myPath);
            lines.remove(0);
            for(String line: lines)
            {
                String[] lineSplit = line.split(",");
                // id, unitcode, unitname, sem, year, lecture
                //Unit u = new Unit(Integer.parseInt(lineSplit[0]), lineSplit[1], lineSplit[2], Integer.parseInt(lineSplit[3]), Integer.parseInt(lineSplit[4]), lecture);
                Unit u = new Unit();
                u.setUnitCode(lineSplit[0]);
                u.setUnitName(lineSplit[1]);
                u.setYear(Integer.parseInt(lineSplit[2]));
                u.setSemester(new Semester(lineSplit[3]));
                u.setLecture(userRepo.findById(Integer.parseInt(lineSplit[4])).get());
                
                try
                {
                    u = unitRepo.save(u);
                }
                catch (DataIntegrityViolationException ex)
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
