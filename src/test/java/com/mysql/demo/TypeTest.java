/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mysql.demo;

/**
 * Author: Matthew MacLennan
 * Date: 22/9/2019
 * Version: 1
 * Comment: Unit test for Type
 */
public class TypeTest 
{
    public static void main(String args[])
    {
        Type a = new Type();
        display(a);
        
        a.setTypeID(1234);
        a.setDescription("test1");
        display(a);
        
        Type b = new Type(4321, "test2");
        display(b);
        
        b.setTypeID(5678);
        b.setDescription("test3");
        display(b);
    }
    
    public static void display(Type t)
    {
        System.out.println("Type ID: " + t.getTypeID());
        System.out.println("Description: " + t.getDescription());
        System.out.println();
        System.out.println();
    }
}
