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
public class ClassListImportTest 
{
    @Autowired
    private ClassListImport clImport;
    
    @Test
    public void TestCL()
    {
        //System.out.println("sdfgsd");
        clImport.ImportClassList("D:/Desktop/ClassList Example.csv");
        //System.out.println("sdfgsd");
    }
}
