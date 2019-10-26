/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

/**
 *
 * @author Matthew MacLennan
 */
public class UserTypeKeyTest 
{
    public static void main(String args[])
    {
        UserTypeKey a = new UserTypeKey();
        display(a);
        
        a.setUser(1);
        a.setType(2);
        display(a);
        
        UserTypeKey b = new UserTypeKey(3, 4);
        display(b);
        
        b.setUser(5);
        b.setType(6);
        display(b);
    }
    
    public static void display(UserTypeKey ui)
    {
        System.out.println("User ID: " + ui.getUser());
        System.out.println("Type ID: " + ui.getType());
        System.out.println();
        System.out.println();
    }
}
