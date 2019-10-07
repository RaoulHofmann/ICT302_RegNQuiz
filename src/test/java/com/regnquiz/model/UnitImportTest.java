/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

import com.regnquiz.model.repositories.UnitRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 *
 * @author Matthew MacLennan
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UnitImportTest 
{
    @Autowired
    private UnitRepository unitRepo;
    
    @Autowired
    private UnitImport ui;
    
    @Test
    public void TestUnit()
    {
        User lecture = new User(1, "first", "pref", "last");
        ui.ImportUnit("D:/Unit.csv", lecture);
        Iterable<Unit> units = unitRepo.findAll();
        
        for(Unit u : units)
        {
            display(u);
        }
    }
    
    public void display(Unit u)
    {
        System.out.println("Unit ID: " + u.getUnitID());
        System.out.println("Unit Code: " + u.getUnitCode());
        System.out.println("Unit Name: " + u.getUnitName());
        System.out.println("Year: " + u.getYear());
        System.out.println();
        System.out.println();
    }
}
