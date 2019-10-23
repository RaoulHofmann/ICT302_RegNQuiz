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

/**
 *
 * @author Matthew MacLennan
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UnitImportTest 
{
    @Autowired
    private UnitImport ui;
    
    @Test
    public void TestUnit()
    {
        ui.ImportUnit("D:/Desktop/UnitExample.csv");
    }
}