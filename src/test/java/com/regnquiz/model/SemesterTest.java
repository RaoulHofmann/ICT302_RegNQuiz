/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;
import java.util.Set;
import java.util.HashSet;

/**
 * Author: Matthew MacLennan
 * Date: 22/9/2019
 * Version: 1
 * Comment: Unit test for Semester
 */
public class SemesterTest 
{
    public static void main(String args[])
    {
        Semester a = new Semester();
        display(a);
        
        a.setSemID(1234);
        a.setDescription("test1");
        
        Set<Unit> u = new HashSet<>();
        u.add(new Unit());
        
        a.setUnit(u);
        display(a);
        
        Semester b = new Semester(4321, "test2");
        display(b);
        
        b.setSemID(5678);
        b.setDescription("test3");
        b.setUnit(u);
        display(b);
        
        b.addUnit(new Unit());
        display(b);
    }
    
    public static void display(Semester s)
    {
        System.out.println("Semseter ID: " + s.getSemID());
        System.out.println("Description: " + s.getDescription());
        System.out.println("Unit Size: " + s.getUnit().size());
        System.out.println();
        System.out.println();
    }
}
