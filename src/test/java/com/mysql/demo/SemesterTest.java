/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mysql.demo;

/**
 *
 * @author Matthew MacLennan
 */
public class SemesterTest 
{
    public static void main(String args[])
    {
        Semester a = new Semester();
        display(a);
        
        a.setSemID(1234);
        a.setDescription("test1");
        display(a);
        
        Semester b = new Semester(4321, "test2");
        display(b);
        
        b.setSemID(5678);
        b.setDescription("test3");
        display(b);
    }
    
    public static void display(Semester s)
    {
        System.out.println("Semseter ID: " + s.getSemID());
        System.out.println("Description: " + s.getDescription());
        System.out.println();
        System.out.println();
    }
}
