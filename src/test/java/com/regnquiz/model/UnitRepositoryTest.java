/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.regnquiz.model.repositories.UnitRepository;
import com.regnquiz.model.repositories.SemesterRepository;
import com.regnquiz.model.repositories.UserRepository;
/**
 *
 * @author Matthew MacLennan
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UnitRepositoryTest 
{
    @Autowired
    private UnitRepository unitRepo;
    
    @Autowired
    private SemesterRepository semRepo;
    
    @Autowired
    private UserRepository userRepo;
    
    @Test
    public void testUnit()
    {
        Unit u = new Unit(3, "ict302", "project", semRepo.findById(1).get(), 2019, userRepo.findById(11).get());
        display(u);
        
        unitRepo.save(u);
        
        display(unitRepo.findById(3).get());
        
        Iterable<Unit> units = unitRepo.findAll();
        for(Unit unit : units)
            display(unit);
    }
    
    public static void display(Unit u)
    {
        System.out.println("Unit ID: " + u.getUnitID());
        System.out.println("Unit Code: " + u.getUnitCode());
        System.out.println("Unit Name: " + u.getUnitName());
        System.out.println("Year: " + u.getYear());
        System.out.println("Semester ID: " + u.getSemester().getSemID());
        System.out.println("Lecture ID: " + u.getLecture().getUserID());
        System.out.println();
        System.out.println();
    }
}
